package Youcode.project.Repository;


import Youcode.project.Model.Etat;
import Youcode.project.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    List<Manager> findManagersByEtat(Etat etat);







    Optional<Manager> findManagerById(Long id);
}
