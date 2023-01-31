package Youcode.project.Controller;

import Youcode.project.Model.Manager;
import Youcode.project.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/Managers")
    @ResponseBody
    public List<Manager> getManagers()
    {
        return managerService.getManagersActive();
    }

    @GetMapping("/RestoreManagers")
    @ResponseBody
    public List<Manager> getManagersDeleted()
    {
        return managerService.getManagersDesactive();
    }

    @PostMapping("/AddManager")
    public Manager saveManager(@RequestBody Manager manager)
    {
        return managerService.saveManager(manager);
    }

    @PostMapping("/UpdateManager")
    public Manager updateManager(@RequestBody Manager manager)
    {
        return managerService.updateManager(manager);
    }

    @PutMapping("/ActiverManager")
    public Manager activerManager(@RequestBody Long id)
    {
        return managerService.restore(id);
    }

    @PutMapping("/DesactiverManager")
    public Manager desactiverManager(@RequestBody Long id)
    {
      return   managerService.delete(id);
    }


}
