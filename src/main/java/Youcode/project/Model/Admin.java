package Youcode.project.Model;


import lombok.Data;

import javax.persistence.Entity;



@Entity
@Data

public class Admin extends User{


    public Admin(String firstName,
                 String lastName,
                 String phone,
                 String address,
                 String email,
                 String password,
                 String message) {
        super(firstName, lastName, phone, address, email, password, message);
    }

    public Admin()
    {

    }


}
