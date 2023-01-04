package Youcode.project.Model;

import Youcode.project.Dto.Message;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private Etat etat;
    private String ville;
    private String name;
    private String description;
    @Transient
    private Message message;
    @OneToMany
    private List<Chambre> chambres=new ArrayList<>();
    @ManyToMany
    private List<Manager> manager;

    public Hotel(Etat etat, String ville, String name, String description, Message message) {
        this.etat = etat;
        this.ville = ville;
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public Hotel() {
    }
}
