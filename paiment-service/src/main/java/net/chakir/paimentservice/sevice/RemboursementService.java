package net.chakir.paimentservice.sevice;

import jakarta.persistence.EntityNotFoundException;
import net.chakir.paimentservice.entities.Remboursement;
import net.chakir.paimentservice.model.Client;
import net.chakir.paimentservice.repo.RemboursementRepository;
import net.chakir.paimentservice.transaction.ClientRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final ClientRestClient clientRestClient;

    public RemboursementService( RemboursementRepository remboursementRepository, ClientRestClient clientRestClient ) {
        this.remboursementRepository = remboursementRepository;
        this.clientRestClient = clientRestClient;
    }


    public Remboursement createRemboursement(Remboursement remboursement) {
        return remboursementRepository.save(remboursement);
    }
    public Remboursement getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.getRemboursementById(id);
        Client client = clientRestClient.getClient(remboursement.getClientId());
        remboursement.setClient(client);
        return remboursement;
    }

    public List<Remboursement> getAllRemboursements() {
        List<Remboursement> remboursementList = remboursementRepository.findAll();
        remboursementList.forEach(remboursement -> {
            Client client = clientRestClient.getClient(remboursement.getClientId());
            remboursement.setClient(client);
        });
        return remboursementList;
    }

    public List<Remboursement> getRemboursementsByClientId(Long clientId) {
        List<Remboursement> remboursements = remboursementRepository.getRemboursementByClientId(clientId);
        remboursements.forEach(remboursement -> {
            Client client = clientRestClient.getClient(remboursement.getClientId());
            remboursement.setClient(client);
        });
        return remboursements;

    }

    public List<Remboursement> getRemboursementsByPaiementId(Long patientId) {
        List<Remboursement> remboursements = remboursementRepository.getRemboursementByPaiementId(patientId);
        remboursements.forEach(remboursement -> {
            Client client = clientRestClient.getClient(remboursement.getClientId());
            remboursement.setClient(client);
        });
        return remboursements;
    }

    public Remboursement updateRemboursement(Long id, Remboursement remboursementDetails) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Remboursement with ID " + id + " not found"));

        remboursement.setMontant(remboursementDetails.getMontant());
        remboursement.setDate(remboursementDetails.getDate());
        remboursement.setClientId(remboursementDetails.getClientId());
        remboursement.setPaiementId(remboursementDetails.getPaiementId());
        remboursement.setMotif(remboursementDetails.getMotif());

        return remboursementRepository.save(remboursement);
    }

    public void deleteRemboursement(Long id) {
        if (!remboursementRepository.existsById(id)) {
            throw new EntityNotFoundException("Remboursement with ID " + id + " not found");
        }
        remboursementRepository.deleteById(id);
    }





}
