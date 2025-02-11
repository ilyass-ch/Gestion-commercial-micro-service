package net.chakir.notifservice.web;

import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.sevice.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/notifications")
    public List<Notification> getNotifications(@PathVariable("clientId") Long clientId) {
        return clientService.getNotificationsByClientId(clientId);
    }

}
