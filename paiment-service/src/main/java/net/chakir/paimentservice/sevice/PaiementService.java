package net.chakir.paimentservice.sevice;

import net.chakir.paimentservice.entities.Paiement;
import net.chakir.paimentservice.enums.CodePromoStatut;
import net.chakir.paimentservice.model.Client;
import net.chakir.paimentservice.model.CodePromo;
import net.chakir.paimentservice.model.Commande;
import net.chakir.paimentservice.repo.PaiementRepository;
import net.chakir.paimentservice.transaction.ClientRestClient;
import net.chakir.paimentservice.transaction.CodePromoRestClient;
import net.chakir.paimentservice.transaction.CommandeRestclient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final ClientRestClient clientRestClient;
    private final CommandeRestclient commandeRestclient;
    private final CodePromoRestClient codePromoRestClient;
    private final KafkaProducerService kafkaProducerService;  // Injection de KafkaProducerService

    public PaiementService(PaiementRepository paiementRepository, ClientRestClient clientRestClient, CommandeRestclient commandeRestclient, CodePromoRestClient codePromoRestClient, KafkaProducerService kafkaProducerService) {
        this.paiementRepository = paiementRepository;
        this.clientRestClient = clientRestClient;
        this.commandeRestclient = commandeRestclient;
        this.codePromoRestClient = codePromoRestClient;
        this.kafkaProducerService = kafkaProducerService;  // Initialisation de KafkaProducerService
    }

    public Paiement createPaiement(Paiement paiement) {
        // Vérifier si un code promo est fourni
//        if (paiement.getCodePromo() != null && !paiement.getCodePromo().isEmpty()) {
//            try {
//                CodePromo codePromo = codePromoRestClient.getCodePromoById(Long.valueOf(paiement.getCodePromo()));
//
//                if (codePromo != null && CodePromoStatut.ACTIF.equals(codePromo.getCodePromoStatut())) {
//                    // Appliquer la réduction
//                    double reduction = codePromo.getMontantReduction();
//                    paiement.setReduction(reduction);
//                    paiement.setMontant(paiement.getMontant() - reduction);
//                } else {
//                    throw new RuntimeException("Code promo invalide ou expiré.");
//                }
//            } catch (Exception e) {
//                throw new RuntimeException("Erreur lors de la récupération du code promo : " + e.getMessage());
//            }
//        }

        Paiement savedPaiement = paiementRepository.save(paiement);

        // Publier un événement de paiement après la création
        kafkaProducerService.sendPaiementEvent(savedPaiement);  // Envoi de l'événement Kafka

        return savedPaiement;
    }

    public boolean verifierCodePromo(String codePromo) {
        if (codePromo == null || codePromo.isEmpty()) {
            throw new RuntimeException("Le code promo est vide ou invalide.");
        }

        try {
            CodePromo promo = codePromoRestClient.getCodePromoById(Long.valueOf(codePromo));

            if (promo != null && CodePromoStatut.ACTIF.equals(promo.getCodePromoStatut())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la vérification du code promo : " + e.getMessage());
        }
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

        Paiement updatedPaiement = paiementRepository.save(paiement);

        // Publier un événement de paiement après la mise à jour
        kafkaProducerService.sendPaiementEvent(updatedPaiement);  // Envoi de l'événement Kafka

        return updatedPaiement;
    }

    public void deletePaiementById(Long id) {
        Paiement paiement = paiementRepository.findById(id).orElseThrow(() -> new RuntimeException("Paiement not found"));

        paiementRepository.deleteById(id);

        // Publier un événement de suppression de paiement
        kafkaProducerService.sendPaiementEvent(paiement);  // Envoi de l'événement Kafka
    }
}
