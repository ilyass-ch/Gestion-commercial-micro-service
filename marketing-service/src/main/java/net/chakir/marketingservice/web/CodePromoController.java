package net.chakir.marketingservice.web;

import net.chakir.marketingservice.entities.CodePromo;
import net.chakir.marketingservice.enums.CodePromoStatut;
import net.chakir.marketingservice.sevice.CodePromoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/code-promos")
public class CodePromoController {
    private final CodePromoService codePromoService;

    public CodePromoController(CodePromoService codePromoService) {
        this.codePromoService = codePromoService;
    }

    @GetMapping
    public List<CodePromo> getAllCodePromos() {
        return codePromoService.getAllCodePromos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodePromo> getCodePromoById(@PathVariable Long id) {
        return codePromoService.getCodePromoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CodePromo> getCodePromoByCode(@PathVariable String code) {
        return codePromoService.getCodePromoByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/statut/{statut}")
    public List<CodePromo> getCodePromosByStatut(@PathVariable CodePromoStatut statut) {
        return codePromoService.getCodePromosByStatut(statut);
    }

    @GetMapping("/expired")
    public List<CodePromo> getExpiredCodePromos(@RequestParam Date date) {
        return codePromoService.getExpiredCodePromos(date);
    }

    @GetMapping("/description")
    public List<CodePromo> getByDescriptionContaining(@RequestParam String description) {
        return codePromoService.getByDescriptionContaining(description);
    }

    @PostMapping
    public CodePromo createCodePromo(@RequestBody CodePromo codePromo) {
        return codePromoService.saveCodePromo(codePromo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CodePromo> updateCodePromo(@PathVariable Long id, @RequestBody CodePromo codePromo) {
        if (!codePromoService.getCodePromoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        codePromo.setId(id);
        return ResponseEntity.ok(codePromoService.saveCodePromo(codePromo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodePromo(@PathVariable Long id) {
        if (codePromoService.getCodePromoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        codePromoService.deleteCodePromo(id);
        return ResponseEntity.noContent().build();
    }

}

