package Youcode.project.Controller;

import Youcode.project.Model.Reservation;
import Youcode.project.Service.ChambreService;
import Youcode.project.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class testController {

    @Autowired
   ReservationService reservationService;
    @Autowired
    ChambreService chambreService;

    @GetMapping("/test")
    Reservation getReservation()
    {
        return reservationService.getReservation(chambreService.getOneChambre(6L).get(), LocalDate.parse("2023-01-06"),LocalDate.parse("2023-01-10"));
    }
}
