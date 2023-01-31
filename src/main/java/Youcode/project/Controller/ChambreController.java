package Youcode.project.Controller;

import Youcode.project.Dto.ChambreOfHotel;
import Youcode.project.Model.Chambre;
import Youcode.project.Model.Hotel;
import Youcode.project.Service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChambreController {
    @Autowired
    ChambreService chambreService;

    @GetMapping("/Chambres")
    @ResponseBody
    public List<Chambre> getChambres()
    {
        return chambreService.getChambresActive();
    }

    @GetMapping("/ChambresOfHotel/{id}")
    public List<Chambre> getChambresOfHotel(@PathVariable("id") Long id)
    {
        return chambreService.getChambresByHotel(id);
    }
    @GetMapping("/Chambre/{id}")
    public Optional<Chambre> getChambreById(@PathVariable("id") Long id)
    {
        return chambreService.getOneChambre(id);
    }
    @GetMapping("/restoreChambre")
    @ResponseBody
    public List<Chambre> getChambresDeleted()
    {
        return chambreService.getChambresDesactive();
    }

    @GetMapping("/ChambresNonReserve")
    @ResponseBody
    public List<Chambre> getChambresDisponnible()
    {
        return chambreService.getChambresDisponnible();
    }

    @GetMapping("/ChambresReserve")
    @ResponseBody
    public List<Chambre> getChambresIndisponnible()
    {
        return chambreService.getChambresIndisponnible();
    }

    @PostMapping("/addChambre")
    public Chambre saveChambre(@RequestBody ChambreOfHotel chambre)
    {
        return chambreService.saveChambre(chambre);
    }

    @PostMapping("/updateChambre")
    public Chambre updateChambre(@RequestBody ChambreOfHotel chambre)
    {
        return chambreService.updateChambre(chambre);
    }

    @PutMapping("/deleteChambre")
    public void desactiverChambre(@RequestBody Long id)
    {
        chambreService.delete(id);
    }

    @PutMapping("/activerChambre")
    public void activerChambre(@RequestBody Long id)
    {
        chambreService.restore(id);
    }
}
