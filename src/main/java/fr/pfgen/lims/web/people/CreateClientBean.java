/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.service.ClientService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@ManagedBean
@ViewScoped
public class CreateClientBean {

    /**
     * Creates a new instance of CreateClientBean
     */
    
    private Client newClient;
    
    @Autowired
    private ClientService clientService;
    
    public CreateClientBean() {
        newClient = new Client();
    }

    public Client getNewClient() {
        return newClient;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    
    public String saveNewClient(){
        clientService.saveClient(newClient);
        return null;
    }
    
    public String goNewType(){
        return "clientTypeCreate?faces-redirect=true";
    }
}
