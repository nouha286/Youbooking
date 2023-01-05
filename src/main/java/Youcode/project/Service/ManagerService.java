package Youcode.project.Service;

import Youcode.project.Model.Admin;
import Youcode.project.Model.Etat;
import Youcode.project.Model.Manager;
import Youcode.project.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    UserService userService;

    public Optional<Manager>  findOneManager(Long id)
    {
        return managerRepository.findManagerById(id);
    }

    public List<Manager>  getManagersActive()
    {
        return managerRepository.findManagersActive();
    }

    public List<Manager> getManagersDesactive()
    {
        return managerRepository.findManagersDesactive();
    }

    public Manager saveManager(Manager manager)
    {
        if (manager.getAddress()!=null &&
                manager.getEmail()!=null &&
                manager.getPassword()!=null &&
                manager.getFirstName()!=null &&
                manager.getPhone()!=null &&
                manager.getLastName()!=null )
        {
            manager.setUUID(UUID.randomUUID().toString());
            manager.setEtat(Etat.Active);
            userService.addRoleToUser(manager,"MANAGER");
            manager.setMessage("success");
            return managerRepository.save(manager);
        }
        manager.setMessage("failed");
        return manager;
    }


    public Manager updateManager(Manager manager)
    {
        Optional<Manager> managerUpdated=managerRepository.findById(manager.getId());
        if (managerUpdated.isPresent())
        {
            if (managerUpdated.get().getAddress()!=null)
            {
                managerUpdated.get().setAddress(manager.getAddress());
            }
            if (managerUpdated.get().getEmail()!=null)
            {
                managerUpdated.get().setEmail(manager.getEmail());
            }
            if (managerUpdated.get().getFirstName()!=null)
            {
                managerUpdated.get().setFirstName(manager.getFirstName());
            }
            if (managerUpdated.get().getPhone()!=null)
            {
                managerUpdated.get().setPhone(manager.getPhone());
            }
            if (managerUpdated.get().getLastName()!=null)
            {
                managerUpdated.get().setLastName(manager.getLastName());
            }
            if (managerUpdated.get().getPassword()!=null)
            {
                managerUpdated.get().setPassword(manager.getPassword());
            }

            managerUpdated.get().setMessage("success");
            return managerRepository.save(managerUpdated.get());
        }
        else
        {
            manager.setMessage("failed");
            return manager;
        }
    }

    public Manager delete(Long id)
    {
        Optional<Manager> manager=managerRepository.findById(id);
        if (manager.isPresent())
        {
            manager.get().setEtat(Etat.Desactive);
            manager.get().setMessage("deleted");
            return manager.get();
        }
        Manager manager1=new Manager();
        manager1.setMessage("not present");
        return manager1;
    }
    public Manager restore(Long id)
    {
        Optional<Manager> manager=managerRepository.findById(id);
        if (manager.isPresent())
        {
            manager.get().setEtat(Etat.Active);
            manager.get().setMessage("deleted");
            return manager.get();
        }
        Manager manager1=new Manager();
        manager1.setMessage("not present");
        return manager1;
    }
}
