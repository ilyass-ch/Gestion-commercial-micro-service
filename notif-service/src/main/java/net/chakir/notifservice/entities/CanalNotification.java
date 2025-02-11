package net.chakir.notifservice.entities;

public class CanalNotification {
    private Long id;
    private String nom;           // Nom du canal (par exemple, Email, SMS, Push)
    private String description;   // Description du canal



    public CanalNotification() {
    }

    public CanalNotification(Long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
