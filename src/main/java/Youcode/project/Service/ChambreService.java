package Youcode.project.Service;

import Youcode.project.Dto.ChambreOfHotel;
import Youcode.project.Model.*;
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
    public Optional<Chambre>  getOneChambre(Long id)
    {
        return chambreRepository.findById(id);
    }
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
            chambreSaved.setHotel(hotel.get());

            return chambreRepository.save(chambreSaved);

        }
        Chambre chambreNotSaved=new Chambre();
        chambreNotSaved.setMessage("failed");
        return chambreNotSaved;
    }

    public Chambre updateChambre(ChambreOfHotel chambre)
    {

       Optional<Chambre>  chambreUpdated=chambreRepository.findById(chambre.getId());
        if (chambreUpdated.isPresent())
        {
            if (chambre.getCategorie()!=null)
            {
                chambreUpdated.get().setCategorie(chambre.getCategorie());
            }
            if (chambre.getDescription()!=null)
            {
                chambreUpdated.get().setDescription(chambre.getDescription());
            }
            if(chambre.getFraiParNuit()>0 && chambre.getFraiParNuit()!=null)
            {
                chambreUpdated.get().setFraiParNuit(chambre.getFraiParNuit());
            }
            if (chambre.getHotelId()!=null)
            {
                Optional<Hotel> hotel= hotelService.getHotelById(chambre.getHotelId());
                chambreUpdated.get().setHotel(hotel.get());
            }
            return chambreRepository.save(chambreUpdated.get());

        }


        Chambre chambre1=new Chambre();
        chambre1.setMessage("failed");
        return chambre1;

    }

    public Chambre delete(Long id)
    {
        Optional<Chambre> chambre=chambreRepository.findById(id);
        if (chambre.isPresent())
        {
            chambre.get().setEtat(Etat.Desactive);
            chambre.get().setMessage("deleted");
            return chambre.get();
        }
        Chambre chambre1=new Chambre();
        chambre1.setMessage("not present");
        return chambre1;
    }

    public Chambre restore(Long id)
    {
        Optional<Chambre> chambre=chambreRepository.findById(id);
        if (chambre.isPresent())
        {
            chambre.get().setEtat(Etat.Active);
            chambre.get().setMessage("restore");
            return chambre.get();
        }
        Chambre chambre1=new Chambre();
        chambre1.setMessage("not present");
        return chambre1;
    }

    public void MakeRoomsAvailable(Long id)
    {
        Optional<Chambre> chambre=chambreRepository.findById(id);
        if (chambre.isPresent())
        {
            chambre.get().setDisponibilite(Disponibilite.Disponible);
            chambreRepository.save(chambre.get());
        }

    }

    public void MakeRoomsUnavailable(Long id)
    {
        Optional<Chambre> chambre=chambreRepository.findById(id);
        if (chambre.isPresent())
        {
            chambre.get().setDisponibilite(Disponibilite.Indisponible);
            chambreRepository.save(chambre.get());
        }
    }


}
