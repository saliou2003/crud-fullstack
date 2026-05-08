package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateCommande = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private StatutCommande statut;
    //Une commande appartient à un seul client.
    @ManyToOne
    //Crée la colonne clé étrangère(client_id) dans la table Commande.
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Personne client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<CommandeProduit> lignes = new ArrayList<>();

    //Getters et Setters
    public Personne getClient() {
        return client;
    }

    public List<CommandeProduit> getLignes() {
        return lignes;
    }

    public void setLignes(List<CommandeProduit> lignes) {
        this.lignes = lignes;
    }

    public void setClient(Personne client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }


    public Double getMontantTotal() {

        /*double total = 0;
        for (CommandeProduit l : lignes) {
            total += l.getQuantite() * l.getPrixUnitaire();
        }
        return total;*/

        return lignes.stream()
                .mapToDouble(l -> l.getQuantite() * l.getPrixUnitaire())
                .sum();
    }
}
