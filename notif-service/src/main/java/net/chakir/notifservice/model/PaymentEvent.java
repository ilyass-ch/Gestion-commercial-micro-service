package net.chakir.notifservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentEvent {
    private Long id;  // Ajoutez le champ id ici
    private Long commandeId;
    private Long clientId;
    private String clientEmail;
    private String clientName;
    private String methodPaiement;
    private String statut;
    private Double montant;
    private String date;
    private String codePromo;
    private Double reduction;
    private Client client;  // Le client est un objet dans cet événement
    private CodePromoDetails codePromoDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
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

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public PaymentEvent(Long id, Long commandeId, Long clientId, String clientEmail, String clientName, String methodPaiement, String statut, Double montant, String date, String codePromo, Double reduction, Client client, CodePromoDetails codePromoDetails) {
        this.id = id;
        this.commandeId = commandeId;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.methodPaiement = methodPaiement;
        this.statut = statut;
        this.montant = montant;
        this.date = date;
        this.codePromo = codePromo;
        this.reduction = reduction;
        this.client = client;
        this.codePromoDetails = codePromoDetails;
    }

    public PaymentEvent() {
    }

}
