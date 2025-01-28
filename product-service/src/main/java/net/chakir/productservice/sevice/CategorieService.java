package net.chakir.productservice.sevice;

import net.chakir.productservice.entities.Categorie;
import net.chakir.productservice.repo.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {


    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    // Obtenir toutes les catégories
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    // Ajouter ou mettre à jour une catégorie
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Obtenir une catégorie par ID
    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    // Supprimer une catégorie par ID
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    public List<Categorie> getCategoriesByName(String name) {
        return categorieRepository.findByName(name);
    }

    public List<Categorie> getCategirieByNameContains(String name) {
        return categorieRepository.findByNameContaining(name);
    }
}
