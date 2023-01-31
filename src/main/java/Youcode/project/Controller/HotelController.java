package Youcode.project.Controller;


import Youcode.project.Dto.HotelWithManager;
import Youcode.project.Model.Client;
import Youcode.project.Model.Hotel;
import Youcode.project.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/Hotels")
    public List<Hotel> getHotels()
    {
       return hotelService.getHotelsActive();
    }

    @GetMapping("/Hotel/{id}")
    public Optional<Hotel> getHotel(@PathVariable("id") Long id)
    {
        return hotelService.getHotelById(id);
    }
    @GetMapping("/Villes")
    public List<String> getVille()
    {
        return hotelService.getAllVille();
    }

    @GetMapping(path = "/HotelsByVilles/{ville}")
    public List<Hotel> getHotelsByVille(@PathVariable("ville") String ville)
    {
        return hotelService.getHotelsByVille(ville);
    }

    @GetMapping("/HotelsDeleted")
    public List<Hotel> getHotelsDesactive()
    {
        return hotelService.getHotelsDesactive();
    }

    @PostMapping("/addHotel")
    public Hotel saveHotel(@RequestBody HotelWithManager hotel)
    {
        return hotelService.saveHotel(hotel);
    }



    @PostMapping("/updateHotel")
    public Hotel updateHotel(@RequestBody HotelWithManager hotel)
    {
        return hotelService.updateHotel(hotel);
    }

    @PutMapping("/restoreHotel")
    public Hotel ActiverHotel(@RequestBody Long id)
    {

        return hotelService.restore(id);
    }

    @PutMapping ("/deleteHotel")
    public Hotel DesctiverHotel(@RequestBody Long id)
    {
       return hotelService.delete(id);

    }

}
