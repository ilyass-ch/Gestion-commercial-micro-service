package net.chakir.paimentservice.entities;

import jakarta.persistence.*;
import net.chakir.paimentservice.model.Client;

import java.time.LocalDate;

@Entity
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long paiementId;
    private Long clientId;
    private Double montant;
    private LocalDate date;
    private String motif;

    @Transient
    private Client client;

    public Remboursement(Long id, Long paiementId, Long clientId, Double montant, LocalDate date, String motif, Client client) {
        this.id = id;
        this.paiementId = paiementId;
        this.clientId = clientId;
        this.montant = montant;
        this.date = date;
        this.motif = motif;
        this.client = client;
    }

    public Remboursement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(Long paiementId) {
        this.paiementId = paiementId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
