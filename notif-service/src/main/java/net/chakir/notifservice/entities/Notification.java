package net.chakir.notifservice.entities;

import net.chakir.notifservice.model.Client;
import net.chakir.notifservice.model.CodePromoDetails;
import net.chakir.notifservice.model.User;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private Long commandeId;
    private Long clientId;
    private String clientEmail;
    private Long userId;
    private String type;  // Type de notification (ex: "Email", "SMS")
    private String contenu;
    private Double montant;
    private String methodPaiement;
    private String statut;
    private LocalDateTime date;
    private String codePromo;
    private Double reduction;
    private Long canalNotificationId;

    private User user;
    private Client client;  // L'email du client est dans cet objet Client
    private CodePromoDetails codePromoDetails;


    public Notification() {
    }

    public Notification(Long id, Long commandeId, Long clientId,String clientEmail,String type, String contenu, Double montant, String methodPaiement, String statut, LocalDateTime date, String codePromo, Double reduction,Long canalNotificationId ,User user, Client client, CodePromoDetails codePromoDetails) {
        this.id = id;
        this.commandeId = commandeId;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.type = type;
        this.contenu = contenu;
        this.montant = montant;
        this.methodPaiement = methodPaiement;
        this.statut = statut;
        this.date = date;
        this.codePromo = codePromo;
        this.reduction = reduction;
        this.canalNotificationId = canalNotificationId;
        this.user = user;
        this.client = client;
        this.codePromoDetails = codePromoDetails;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Long getCanalNotificationId() {
        return canalNotificationId;
    }

    public void setCanalNotificationId(Long canalNotificationId) {
        this.canalNotificationId = canalNotificationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getMethodPaiement() {
        return methodPaiement;
    }

    public void setMethodPaiement(String methodPaiement) {
        this.methodPaiement = methodPaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CodePromoDetails getCodePromoDetails() {
        return codePromoDetails;
    }

    public void setCodePromoDetails(CodePromoDetails codePromoDetails) {
        this.codePromoDetails = codePromoDetails;
    }

    public String getClientEmail() {
        // Renvoie l'email du client à partir de l'objet Client
        return (client != null) ? client.getEmail() : null;
    }


}

