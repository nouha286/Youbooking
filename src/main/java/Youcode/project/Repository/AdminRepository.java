package Youcode.project.Repository;


import Youcode.project.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query("SELECT a FROM Admin a WHERE a.etat='Active' ")
    List<Admin> findAdminsActive();

    @Query("SELECT a FROM Admin a WHERE a.etat='Desactive' ")
    List<Admin> findAdminsDesactive();
}
