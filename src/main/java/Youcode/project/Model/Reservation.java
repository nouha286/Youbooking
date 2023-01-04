package Youcode.project.Model;

import Youcode.project.Dto.Message;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Etat etat;
    private LocalDate createdAt;
    @Transient
    private Message message;

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Etat etat, LocalDate createdAt, Message message) {
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
