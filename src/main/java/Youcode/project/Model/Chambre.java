package Youcode.project.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;
    private String categorie;
    private Etat etat;
    private String description;
    private Double fraiParNuit;
    private Disponibilite disponibilite;

    @Transient
    private String message;

    public Chambre(String categorie, Etat etat, String description, Double fraiParNuit, Disponibilite disponibilite, String message) {
        this.categorie = categorie;
        this.etat = etat;
        this.description = description;
        this.fraiParNuit = fraiParNuit;
        this.disponibilite = disponibilite;
        this.message = message;
    }

    public Chambre() {
    }
}
