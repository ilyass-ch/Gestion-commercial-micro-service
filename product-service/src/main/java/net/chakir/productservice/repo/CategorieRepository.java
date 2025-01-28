package net.chakir.productservice.repo;

import net.chakir.productservice.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByName(String name);
    List<Categorie> findByNameContaining(String name);
}
