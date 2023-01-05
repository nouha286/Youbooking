package Youcode.project.Service;

import Youcode.project.Dto.ReservationChambre;
import Youcode.project.Model.Chambre;
import Youcode.project.Model.Disponibilite;
import Youcode.project.Model.Etat;
import Youcode.project.Model.Reservation;
import Youcode.project.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ChambreService chambreService;


    public List<Reservation> getReservations()
    {
        return reservationRepository.findAll();
    }
    public  Reservation getReservation(Chambre chambre, LocalDate dateDebut, LocalDate dateFin)
    {
      return   reservationRepository.findReservationByDateAndChambre(chambre,dateDebut,dateFin);
    }

    public Reservation addReservation(ReservationChambre reservation)
    {
        Optional<Chambre> chambre=chambreService.getOneChambre(reservation.getChambreId());
        if(reservation.getChambreId()!=null &&
                reservation.getClientId()!=null &&
                reservation.getDateFin()!=null &&
                reservation.getDateDebut()!=null &&
                LocalDate.parse( reservation.getDateDebut()).isAfter(LocalDate.now()) &&
                LocalDate.parse( reservation.getDateFin()).isAfter(LocalDate.now()) &&
                LocalDate.parse( reservation.getDateFin()).isAfter( LocalDate.parse(reservation.getDateDebut())) &&
                reservationRepository.findReservationByDateAndChambre(chambre.get(), LocalDate.parse(reservation.getDateDebut()), LocalDate.parse(reservation.getDateFin()))==null &&
                chambre.isPresent() )
        {
            Reservation reservationSaved=new Reservation( LocalDate.parse(reservation.getDateDebut()),
                    LocalDate.parse(reservation.getDateFin()),
                    Etat.Active,
                    LocalDate.now(),
                    "success");
                    Integer period = Period.between( LocalDate.parse(reservation.getDateDebut()),  LocalDate.parse(reservation.getDateFin())).getDays();
                    Double FraisParNuit=chambre.get().getFraiParNuit();
                    reservationSaved.setFraisTotal(FraisParNuit * period);
                    reservationSaved.setChambre(chambre.get());
                    chambreService.MakeRoomsUnavailable(chambre.get().getId());
                    return reservationRepository.save(reservationSaved);
       }

        Reservation reservation1=new Reservation();
        reservation1.setMessage("failed");
        return reservation1;
    }

    @Transactional
    public void refreshBooking()
    {
        this.getReservations().forEach(reservation -> {if (reservation.getDateFin().isBefore(LocalDate.now()))
        {
            Chambre chambre=reservation.getChambre();
            chambre.setDisponibilite(Disponibilite.Disponible);
            reservation.setEtat(Etat.Desactive);
            reservationRepository.save(reservation);
        }});

    }
}
