package net.chakir.commandeservice.web;


import net.chakir.commandeservice.entities.Commande;
import net.chakir.commandeservice.sevice.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommande(id));
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande createdCommande = commandeService.createCommande(commande);
        return ResponseEntity.ok(createdCommande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        Commande updated = commandeService.updateCommande(id, updatedCommande);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Commande>> getCommandesByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(commandeService.getCommandesByClientId(clientId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Commande>> getCommandesByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(commandeService.getCommandesByStatut(statut));
    }

    @GetMapping("/daterange")
    public ResponseEntity<List<Commande>> getCommandesByDateRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(commandeService.getCommandesByDateRange(start, end));
    }
}
