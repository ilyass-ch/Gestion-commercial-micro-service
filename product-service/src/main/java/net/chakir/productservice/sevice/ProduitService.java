package net.chakir.productservice.sevice;

import net.chakir.productservice.entities.Produit;
import net.chakir.productservice.repo.ProduitRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService (ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public List<Produit> getProduitByName (String name) {
        return produitRepository.findByName(name);
    }
    public List<Produit> getProduitByNameContains (String name) {
        return produitRepository.findByNameContaining(name);
    }
}
