package net.chakir.marketingservice.web;

import net.chakir.marketingservice.entities.Campagne;
import net.chakir.marketingservice.enums.CompagneStatut;
import net.chakir.marketingservice.sevice.CampagneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/campagnes")
public class CampagneController {
    private final CampagneService campagneService;

    public CampagneController(CampagneService campagneService) {
        this.campagneService = campagneService;
    }

    @GetMapping
    public List<Campagne> getAllCampagnes() {
        return campagneService.getAllCompagnes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campagne> getCampagneById(@PathVariable Long id) {
        return campagneService.getCompagneById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Campagne> getCampagneByNom(@PathVariable String nom) {
        return campagneService.getCompagneByNom(nom)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/statut/{statut}")
    public List<Campagne> getCampagnesByStatut(@PathVariable CompagneStatut statut) {
        return campagneService.getCompagnesByStatut(statut);
    }

    @GetMapping("/before")
    public List<Campagne> getCampagnesBeforeDate(@RequestParam Date date) {
        return campagneService.getCompagnesBeforeDate(date);
    }

    @GetMapping("/after")
    public List<Campagne> getCampagnesAfterDate(@RequestParam Date date) {
        return campagneService.getCompagnesAfterDate(date);
    }

    @GetMapping("/description")
    public List<Campagne> getCampagnesByDescription(@RequestParam String keyword) {
        return campagneService.getCompagnesByDescription(keyword);
    }

    @PostMapping
    public Campagne createCampagne(@RequestBody Campagne campagne) {
        return campagneService.saveCompagne(campagne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campagne> updateCampagne(@PathVariable Long id, @RequestBody Campagne campagne) {
        if (!campagneService.getCompagneById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        campagne.setId(id);
        return ResponseEntity.ok(campagneService.saveCompagne(campagne));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampagne(@PathVariable Long id) {
        if (!campagneService.getCompagneById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        campagneService.deleteCompagne(id);
        return ResponseEntity.noContent().build();
    }
}

