package net.chakir.facturationservice.web;

import net.chakir.facturationservice.entities.Taxe;
import net.chakir.facturationservice.sevice.TaxeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxes")
public class TaxeController {
    private final TaxeService taxeService;

    public TaxeController(TaxeService taxeService) {
        this.taxeService = taxeService;
    }

    // ✅ Ajouter une taxe
    @PostMapping
    public ResponseEntity<Taxe> ajouterTaxe(@RequestBody Taxe taxe) {
        return ResponseEntity.ok(taxeService.ajouterTaxe(taxe));
    }

    // ✅ Obtenir toutes les taxes
    @GetMapping
    public ResponseEntity<List<Taxe>> obtenirTaxes() {
        return ResponseEntity.ok(taxeService.obtenirTaxes());
    }

    // ✅ Obtenir une taxe par ID
    @GetMapping("/{id}")
    public ResponseEntity<Taxe> getTaxeById(@PathVariable Long id) {
        return ResponseEntity.ok(taxeService.getTaxeById(id));
    }

    // ✅ Supprimer une taxe par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxe(@PathVariable Long id) {
        taxeService.deleteTaxe(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Mettre à jour une taxe
    @PutMapping("/{id}")
    public ResponseEntity<Taxe> updateTaxe(@PathVariable Long id, @RequestBody Taxe taxe) {
        return ResponseEntity.ok(taxeService.updateTaxe(id, taxe));
    }

    // ✅ Rechercher une taxe par nom
    @GetMapping("/name/{name}")
    public ResponseEntity<Taxe> getTaxeByName(@PathVariable String name) {
        return ResponseEntity.ok(taxeService.getTaxeByName(name));
    }

    // ✅ Rechercher une taxe par pourcentage
    @GetMapping("/pourcentage/{pourcentage}")
    public ResponseEntity<Taxe> getTaxeByPourcentage(@PathVariable double pourcentage) {
        return ResponseEntity.ok(taxeService.getTaxeByPourcentage(pourcentage));
    }
}

