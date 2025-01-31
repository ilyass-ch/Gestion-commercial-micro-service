package net.chakir.notifservice.entities;

import java.time.LocalDate;

public class Notification {

        private Long id;
        private Long utilisateurId;    // ID de l'utilisateur auquel la notification est envoyée

        private String type;           // Type de notification (par exemple, email, SMS, push)
        private String contenu;        // Contenu de la notification
        private LocalDate date;        // Date de la notification
        private String statut;         // Statut de la notification (Envoyée, En attente, Erreur)

        // Constructeurs, getters et setters

        public Notification() {
        }

        public Notification(Long id, Long utilisateurId, String type, String contenu, LocalDate date, String statut) {
            this.id = id;
            this.utilisateurId = utilisateurId;
            this.type = type;
            this.contenu = contenu;
            this.date = date;
            this.statut = statut;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUtilisateurId() {
            return utilisateurId;
        }

        public void setUtilisateurId(Long utilisateurId) {
            this.utilisateurId = utilisateurId;
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

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }
    }


