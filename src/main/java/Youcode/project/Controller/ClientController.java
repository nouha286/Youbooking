package Youcode.project.Controller;

import Youcode.project.Model.Client;

import Youcode.project.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;
    @GetMapping("/Clients")
    @ResponseBody
    public List<Client> getClients()
    {
        return clientService.getClientsActive();
    }

    @GetMapping("/RestoreClients")
    @ResponseBody
    public List<Client> getClientsDeleted()
    {
        return clientService.getClientsDesactive();
    }

    @PostMapping("/AddClient")
    public Client saveClient(@RequestBody Client client)
    {
        return clientService.SaveClient(client);
    }

    @PostMapping("/UpdateClient")
    public Client updateClient(@RequestBody Client client)
    {
        return clientService.updateClient(client);
    }

    @PutMapping("/ActiverClient")
    public void activerClient(@RequestBody Long id)
    {
        clientService.restore(id);
    }

    @PutMapping("/DesactiverClient")
    public void desactiverClient(@RequestBody Long id)
    {
        clientService.delete(id);
    }


}
