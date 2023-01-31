package Youcode.project.Repository;

import Youcode.project.Model.Chambre;
import Youcode.project.Model.Etat;
import Youcode.project.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {


    Chambre findChambreById(Long id);
   List<Chambre> findChambresByEtat(Etat etat);

    @Query("SELECT c FROM  Chambre c WHERE c.disponibilite='Disponible'")
    List<Chambre> findChambreDisponnible();

    @Query("SELECT c FROM  Chambre c WHERE c.disponibilite='Indisponible'")
    List<Chambre> findChambreNonDisponnible();


    List<Chambre> findChambresByHotel(Hotel hotel);
}
