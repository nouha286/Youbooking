package Youcode.project.Model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data

@Inheritance(strategy=InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String message;

    public User(String firstName, String lastName, String phone, String address, String email, String password, String message) {
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
