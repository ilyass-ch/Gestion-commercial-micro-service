package net.chakir.marketingservice.web;

import net.chakir.marketingservice.entities.StatistiqueCampagne;
import net.chakir.marketingservice.sevice.StatistiqueCampagneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistiques-campagne")
public class StatistiqueCampagneController {
    private final StatistiqueCampagneService statistiqueCampagneService;

    public StatistiqueCampagneController(StatistiqueCampagneService statistiqueCampagneService) {
        this.statistiqueCampagneService = statistiqueCampagneService;
    }

    @GetMapping
    public List<StatistiqueCampagne> getAllStatistiques() {
        return statistiqueCampagneService.getAllStatistiques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatistiqueCampagne> getStatistiqueById(@PathVariable Long id) {
        return statistiqueCampagneService.getStatistiqueById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/campagne/{campagneId}")
    public ResponseEntity<StatistiqueCampagne> getStatistiqueByCampagneId(@PathVariable Long campagneId) {
        return statistiqueCampagneService.getStatistiqueByCampagneId(campagneId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/emails-envoyes")
    public List<StatistiqueCampagne> getStatistiquesByEmailsEnvoyes(@RequestParam int nombreEmails) {
        return statistiqueCampagneService.getStatistiquesByEmailsEnvoyes(nombreEmails);
    }

    @GetMapping("/taux-ouverture")
    public List<StatistiqueCampagne> getStatistiquesByTauxOuverture(@RequestParam double tauxOuverture) {
        return statistiqueCampagneService.getStatistiquesByTauxOuverture(tauxOuverture);
    }

    @GetMapping("/taux-conversion")
    public List<StatistiqueCampagne> getStatistiquesByTauxConversion(@RequestParam double tauxConversion) {
        return statistiqueCampagneService.getStatistiquesByTauxConversion(tauxConversion);
    }

    @PostMapping
    public StatistiqueCampagne createStatistique(@RequestBody StatistiqueCampagne statistiqueCampagne) {
        return statistiqueCampagneService.saveStatistique(statistiqueCampagne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatistiqueCampagne> updateStatistique(@PathVariable Long id, @RequestBody StatistiqueCampagne statistiqueCampagne) {
        if (!statistiqueCampagneService.getStatistiqueById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        statistiqueCampagne.setId(id);
        return ResponseEntity.ok(statistiqueCampagneService.saveStatistique(statistiqueCampagne));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistique(@PathVariable Long id) {
        if (!statistiqueCampagneService.getStatistiqueById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        statistiqueCampagneService.deleteStatistique(id);
        return ResponseEntity.noContent().build();
    }
}
