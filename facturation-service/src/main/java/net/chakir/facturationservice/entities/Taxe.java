package net.chakir.facturationservice.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Taxe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double pourcentage;
    private String description;
    @ManyToMany(mappedBy = "taxes")
    private List<Facture> factures;

    public Taxe() {
    }

    public Taxe(Long id, String name, Double pourcentage, String description, List<Facture> factures) {
        this.id = id;
        this.name = name;
        this.pourcentage = pourcentage;
        this.description = description;
        this.factures = factures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
}
