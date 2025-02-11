package net.chakir.notifservice.web;

import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.sevice.ClientService;
import net.chakir.notifservice.sevice.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, ClientService clientService) {
        this.userService = userService;
    }

    public List<Notification> getNotifications(@PathVariable("userId") Long usertId){
        return userService.getNotificationsByUSerId(usertId);
    }
}
