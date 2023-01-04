package Youcode.project.Repository;

import Youcode.project.Model.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {


    @Query("SELECT c FROM Chambre c WHERE c.etat='Active'")
    List<Chambre> findChambresActive();

    @Query("SELECT c FROM Chambre c WHERE c.etat='Desactive'")
    List<Chambre> findChambresDesactive();

    @Query("SELECT c FROM  Chambre c WHERE c.disponibilite='Disponible'")
    List<Chambre> findChambreDisponnible();

    @Query("SELECT c FROM  Chambre c WHERE c.disponibilite='Indisponible'")
    List<Chambre> findChambreNonDisponnible();
}
