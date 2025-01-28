package net.chakir.commandeservice.sevice;


import net.chakir.commandeservice.entities.CommandeProduit;
import net.chakir.commandeservice.model.Produit;
import net.chakir.commandeservice.repo.CommandeProduitRepository;
import net.chakir.commandeservice.transaction.ProduitRestClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeProduitService {
    private final CommandeProduitRepository commandeProduitRepository;
    private final ProduitRestClient produitRestClient;

    public CommandeProduitService(CommandeProduitRepository commandeProduitRepository, ProduitRestClient produitRestClient) {
        this.commandeProduitRepository = commandeProduitRepository;
        this.produitRestClient = produitRestClient;
    }

    public List<CommandeProduit> getAllCommandeProduits() {
        List<CommandeProduit> commandeProduits = commandeProduitRepository.findAll();
        commandeProduits.forEach(cp -> {
            Produit produit = produitRestClient.getProduitById(cp.getProduitId());
            cp.setProduit(produit);
        });
        return commandeProduits;
    }

    public CommandeProduit getCommandeProduitById(Long id) {
        Optional<CommandeProduit> optionalCommandeProduit = commandeProduitRepository.findById(id);
        if (optionalCommandeProduit.isPresent()) {
            CommandeProduit commandeProduit = optionalCommandeProduit.get();
            Produit produit = produitRestClient.getProduitById(commandeProduit.getProduitId());
            commandeProduit.setProduit(produit);
            return commandeProduit;
        } else {
            throw new RuntimeException("CommandeProduit non trouvé avec l'id: " + id);
        }
    }

    public CommandeProduit createCommandeProduit(CommandeProduit commandeProduit) {
        return commandeProduitRepository.save(commandeProduit);
    }

    public CommandeProduit updateCommandeProduit(Long id, CommandeProduit updatedCommandeProduit) {
        Optional<CommandeProduit> optionalCommandeProduit = commandeProduitRepository.findById(id);
        if (optionalCommandeProduit.isPresent()) {
            CommandeProduit existingCommandeProduit = optionalCommandeProduit.get();
            existingCommandeProduit.setQuantite(updatedCommandeProduit.getQuantite());
            existingCommandeProduit.setPrixUnitaire(updatedCommandeProduit.getPrixUnitaire());
            existingCommandeProduit.setProduitId(updatedCommandeProduit.getProduitId());
            return commandeProduitRepository.save(existingCommandeProduit);
        } else {
            throw new RuntimeException("CommandeProduit non trouvé avec l'id: " + id);
        }
    }

    public void deleteCommandeProduit(Long id) {
        Optional<CommandeProduit> optionalCommandeProduit = commandeProduitRepository.findById(id);
        if (optionalCommandeProduit.isPresent()) {
            commandeProduitRepository.deleteById(id);
        } else {
            throw new RuntimeException("CommandeProduit non trouvé avec l'id: " + id);
        }
    }

    public List<CommandeProduit> getCommandeProduitsByCommandeId(Long commandeId) {
        List<CommandeProduit> commandeProduits = commandeProduitRepository.findByCommandeId(commandeId);
        commandeProduits.forEach(cp -> {
            Produit produit = produitRestClient.getProduitById(cp.getProduitId());
            cp.setProduit(produit);
        });
        return commandeProduits;
    }

    public List<CommandeProduit> getCommandeProduitsByProduitId(Long produitId) {
        return commandeProduitRepository.findByProduitId(produitId);
    }
}

