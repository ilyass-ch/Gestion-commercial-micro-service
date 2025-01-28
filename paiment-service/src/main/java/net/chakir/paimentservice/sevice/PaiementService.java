package net.chakir.paimentservice.sevice;

import net.chakir.paimentservice.entities.Paiement;
import net.chakir.paimentservice.entities.Remboursement;
import net.chakir.paimentservice.model.Client;
import net.chakir.paimentservice.model.Commande;
import net.chakir.paimentservice.repo.PaiementRepository;
import net.chakir.paimentservice.transaction.ClientRestClient;
import net.chakir.paimentservice.transaction.CommandeRestclient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final ClientRestClient clientRestClient;
    private final CommandeRestclient commandeRestclient;

    public PaiementService(PaiementRepository paiementRepository, ClientRestClient clientRestClient, CommandeRestclient commandeRestclient) {
        this.paiementRepository = paiementRepository;
        this.clientRestClient = clientRestClient;
        this.commandeRestclient = commandeRestclient;
    }

    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement getPaiement(Long id) {
        Paiement paiement = paiementRepository.findPaiementById(id);
        Client client = clientRestClient.getClient(paiement.getClientId());
        Commande commande = commandeRestclient.getCommandeById(paiement.getCommandeId());
        paiement.setClient(client);
        paiement.setCommande(commande);
        return paiement;
    }

    public List<Paiement> getPaiementsByClientId(Long clientId ) {
        List<Paiement> paiements = paiementRepository.findPaiementByClientId(clientId);
        paiements.forEach(paiement -> {
            Client client = clientRestClient.getClient(paiement.getClientId());
            Commande commande = commandeRestclient.getCommandeById(paiement.getCommandeId());
            paiement.setClient(client);
            paiement.setCommande(commande);
        });
        return paiements;
    }

    public List<Paiement> getAllPaiments (){
        List<Paiement> paiements = paiementRepository.findAll();
        paiements.forEach(paiement -> {
            Client client = clientRestClient.getClient(paiement.getClientId());
            Commande commande = commandeRestclient.getCommandeById(paiement.getCommandeId());
            paiement.setClient(client);
            paiement.setCommande(commande);
        });
        return paiements;
    }

    public Paiement updatePaiement(Long id, Paiement paiementDetails) {
        Paiement paiement = paiementRepository.findById(id).orElseThrow(() -> new RuntimeException("Paiement not found"));
        paiement.setMontant(paiementDetails.getMontant());
        paiement.setDate(paiementDetails.getDate());
        paiement.setClientId(paiementDetails.getClientId());
        paiement.setCommandeId(paiementDetails.getCommandeId());
        paiement.setMethodPaiement(paiementDetails.getMethodPaiement());
        paiement.setStatut(paiementDetails.getStatut());
        return paiementRepository.save(paiement);
    }


    public void deletePaiementById(Long id) {
        paiementRepository.deleteById(id);
    }
}

