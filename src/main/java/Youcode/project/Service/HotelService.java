package Youcode.project.Service;

import Youcode.project.Model.Hotel;
import Youcode.project.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public Optional<Hotel> getHotelById(Long id)
    {
        return hotelRepository.findHotelById(id);
    }
}
