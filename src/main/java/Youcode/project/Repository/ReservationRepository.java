package Youcode.project.Repository;
import Youcode.project.Model.Chambre;
import Youcode.project.Model.Client;
import Youcode.project.Model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {


    @Query("SELECT r From Reservation  r WHERE r.etat='Active' and r.client=:client")
    List<Reservation> findReservationsByClient(@Param("client") Client client);
    @Query("SELECT r FROM Reservation r WHERE r.chambre=:chambre and r.etat='Active' and ((:dateDebut BETWEEN r.dateDebut and r.dateFin) or (:dateFin BETWEEN r.dateDebut and r.dateFin) or (r.dateFin BETWEEN :dateDebut and :dateFin) or (r.dateDebut BETWEEN :dateDebut and :dateFin))")
    List<Reservation> findReservationByDateAndChambre(@Param( "chambre") Chambre chambre,
                                                      @Param("dateDebut")LocalDate dateDebut,
                                                      @Param("dateFin")LocalDate dateFin);

}
