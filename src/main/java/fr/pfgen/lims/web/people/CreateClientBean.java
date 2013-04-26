/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.service.ClientService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author eric
 */
@Controller
@Scope("view")
@ManagedBean
public class CreateClientBean implements Serializable{

    /**
     * Creates a new instance of CreateClientBean
     */
    private Client newClient = new Client();
    
    @Autowired
    private ClientService clientService;
    
    private UIComponent emailField;

    public UIComponent getEmailField() {
        return emailField;
    }

    public void setEmailField(UIComponent emailField) {
        this.emailField = emailField;
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

    public String saveNewClient() {
        try {
            newClient.setFirstname(WordUtils.capitalizeFully(newClient.getFirstname(), '-', ' '));
            newClient.setLastname(WordUtils.capitalizeFully(newClient.getLastname(), '-', ' '));
            newClient.setEmail(newClient.getEmail().toLowerCase());
            clientService.saveClient(newClient);
            FacesContext context = FacesContext.getCurrentInstance();
            String text = context.getApplication().getResourceBundle(context, "messages").getString("newClient_added");
            FacesMessage msg = new FacesMessage(text, newClient.toString());
            context.addMessage(null, msg);
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "clients?faces-redirect=true";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            String text = context.getApplication().getResourceBundle(context, "messages").getString("label_error");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, e.getMessage());
            context.addMessage(null, msg);
            return null;
        }
    }
    
    public void validateEmail(FacesContext context, UIComponent validate, Object value){
        String email = (String)value;

        Client existingClient = clientService.findByEmail(email);
        if (existingClient != null){
            ((UIInput)validate).setValid(false);
            String text = context.getApplication().getResourceBundle(context, "messages").getString("label_alreadyExists");
            FacesMessage msg = new FacesMessage(email+" "+text);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(validate.getClientId(context), msg);
        }
    }
}
