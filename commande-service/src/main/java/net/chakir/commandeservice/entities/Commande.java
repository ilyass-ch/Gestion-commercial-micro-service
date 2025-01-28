package net.chakir.commandeservice.entities;

import jakarta.persistence.*;
import net.chakir.commandeservice.model.Client;


import java.time.LocalDateTime;

@Entity
public class Commande {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long clientId;
        private String statut;
        private double montantTotal;
        private LocalDateTime dateCreation;
        @Transient
        private Client client;

    public Commande() {}

    public Commande(Long id, Long clientId, String statut, double montantTotal, LocalDateTime dateCreation, Client client) {
        this.id = id;
        this.clientId = clientId;
        this.statut = statut;
        this.montantTotal = montantTotal;
        this.dateCreation = dateCreation;
        this.client = client;
    }

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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
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
}
