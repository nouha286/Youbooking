package Youcode.project.Service;

import Youcode.project.Model.Admin;
import Youcode.project.Model.Client;
import Youcode.project.Model.Etat;
import Youcode.project.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserService userService;

    public Client getClientById(Long id)
    {
       return clientRepository.findClientById(id);
    }
    public List<Client> getClientsActive()
    {
        return clientRepository.findClientsActive();
    }
    public List<Client> getClientsDesactive()
    {
        return clientRepository.findClientsDesactive();
    }

    public Client SaveClient(Client client)
    {
        if (client.getAddress()!=null &&
                client.getEmail()!=null &&
                client.getPassword()!=null &&
                client.getFirstName()!=null &&
                client.getPhone()!=null &&
                client.getLastName()!=null &&
                client.getCNE()!=null) {
           userService.addRoleToUser(client, "CLIENT");
           client.setEtat(Etat.Active);
           client.setMessage("success");
            return clientRepository.save(client);
        }
        client.setMessage("failed");
        return client;
    }


    public Client updateClient(Client client)
    {
        Optional<Client> clientUpdated=clientRepository.findById(client.getId());
        if (clientUpdated.isPresent())
        {
            if (clientUpdated.get().getAddress()!=null)
            {
                clientUpdated.get().setAddress(client.getAddress());
            }
            if (clientUpdated.get().getEmail()!=null)
            {
                clientUpdated.get().setEmail(client.getEmail());
            }
            if (clientUpdated.get().getFirstName()!=null)
            {
                clientUpdated.get().setFirstName(client.getFirstName());
            }
            if (clientUpdated.get().getPhone()!=null)
            {
                clientUpdated.get().setPhone(client.getPhone());
            }
            if (clientUpdated.get().getLastName()!=null)
            {
                clientUpdated.get().setLastName(client.getLastName());
            }
            if (clientUpdated.get().getCNE()!=null)
            {
                clientUpdated.get().setCNE(client.getCNE());
            }
            if (clientUpdated.get().getPassword()!=null)
            {
                clientUpdated.get().setPassword(client.getPassword());
            }
            clientUpdated.get().setMessage("success");
            return clientRepository.save(clientUpdated.get());
        }
        else
        {
            client.setMessage("failed");
            return client;
        }
    }

    public Client delete(Long id)
    {
        Optional<Client> client=clientRepository.findById(id);
        if (client.isPresent())
        {
            client.get().setEtat(Etat.Desactive);
            client.get().setMessage("deleted");
            return client.get();
        }
        Client client1=new Client();
        client1.setMessage("not present");
        return client1;
    }

    public Client restore(Long id)
    {
        Optional<Client> client=clientRepository.findById(id);
        if (client.isPresent())
        {
            client.get().setEtat(Etat.Active);
            client.get().setMessage("deleted");
            return client.get();
        }
        Client client1=new Client();
        client1.setMessage("not present");
        return client1;
    }
}
