package Youcode.project.Config;

import Youcode.project.Model.*;
import Youcode.project.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner commandLineRunner (RoleRepository roleRepository){
        return args -> {
            Role role =new Role("CLIENT");
            Role role1=new Role("MANAGER");
            Role role2=new Role("ADMIN");

            roleRepository.saveAll ( List.of ( role, role1, role2 ));



        };
    }
}
