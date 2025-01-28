package net.chakir.clientservice.sevice;

import net.chakir.clientservice.entities.Client;
import net.chakir.clientservice.repo.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));
    }

    public List<Client> searchClientsByNom(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setNom(updatedClient.getNom());
            existingClient.setPrenom(updatedClient.getPrenom());
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setTelephone(updatedClient.getTelephone());
            existingClient.setAdresse(updatedClient.getAdresse());
            existingClient.setCodePostal(updatedClient.getCodePostal());
            existingClient.setPays(updatedClient.getPays());
            existingClient.setSex(updatedClient.getSex());
            return clientRepository.save(existingClient);
        } else {
            throw new RuntimeException("Client non trouvé avec l'id: " + id);
        }
    }

    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Client non trouvé avec l'id: " + id);
        }
    }
}
