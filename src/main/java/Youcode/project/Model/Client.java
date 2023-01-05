package Youcode.project.Model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table
public class Client extends User {

    public Client(String firstName,
                  String lastName,
                  String phone,
                  String address,
                  String email,
                  String password,
                  String message,
                  String CNE) {
        super(firstName, lastName, phone, address, email, password, message);
        this.CNE = CNE;
    }

    private String CNE;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Reservation> reservation;



    public Client() {

    }
}
