/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.service.ClientTypeService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@ManagedBean
@RequestScoped
public class ClientForm{
    
    private Client client = new Client();
    private Long clientTypeID;
    
    @Autowired
    ClientTypeService clientTypeService;

    public Long getClientTypeID() {
        return clientTypeID;
    }

    public void setClientTypeID(Long clientTypeID) {
        this.clientTypeID = clientTypeID;
    }

    public ClientTypeService getClientTypeService() {
        return clientTypeService;
    }

    public void setClientTypeService(ClientTypeService clientTypeService) {
        this.clientTypeService = clientTypeService;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ClientType> getAvailableClientTypes() {
        return clientTypeService.findAllClientTypes();
    }
    
    public String addNewClient(){
        return null;
        
    }
}
