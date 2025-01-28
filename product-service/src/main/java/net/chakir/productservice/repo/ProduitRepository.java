package net.chakir.productservice.repo;

import net.chakir.productservice.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByName(String name);
    List<Produit> findByNameContaining(String name);
}
