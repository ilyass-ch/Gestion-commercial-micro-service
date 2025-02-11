package net.chakir.notifservice.model;



import java.time.LocalDateTime;

public class CommandeEvent {

    private Long id;
    private Long clientId;
    private double montantTotal;
    private String statut;
    private LocalDateTime dateCreation;
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CommandeEvent(Long id, Long clientId, double montantTotal,String statut, LocalDateTime dateCreation, Client client) {
        this.id = id;
        this.clientId = clientId;
        this.montantTotal = montantTotal;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.client = client;
    }

    public CommandeEvent() {
    }
}
