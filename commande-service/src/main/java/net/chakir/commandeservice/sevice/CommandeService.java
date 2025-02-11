package net.chakir.commandeservice.sevice;


import net.chakir.commandeservice.entities.Commande;
import net.chakir.commandeservice.model.Client;
import net.chakir.commandeservice.repo.CommandeRepository;
import net.chakir.commandeservice.transaction.ClientRestClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    private final ClientRestClient clientRestClient;
    private final CommandeRepository commandeRepository;
    private final KafkaProducerService kafkaProducerService;

    public CommandeService(CommandeRepository commandeRepository, ClientRestClient clientRestClient, KafkaProducerService kafkaProducerService) {
        this.commandeRepository = commandeRepository;
        this.clientRestClient = clientRestClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Commande> getCommandes() {
        List<Commande> commandeList = commandeRepository.findAll();
        commandeList.forEach(commande -> {
            Client client = clientRestClient.getClient(commande.getClientId());
            commande.setClient(client); // Persister le client dans la commande avant de retourner
        });
        return commandeList;
    }

    public Commande getCommande(Long id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            Client client = clientRestClient.getClient(commande.getClientId());
            commande.setClient(client); // Persister le client dans la commande avant de retourner
            return commande;
        } else {
            throw new RuntimeException("Commande non trouvée avec l'id: " + id);
        }
    }

    public Commande createCommande(Commande commande) {
        commande.setDateCreation(LocalDateTime.now());

        kafkaProducerService.sendCommandeEvent(commande);
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande existingCommande = optionalCommande.get();
            existingCommande.setStatut(updatedCommande.getStatut());
            existingCommande.setMontantTotal(updatedCommande.getMontantTotal());
            existingCommande.setClientId(updatedCommande.getClientId());
            return commandeRepository.save(existingCommande);
        } else {
            throw new RuntimeException("Commande non trouvée avec l'id: " + id);
        }
    }

    public void deleteCommande(Long id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            commandeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Commande non trouvée avec l'id: " + id);
        }
    }

    public List<Commande> getCommandesByClientId(Long clientId) {
        List<Commande> commandes = commandeRepository.findByClientId(clientId);
        commandes.forEach(commande -> {
            Client client = clientRestClient.getClient(commande.getClientId());
            commande.setClient(client);
        });
        return commandes;
    }

    public List<Commande> getCommandesByStatut(String statut) {
        return commandeRepository.findByStatut(statut);
    }

    public List<Commande> getCommandesByDateRange(LocalDateTime start, LocalDateTime end) {
        return commandeRepository.findByDateCreationBetween(start, end);
    }
}
