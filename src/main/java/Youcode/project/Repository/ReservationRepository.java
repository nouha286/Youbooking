package Youcode.project.Repository;
import Youcode.project.Model.Chambre;
import Youcode.project.Model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {


    @Query("SELECT r FROM Reservation r WHERE r.chambre=:chambre and r.etat='Active' and (:dateDebut BETWEEN r.dateDebut and r.dateFin) or (:dateFin BETWEEN r.dateDebut and r.dateFin)")
    Reservation findReservationByDateAndChambre(@Param( "chambre") Chambre chambre,
                                                @Param("dateDebut")LocalDate dateDebut,
                                                @Param("dateFin")LocalDate dateFin);

}
