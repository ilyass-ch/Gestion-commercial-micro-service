package net.chakir.paimentservice.transaction;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.chakir.paimentservice.model.Client;
import net.chakir.paimentservice.model.CodePromo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MARKETING-SERVICE")
public interface CodePromoRestClient {
    @GetMapping("code-promos/{id}")
    @CircuitBreaker(name = "marketing-Service", fallbackMethod = "getDefaultCodePromo")
    CodePromo getCodePromoById(@PathVariable("id") Long id);

    @GetMapping("/code-promos")
    List<CodePromo> getAllCodePromos();

    default CodePromo getDefaultCodePromo (Long id, Exception e) {
        CodePromo codePromo = new CodePromo();
            codePromo.setId(id);
            codePromo.setCode("");
            codePromo.setDescription("");
            codePromo.setCodePromoStatut(null);
            return codePromo;
    }
}
