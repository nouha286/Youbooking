package Youcode.project.Model;


import Youcode.project.Dto.Message;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class Manager extends User{
    private String UUID;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Hotel> hotels;

    public Manager(String firstName,
                   String lastName,
                   String phone,
                   String address,
                   String email,
                   String password,
                   Message message,
                   String UUID) {
        super(firstName, lastName, phone, address, email, password, message);
        this.UUID = UUID;
    }

    public Manager() {

    }
}
