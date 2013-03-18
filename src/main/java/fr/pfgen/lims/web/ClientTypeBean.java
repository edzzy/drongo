/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web;

import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.service.ClientTypeService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@ManagedBean
@SessionScoped
public class ClientTypeBean {

    @Autowired
    ClientTypeService clientTypeService;
    
    private List<ClientType> clientTypes;
    
    /**
     * Creates a new instance of ClientTypeBean
     */
    @PostConstruct
    public void init() {
        clientTypes = clientTypeService.findAllClientTypes();
    }

    public ClientTypeService getClientTypeService() {
        return clientTypeService;
    }

    public void setClientTypeService(ClientTypeService clientTypeService) {
        this.clientTypeService = clientTypeService;
    }

    public List<ClientType> getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(List<ClientType> clientTypes) {
        this.clientTypes = clientTypes;
    }
    
    
}
