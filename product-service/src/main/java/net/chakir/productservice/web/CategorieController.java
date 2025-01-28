package net.chakir.productservice.web;

import net.chakir.productservice.entities.Categorie;
import net.chakir.productservice.sevice.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    // Récupérer toutes les catégories
    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    // Récupérer une catégorie par ID
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieService.getCategorieById(id);
        return categorie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajouter une nouvelle catégorie
    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.saveCategorie(categorie);
    }

    // Mettre à jour une catégorie existante
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        Optional<Categorie> existingCategorie = categorieService.getCategorieById(id);
        if (existingCategorie.isPresent()) {
            categorie.setId(id);
            return ResponseEntity.ok(categorieService.saveCategorie(categorie));
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer une catégorie par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public List<Categorie> getCategoriesByName(@RequestParam String name) {
        return categorieService.getCategoriesByName(name);
    }


    @GetMapping("/by-name-contains")
    public List<Categorie> getCategoriesByNameContains(@RequestParam String name) {
        return categorieService.getCategirieByNameContains(name);
    }
}
