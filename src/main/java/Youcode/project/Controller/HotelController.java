package Youcode.project.Controller;


import Youcode.project.Dto.HotelWithManager;
import Youcode.project.Model.Client;
import Youcode.project.Model.Hotel;
import Youcode.project.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/Hotels")
    public List<Hotel> getHotels()
    {
       return hotelService.getHotelsActive();
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
    public Hotel updateHotel(HotelWithManager hotel)
    {
        return hotelService.updateHotel(hotel);
    }

    @PutMapping("/restoreHotel")
    public void ActiverHotel(Long id)
    {
        hotelService.restore(id);
    }

    @PutMapping("/deleteHotel")
    public void DesctiverHotel(Long id)
    {
        hotelService.delete(id);

    }

}
