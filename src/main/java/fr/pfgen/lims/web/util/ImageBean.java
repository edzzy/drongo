/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util;

import fr.pfgen.lims.domain.equipments.RunDevice;
import java.io.ByteArrayInputStream;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author eric
 */
@Controller
@Scope("request")
@ManagedBean
public class ImageBean {
    
    public StreamedContent getDeviceStreamedContent() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        StreamedContent streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(((RunDevice)sessionMap.get("device")).getImage()));
        
        return streamedContent;
    }
}
