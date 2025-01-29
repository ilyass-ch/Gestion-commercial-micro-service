package net.chakir.facturationservice.repo;

import net.chakir.facturationservice.entities.Taxe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxeRepository extends JpaRepository<Taxe, Long> {
    Taxe findByName(String name);
    Taxe findByPourcentage(double pourcentage);
}
