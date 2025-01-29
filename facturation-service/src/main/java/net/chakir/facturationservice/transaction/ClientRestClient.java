package net.chakir.facturationservice.transaction;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.chakir.facturationservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestClient {
    @GetMapping("clients/{id}")
    @CircuitBreaker(name = "client-Service", fallbackMethod = "getDefaultClient")
    Client getClient(@PathVariable("id") Long id);

    @GetMapping("/clients")
    List<Client> allClients ();

    default Client getDefaultClient (Long id, Exception e) {
        Client client = new Client();
        client.setId(id);
        client.setNom("NOT AVAILABLE");
        client.setPrenom("NOT AVAILABLE");
        client.setEmail("NOT AVAILABLE");
        client.setTelephone("NOT AVAILABLE");
        client.setAdresse("NOT AVAILABLE");
        return client;
    }
}
