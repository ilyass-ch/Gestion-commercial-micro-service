package net.chakir.facturationservice.sevice;

import jakarta.persistence.EntityNotFoundException;
import net.chakir.facturationservice.entities.Facture;
import net.chakir.facturationservice.entities.Taxe;
import net.chakir.facturationservice.model.Client;
import net.chakir.facturationservice.model.Commande;
import net.chakir.facturationservice.repo.FactureRepository;
import net.chakir.facturationservice.repo.TaxeRepository;
import net.chakir.facturationservice.transaction.ClientRestClient;
import net.chakir.facturationservice.transaction.CommandeRestclient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {
    private final FactureRepository factureRepository;
    private final TaxeRepository taxeRepository;
    private final ClientRestClient clientRestClient;
    private final CommandeRestclient commandeRestclient;

    public FactureService(FactureRepository factureRepository, TaxeRepository taxeRepository,
                          ClientRestClient clientRestClient, CommandeRestclient commandeRestclient) {
        this.factureRepository = factureRepository;
        this.taxeRepository = taxeRepository;
        this.clientRestClient = clientRestClient;
        this.commandeRestclient = commandeRestclient;
    }

    // ✅ Calcul automatique du montant TTC en fonction des taxes associées
    private double calculerMontantTTC(Facture facture) {
        List<Taxe> taxes = facture.getTaxes();
        double tauxTotal = taxes.stream().mapToDouble(Taxe::getPourcentage).sum();
        return facture.getMontantHT() * (1 + (tauxTotal / 100));
    }

    public List<Facture> findAll() {
        List<Facture> factures = factureRepository.findAll();
        factures.forEach(this::chargerDetailsFacture);
        return factures;
    }

    public Facture findById(Long id) {
        return factureRepository.findById(id)
                .map(facture -> {
                    chargerDetailsFacture(facture);
                    return facture;
                })
                .orElseThrow(() -> new RuntimeException("Facture avec l'id " + id + " non trouvée"));
    }

    public List<Facture> findByClientId(Long clientId) {
        List<Facture> factures = factureRepository.findByClientId(clientId);
        factures.forEach(this::chargerDetailsFacture);
        return factures;
    }

    public Facture findByCommandeId(Long commandeId) {
        Facture facture = factureRepository.findByCommandeId(commandeId);
        if (facture == null) {
            throw new RuntimeException("Facture avec Commande ID " + commandeId + " non trouvée");
        }
        chargerDetailsFacture(facture);
        return facture;
    }

    public Facture save(Facture facture) {
        // Recalculer le montant TTC avant sauvegarde
        facture.setMontantTTC(calculerMontantTTC(facture));
        return factureRepository.save(facture);
    }

    public Facture updateFacture(Long id, Facture facture) {
        return factureRepository.findById(id)
                .map(existingFacture -> {
                    existingFacture.setClientId(facture.getClientId());
                    existingFacture.setCommandeId(facture.getCommandeId());
                    existingFacture.setTva(facture.getTva());
                    existingFacture.setMontantHT(facture.getMontantHT());
                    existingFacture.setTaxes(facture.getTaxes()); // Mise à jour des taxes

                    // Recalcul automatique du Montant TTC après mise à jour des taxes
                    existingFacture.setMontantTTC(calculerMontantTTC(existingFacture));

                    return factureRepository.save(existingFacture);
                })
                .orElseThrow(() -> new EntityNotFoundException("Facture avec l'ID " + id + " non trouvée"));
    }

    public void  deleteFacture(Long id) {
         if (factureRepository.existsById(id)) {
             factureRepository.deleteById(id);
         }
         throw new EntityNotFoundException("Facture with ID "+ id+" not found");
    }


    // ✅ Charger les détails (Client, Commande et Taxes) pour chaque facture
    private void chargerDetailsFacture(Facture facture) {
        Client client = clientRestClient.getClient(facture.getClientId());
        Commande commande = commandeRestclient.getCommandeById(facture.getCommandeId());
        facture.setCommande(commande);
        facture.setClient(client);
    }
}
