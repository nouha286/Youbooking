package Youcode.project.Repository;


import Youcode.project.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    @Query("SELECT m FROM  Manager m WHERE m.etat='Active'")
    List<Manager> findManagersActive();

    @Query("SELECT m FROM  Manager m WHERE m.etat='Desactive'")
    List<Manager> findManagersDesactive();
}
