package Youcode.project.Model;


import Youcode.project.Dto.Message;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data

@Inheritance(strategy=InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    private Role role;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private Etat etat;
    private String password;
    @Transient
    private Message message;

    public User(String firstName, String lastName, String phone, String address, String email, String password, Message message) {
        firstName = firstName;
        lastName = lastName;
        phone = phone;
        address = address;
        email = email;
        this.password = password;
        this.message = message;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", Phone='" + phone + '\'' +
                ", Address='" + address + '\'' +
                ", Email='" + email + '\'' +
                ", etat=" + etat +
                ", password='" + password + '\'' +
                ", message=" + message +
                '}';
    }
}
