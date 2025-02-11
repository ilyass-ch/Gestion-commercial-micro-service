package net.chakir.userservice.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users") // Renomme la table pour éviter le mot réservé "user"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName; // Nom d'utilisateur
    private String email; // Email de l'utilisateur
    private String password; // Mot de passe de l'utilisateur
    private String telephone;
    private boolean enabled;
    private String verificationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles; // Liste des rôles associés

    private LocalDateTime dateCreation; // Date de création de l'utilisateur

    // Constructeur par défaut
    public User() {
    }

    // Constructeur avec tous les paramètres
    public User(Long id, String userName, String email, String password,String telephone, List<Role> roles, LocalDateTime dateCreation, boolean enabled, String verificationCode) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
        this.roles = roles;
        this.dateCreation = dateCreation;
    }


    // Getters et setters


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;}

    public void setTelephone(String telephone) {
        this.telephone = telephone;}

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void generateVerificationCode() {
        this.verificationCode = UUID.randomUUID().toString().substring(0, 6);
    }
}
