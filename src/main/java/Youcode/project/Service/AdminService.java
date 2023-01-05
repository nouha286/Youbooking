package Youcode.project.Service;


import Youcode.project.Model.Admin;
import Youcode.project.Model.Etat;
import Youcode.project.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserService userService;

    public List<Admin> getAdminsActive()
    {
        return adminRepository.findAdminsActive();
    }

    public List<Admin> getAdminsDesactive()
    {
        return adminRepository.findAdminsDesactive();
    }

    public Admin saveAdmin(Admin admin)
    {
        if (admin.getAddress()!=null &&
                admin.getEmail()!=null &&
                admin.getPassword()!=null &&
                admin.getFirstName()!=null &&
                admin.getPhone()!=null &&
                admin.getLastName()!=null)
        {
            admin.setEtat(Etat.Active);
            userService.addRoleToUser(admin, "ADMIN");
            admin.setMessage("success");
            return adminRepository.save(admin);

        }
        admin.setMessage("failed");
        return admin;
    }

    public Admin updateAdmin(Admin admin)
    {
        Optional<Admin> adminUpdated=adminRepository.findById(admin.getId());
        if (adminUpdated.isPresent())
        {
            if (adminUpdated.get().getAddress()!=null)
            {
                adminUpdated.get().setAddress(admin.getAddress());
            }
            if (adminUpdated.get().getEmail()!=null)
            {
                adminUpdated.get().setEmail(admin.getEmail());
            }
            if (adminUpdated.get().getFirstName()!=null)
            {
                adminUpdated.get().setFirstName(admin.getFirstName());
            }
            if (adminUpdated.get().getPhone()!=null)
            {
                adminUpdated.get().setPhone(admin.getPhone());
            }
            if (adminUpdated.get().getLastName()!=null)
            {
                adminUpdated.get().setLastName(admin.getLastName());
            }
            if (adminUpdated.get().getPassword()!=null)
            {
                adminUpdated.get().setMessage(admin.getPassword());
            }
            adminUpdated.get().setMessage("success");
            return adminRepository.save(adminUpdated.get());
        }
        else
        {
            admin.setMessage("failed");
            return admin;
        }
    }

    public Admin delete(Long id)
    {
        Optional<Admin> admin=adminRepository.findById(id);
        if (admin.isPresent())
        {
            admin.get().setEtat(Etat.Desactive);
            admin.get().setMessage("deleted");
            return admin.get();
        }
        Admin admin1=new Admin();
        admin1.setMessage("not present");
        return admin1;
    }

    public Admin restore(Long id)
    {
        Optional<Admin> admin=adminRepository.findById(id);
        if (admin.isPresent())
        {
            admin.get().setEtat(Etat.Active);
            admin.get().setMessage("deleted");
            return admin.get();
        }
        Admin admin1=new Admin();
        admin1.setMessage("not present");
        return admin1;
    }
}
