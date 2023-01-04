package Youcode.project.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class Manager extends User{
    private String UUID;

    @OneToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    public Manager(String firstName,
                   String lastName,
                   String phone,
                   String address,
                   String email,
                   String password,
                   String message,
                   String UUID) {
        super(firstName, lastName, phone, address, email, password, message);
        this.UUID = UUID;
    }

    public Manager() {

    }
}
