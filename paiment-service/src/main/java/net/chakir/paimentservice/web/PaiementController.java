package net.chakir.paimentservice.web;

import net.chakir.paimentservice.entities.Paiement;
import net.chakir.paimentservice.sevice.PaiementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiements")
public class PaiementController {
    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // Ajouter un paiement
    @PostMapping
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        Paiement createdPaiement = paiementService.createPaiement(paiement);
        return ResponseEntity.ok(createdPaiement);
    }

    // Obtenir un paiement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiement(@PathVariable Long id) {
        Paiement paiement = paiementService.getPaiement(id);
        return ResponseEntity.ok(paiement);
    }

    // Obtenir tous les paiements
    @GetMapping
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        List<Paiement> paiements = paiementService.getAllPaiments();
        return ResponseEntity.ok(paiements);
    }

    // Obtenir les paiements par ID du client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Paiement>> getPaiementsByClientId(@PathVariable Long clientId) {
        List<Paiement> paiements = paiementService.getPaiementsByClientId(clientId);
        return ResponseEntity.ok(paiements);
    }

    // Supprimer un paiement par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiementById(id);
        return ResponseEntity.noContent().build();
    }

    // Mettre à jour un paiement
    @PutMapping("/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        Paiement updatedPaiement = paiementService.updatePaiement(id, paiement);
        return ResponseEntity.ok(updatedPaiement);
    }
}
