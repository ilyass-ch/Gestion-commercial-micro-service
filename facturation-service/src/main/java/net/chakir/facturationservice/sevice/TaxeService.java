package net.chakir.facturationservice.sevice;

import jakarta.persistence.EntityNotFoundException;
import net.chakir.facturationservice.entities.Taxe;
import net.chakir.facturationservice.repo.TaxeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxeService {
    private final TaxeRepository taxeRepository;

    public TaxeService(TaxeRepository taxeRepository) {
        this.taxeRepository = taxeRepository;

    }

    public Taxe ajouterTaxe(Taxe taxe) {
        return taxeRepository.save(taxe);
    }

    public List<Taxe> obtenirTaxes() {
        return taxeRepository.findAll();
    }

    public Taxe getTaxeById(Long id) {
        return taxeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taxe avec l'id " + id + " non trouvée"));
    }
    public void deleteTaxe(Long id) {
        if (!taxeRepository.existsById(id)) {
            throw new EntityNotFoundException("Taxe with ID " + id + " not found");
        }
        taxeRepository.deleteById(id);
    }
    public Taxe updateTaxe(Long id, Taxe updatedTaxe) {
        return taxeRepository.findById(id)
                .map(existingTaxe -> {
                    existingTaxe.setName(updatedTaxe.getName());
                    existingTaxe.setPourcentage(updatedTaxe.getPourcentage());
                    existingTaxe.setDescription(updatedTaxe.getDescription());
                    return taxeRepository.save(existingTaxe);
                })
                .orElseThrow(() -> new EntityNotFoundException("Taxe avec l'ID " + id + " non trouvée"));
    }

    public Taxe getTaxeByName(String name) {
        return taxeRepository.findByName(name);
    }

    public Taxe getTaxeByPourcentage(double p) {
        return taxeRepository.findByPourcentage(p);
    }

}
