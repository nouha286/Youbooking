package Youcode.project.Controller;

import Youcode.project.Model.Admin;

import Youcode.project.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AdminController {
    @Autowired
    AdminService adminService;
        @GetMapping("/Admins")
    @ResponseBody
    public List<Admin> getAdmins()
    {
        return adminService.getAdminsActive();
    }

    @GetMapping("/RestoreAdmins")
    @ResponseBody
    public List<Admin> getAdminsDeleted()
    {
        return adminService.getAdminsDesactive();
    }

    @PostMapping("/AddAdmin")
    public Admin saveAdmin(@RequestBody Admin admin)
    {
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/UpdateAdmin")
    public Admin updateAdmin(@RequestBody Admin admin)
    {
        return adminService.updateAdmin(admin);
    }

    @PutMapping("/ActiverAdmin")
    public void activerAdmin(@RequestBody Long id)
    {
        adminService.restore(id);
    }

    @PutMapping("/DesactiverAdmin")
    public void desactiverAdmin(@RequestBody Long id)
    {
        adminService.delete(id);
    }


}
