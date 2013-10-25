/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.util;

import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.service.ClientTypeService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * @author eric
 */
public class InitDatabase implements ServletContextListener{

    @Autowired
    ClientTypeService clientTypeService=null;
    
    
    @Override
    public void contextDestroyed(ServletContextEvent sce)
        {
            this.clientTypeService=null;
        }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        WebApplicationContextUtils
        .getRequiredWebApplicationContext(sce.getServletContext())
        .getAutowireCapableBeanFactory()
        .autowireBean(this);
        
        ClientType c = new ClientType();
        c.setName("interne");
        clientTypeService.saveClientType(c);
        c = new ClientType();
        c.setName("académique");
        clientTypeService.saveClientType(c);
        c = new ClientType();
        c.setName("privé");
        clientTypeService.saveClientType(c);
        
        
    }
}
