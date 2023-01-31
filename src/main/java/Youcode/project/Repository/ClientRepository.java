package Youcode.project.Repository;

import Youcode.project.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findClientById(Long id);
    @Query("SELECT c FROM Client c WHERE c.etat='Active'")
    List<Client> findClientsActive();
    @Query("SELECT c FROM Client c WHERE c.etat='Desactive'")
    List<Client> findClientsDesactive();
}
