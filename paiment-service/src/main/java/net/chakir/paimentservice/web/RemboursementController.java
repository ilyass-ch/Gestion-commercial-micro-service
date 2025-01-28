package net.chakir.paimentservice.web;

import net.chakir.paimentservice.entities.Remboursement;
import net.chakir.paimentservice.sevice.RemboursementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remboursements")
public class RemboursementController {

    private final RemboursementService remboursementService;

    public RemboursementController(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
    }

    // Créer un remboursement
    @PostMapping
    public ResponseEntity<Remboursement> createRemboursement(@RequestBody Remboursement remboursement) {
        Remboursement createdRemboursement = remboursementService.createRemboursement(remboursement);
        return ResponseEntity.ok(createdRemboursement);
    }

    // Obtenir un remboursement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Remboursement> getRemboursementById(@PathVariable Long id) {
        Remboursement remboursement = remboursementService.getRemboursementById(id);
        return ResponseEntity.ok(remboursement);
    }

    // Obtenir tous les remboursements
    @GetMapping
    public ResponseEntity<List<Remboursement>> getAllRemboursements() {
        List<Remboursement> remboursementList = remboursementService.getAllRemboursements();
        return ResponseEntity.ok(remboursementList);
    }

    // Obtenir les remboursements par client ID
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Remboursement>> getRemboursementsByClientId(@PathVariable Long clientId) {
        List<Remboursement> remboursements = remboursementService.getRemboursementsByClientId(clientId);
        return ResponseEntity.ok(remboursements);
    }

    // Obtenir les remboursements par paiement ID
    @GetMapping("/paiement/{paiementId}")
    public ResponseEntity<List<Remboursement>> getRemboursementsByPaiementId(@PathVariable Long paiementId) {
        List<Remboursement> remboursements = remboursementService.getRemboursementsByPaiementId(paiementId);
        return ResponseEntity.ok(remboursements);
    }

    // Mettre à jour un remboursement
    @PutMapping("/{id}")
    public ResponseEntity<Remboursement> updateRemboursement(@PathVariable Long id, @RequestBody Remboursement remboursementDetails) {
        Remboursement updatedRemboursement = remboursementService.updateRemboursement(id, remboursementDetails);
        return ResponseEntity.ok(updatedRemboursement);
    }

    // Supprimer un remboursement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}
