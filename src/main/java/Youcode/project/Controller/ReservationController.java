package Youcode.project.Controller;

import Youcode.project.Dto.ReservationChambre;
import Youcode.project.Model.Reservation;
import Youcode.project.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/Reservations")
    @ResponseBody
    public List<Reservation> getReservation()
    {
        return reservationService.getReservations();
    }

    @GetMapping("/AllReservationsOfClient/{id}")
    @ResponseBody
    public List<Reservation> getReservationsByClient(@PathVariable("id") Long id)
    {
        return reservationService.getReservationsByClient(id);
    }

    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody ReservationChambre reservation)
    {
       return reservationService.addReservation(reservation);
    }

    @PutMapping("MisAjour")
    public  void retirerReservationExpere()
    {
        reservationService.refreshBooking();
    }
}
