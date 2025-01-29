package net.chakir.marketingservice.sevice;

import net.chakir.marketingservice.entities.CodePromo;
import net.chakir.marketingservice.enums.CodePromoStatut;
import net.chakir.marketingservice.repo.CodePromoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CodePromoService {
    private final CodePromoRepository codePromoRepository;

    public CodePromoService(CodePromoRepository codePromoRepository) {
        this.codePromoRepository = codePromoRepository;
    }

    public List<CodePromo> getAllCodePromos() {
        return codePromoRepository.findAll();
    }

    public Optional<CodePromo> getCodePromoById(Long id) {
        return codePromoRepository.findById(id);
    }

    public Optional<CodePromo> getCodePromoByCode(String code) {
        return codePromoRepository.findByCode(code);
    }

    public List<CodePromo> getCodePromosByStatut(CodePromoStatut statut) {
        return codePromoRepository.findByCodePromoStatut(statut);
    }

    public List<CodePromo> getExpiredCodePromos(Date date) {
        return codePromoRepository.findByDateExpirationBefore(date);
    }

    public List<CodePromo> getCodePromosByMinReduction(Double amount) {
        return codePromoRepository.findByMontantReductionGreaterThan(amount);
    }

    public CodePromo saveCodePromo(CodePromo codePromo) {
        return codePromoRepository.save(codePromo);
    }

    public void deleteCodePromo(Long id) {
        codePromoRepository.deleteById(id);
    }

    public List<CodePromo> getByDescriptionContaining (String description) {
        return codePromoRepository.findByDescriptionContaining(description);
    }
}
