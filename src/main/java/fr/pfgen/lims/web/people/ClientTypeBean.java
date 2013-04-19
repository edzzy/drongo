/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

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
public class ClientTypeBean {

    @Autowired
    ClientTypeService clientTypeService;
    
    public List<ClientType> getClientTypes() {
        return clientTypeService.findAllClientTypes();
    }
}
