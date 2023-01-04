package Youcode.project.Service;

import Youcode.project.Dto.ChambreOfHotel;
import Youcode.project.Model.Chambre;
import Youcode.project.Model.Disponibilite;
import Youcode.project.Model.Etat;
import Youcode.project.Model.Hotel;
import Youcode.project.Repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    HotelService hotelService;
    public List<Chambre> getChambresActive()
    {
        return chambreRepository.findChambresActive();
    }

    public List<Chambre> getChambresDesactive()
    {
        return chambreRepository.findChambresDesactive();
    }

    public List<Chambre> getChambresDisponnible()
    {
        return chambreRepository.findChambreDisponnible();
    }
    public List<Chambre> getChambresIndisponnible()
    {
        return chambreRepository.findChambreNonDisponnible();
    }

    public Chambre saveChambre(ChambreOfHotel chambre)
    {
        Optional<Hotel> hotel=hotelService.getHotelById(chambre.getHotelId());

        if (chambre.getCategorie()!=null &&
                chambre.getDescription()!=null &&
                chambre.getFraiParNuit()>0 &&
                chambre.getHotelId()!=null &&
                hotel.isPresent())
        {


            Chambre chambreSaved=new Chambre(chambre.getCategorie(),
                    Etat.Active,
                    chambre.getDescription(),
                    chambre.getFraiParNuit(),
                    Disponibilite.Disponible,
                    "success");

            return chambreRepository.save(chambreSaved);

        }
        Chambre chambreNotSaved=new Chambre();
        chambreNotSaved.setMessage("failed");
        return chambreNotSaved;
    }





}
