package net.chakir.facturationservice.web;

import net.chakir.facturationservice.entities.Facture;
import net.chakir.facturationservice.sevice.FactureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {
    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    /// Ajouter une nouvelle facture
    @PostMapping
    public ResponseEntity<Facture> save(@RequestBody Facture facture) {
        Facture savedFacture = factureService.save(facture);
        return ResponseEntity.ok(savedFacture);
    }

    /// Obtenir toutes les factures
    @GetMapping
    public ResponseEntity<List<Facture>> findAll() {
        return ResponseEntity.ok(factureService.findAll());
    }

    /// Obtenir une facture par ID
    @GetMapping("/{id}")
    public ResponseEntity<Facture> findById(@PathVariable Long id) {
        return ResponseEntity.ok(factureService.findById(id));
    }

    /// Obtenir les factures par `clientId`
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Facture>> findByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(factureService.findByClientId(clientId));
    }

    /// Obtenir une facture par `commandeId`
    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<Facture> findByCommandeId(@PathVariable Long commandeId) {
        return ResponseEntity.ok(factureService.findByCommandeId(commandeId));
    }

    /// Mettre à jour une facture
    @PutMapping("/{id}")
    public ResponseEntity<Facture> update(@PathVariable Long id, @RequestBody Facture facture) {
        Facture updatedFacture = factureService.updateFacture(id, facture);
        return ResponseEntity.ok(updatedFacture);
    }

    /// Supprimer une facture
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }
}

