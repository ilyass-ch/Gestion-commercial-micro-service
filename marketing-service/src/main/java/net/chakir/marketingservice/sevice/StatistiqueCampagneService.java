package net.chakir.marketingservice.sevice;

import net.chakir.marketingservice.entities.StatistiqueCampagne;
import net.chakir.marketingservice.repo.StatistiqueCampagneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatistiqueCampagneService {
    private final StatistiqueCampagneRepository statistiqueCampagneRepository;

    public StatistiqueCampagneService(StatistiqueCampagneRepository statistiqueCampagneRepository) {
        this.statistiqueCampagneRepository = statistiqueCampagneRepository;
    }

    public List<StatistiqueCampagne> getAllStatistiques() {
        return statistiqueCampagneRepository.findAll();
    }

    public Optional<StatistiqueCampagne> getStatistiqueById(Long id) {
        return statistiqueCampagneRepository.findById(id);
    }

    public Optional<StatistiqueCampagne> getStatistiqueByCampagneId(Long campagneId) {
        return statistiqueCampagneRepository.findByCampagneId(campagneId);
    }

    public List<StatistiqueCampagne> getStatistiquesByEmailsEnvoyes(int nombreEmails) {
        return statistiqueCampagneRepository.findByNombreEmailsEnvoyesGreaterThan(nombreEmails);
    }

    public List<StatistiqueCampagne> getStatistiquesByTauxOuverture(double tauxOuverture) {
        return statistiqueCampagneRepository.findByTauxOuvertureGreaterThan(tauxOuverture);
    }

    public List<StatistiqueCampagne> getStatistiquesByTauxConversion(double tauxConversion) {
        return statistiqueCampagneRepository.findByTauxConversionGreaterThan(tauxConversion);
    }

    public StatistiqueCampagne saveStatistique(StatistiqueCampagne statistiqueCampagne) {
        return statistiqueCampagneRepository.save(statistiqueCampagne);
    }

    public void deleteStatistique(Long id) {
        statistiqueCampagneRepository.deleteById(id);
    }
}
