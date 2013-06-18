/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.service.ClientTypeService;
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
public class CreateClientTypeBean {

    /**
     * Creates a new instance of CreateClientTypeBean
     */
    
    private ClientType newClientType;
    
    @Autowired
    private ClientTypeService clientTypeService;

    public CreateClientTypeBean(){
        newClientType = new ClientType();
    }
    
    public ClientType getNewClientType() {
        return newClientType;
    }

    public void setNewClientType(ClientType newClientType) {
        this.newClientType = newClientType;
    }

    public ClientTypeService getClientTypeService() {
        return clientTypeService;
    }

    public void setClientTypeService(ClientTypeService clientTypeService) {
        this.clientTypeService = clientTypeService;
    }
    
    public String saveNewClientType(){
        clientTypeService.saveClientType(newClientType);
        return null;
    }
}
