package Youcode.project.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Etat etat;
    private String ville;
    private String name;
    private String description;
    @Transient
    private String message;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Chambre> chambres=new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER)
    private Manager manager;

    public Hotel(Etat etat, String ville, String name, String description, String message) {
        this.etat = etat;
        this.ville = ville;
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public Hotel(Long id, Etat etat, String ville, String name, String description, String message) {
        this.id = id;
        this.etat = etat;
        this.ville = ville;
        this.name = name;
        this.description = description;
        this.message = message;

    }

    public Hotel() {
    }


}
