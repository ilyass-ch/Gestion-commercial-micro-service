package net.chakir.commandeservice.web;


import net.chakir.commandeservice.entities.CommandeProduit;
import net.chakir.commandeservice.sevice.CommandeProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commande-produits")
public class CommandeProduitController {
    private final CommandeProduitService commandeProduitService;

    public CommandeProduitController(CommandeProduitService commandeProduitService) {
        this.commandeProduitService = commandeProduitService;
    }

    @GetMapping
    public ResponseEntity<List<CommandeProduit>> getAllCommandeProduits() {
        return ResponseEntity.ok(commandeProduitService.getAllCommandeProduits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeProduit> getCommandeProduitById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeProduitService.getCommandeProduitById(id));
    }

    @PostMapping
    public ResponseEntity<CommandeProduit> createCommandeProduit(@RequestBody CommandeProduit commandeProduit) {
        CommandeProduit createdCommandeProduit = commandeProduitService.createCommandeProduit(commandeProduit);
        return ResponseEntity.ok(createdCommandeProduit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeProduit> updateCommandeProduit(@PathVariable Long id, @RequestBody CommandeProduit updatedCommandeProduit) {
        CommandeProduit updated = commandeProduitService.updateCommandeProduit(id, updatedCommandeProduit);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommandeProduit(@PathVariable Long id) {
        commandeProduitService.deleteCommandeProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<List<CommandeProduit>> getCommandeProduitsByCommandeId(@PathVariable Long commandeId) {
        return ResponseEntity.ok(commandeProduitService.getCommandeProduitsByCommandeId(commandeId));
    }

    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<CommandeProduit>> getCommandeProduitsByProduitId(@PathVariable Long produitId) {
        return ResponseEntity.ok(commandeProduitService.getCommandeProduitsByProduitId(produitId));
    }
}

