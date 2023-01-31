package Youcode.project.Service;

import Youcode.project.Dto.HotelWithManager;
import Youcode.project.Model.Chambre;
import Youcode.project.Model.Etat;
import Youcode.project.Model.Hotel;
import Youcode.project.Model.Manager;
import Youcode.project.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ManagerService managerService;

    public Optional<Hotel> getHotelById(Long id)
    {
        return hotelRepository.findHotelById(id);
    }

    public List<Hotel> getHotelsActive()
    {
        return hotelRepository.findHotelsByEtat(Etat.Active);
    }

    public List<Hotel> getHotelsDesactive()
    {
        return hotelRepository.findHotelsByEtat(Etat.Desactive);
    }

    public Hotel saveHotel(HotelWithManager hotel)
    {
        Optional<Manager> manager=managerService.findOneManager(hotel.getManagerId());

        if (hotel.getDescription()!=null &&
            hotel.getName()!=null &&
            hotel.getVille()!=null &&
            manager.isPresent())
        {
            Hotel hotelSaved=new Hotel(Etat.Active,
                    hotel.getVille(),
                    hotel.getName(),
                    hotel.getDescription(),
                    "success"
            );


           hotelSaved.setManager(manager.get());
           return hotelRepository.save(hotelSaved);
        }
        Hotel hotel1=new Hotel();
        hotel1.setMessage("failed");
        return  hotel1;
    }


    public Hotel updateHotel(HotelWithManager hotel)
    {
        Optional<Hotel> hotelUpdated=hotelRepository.findHotelById(hotel.getId());
        Optional<Manager> manager=managerService.findOneManager(hotel.getManagerId());
        if (hotelUpdated.isPresent())
        {
            if (hotel.getName()!=null)
            {
                hotelUpdated.get().setName(hotel.getName());
            }
            if (hotel.getDescription()!=null)
            {
                hotelUpdated.get().setDescription(hotelUpdated.get().getDescription());
            }
            if (hotel.getVille()!=null)
            {
                hotelUpdated.get().setVille(hotel.getVille());
            }
            if(hotel.getManagerId()!=null && hotelUpdated.get().getManager().getId()!=hotel.getManagerId() && manager.isPresent())
            {

                hotelUpdated.get().setManager(manager.get());
            }
            hotelUpdated.get().setMessage("success");
            return hotelRepository.save(hotelUpdated.get());
        }
        Hotel hotel1=new Hotel();
        hotel1.setMessage("failed");
        return hotel1;

    }
    public Hotel delete(Long id)
    {
        Optional<Hotel> hotel=hotelRepository.findById(id);
        if (hotel.isPresent())
        {
            hotel.get().setEtat(Etat.Desactive);
            hotel.get().setMessage("deleted");
            hotelRepository.save(hotel.get());
            return hotel.get();
        }
        Hotel hotel1=new Hotel();
        hotel1.setMessage("not present");
        return hotel1;
    }

    public Hotel restore(Long id)
    {
        Optional<Hotel> hotel=hotelRepository.findById(id);
        if (hotel.isPresent())
        {
            hotel.get().setEtat(Etat.Active);
            hotel.get().setMessage("deleted");
            hotelRepository.save(hotel.get());
            return hotel.get();
        }
        Hotel hotel1=new Hotel();
        hotel1.setMessage("not present");
        return hotel1;
    }

  public List<String> getAllVille()
  {
      return hotelRepository.getAllVille();
  }

  public List<Hotel> getHotelsByVille(String ville)
  {
      return hotelRepository.findHotelsByVille(ville);
  }
}
