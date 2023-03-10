package Youcode.project.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Etat etat;
    private LocalDate createdAt;
    private Double FraisTotal;
    @Transient
    private String message;

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Etat etat, LocalDate createdAt, String message) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.createdAt = createdAt;
        this.message = message;
    }
    public Reservation()
    {

    }
}
