package net.chakir.productservice.web;

import net.chakir.productservice.entities.Produit;
import net.chakir.productservice.sevice.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
public class ProduitController {


    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<Produit> getAllProduits() {
    List<Produit> produits = produitService.getAllProduits();
    System.out.println(produits);
    return produits;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.saveProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Optional<Produit> existingProduit = produitService.getProduitById(id);
        if (existingProduit.isPresent()) {
            produit.setId(id);
            return ResponseEntity.ok(produitService.saveProduit(produit));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/By-Name")
    public List<Produit> getProduitsByName(@RequestParam String name) {
        return produitService.getProduitByName(name);
    }

    @GetMapping("/by-name-contains")
    public List<Produit> getProduitsByNameContains(@RequestParam String name) {
        return produitService.getProduitByNameContains(name);
    }

}
