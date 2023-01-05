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
