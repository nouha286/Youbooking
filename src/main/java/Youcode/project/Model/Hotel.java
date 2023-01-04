package Youcode.project.Model;

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
    private String message;
    @OneToMany
    private List<Chambre> chambres=new ArrayList<>();
    @OneToOne
    private Manager manager;

    public Hotel(Etat etat, String ville, String name, String description, String message) {
        this.etat = etat;
        this.ville = ville;
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public Hotel() {
    }
}
