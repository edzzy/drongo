/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
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
public class ClientBean implements Serializable {

    @Autowired
    ClientService clientService;
    
    @Autowired
    ClientTypeService clientTypeService;
    
    private List<Client> clientList;
    private List<ClientType> clientTypeList;
    private List<Client> filteredClients;

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostConstruct
    public void init() {
        clientList = clientService.findAllClients();
        clientTypeList = clientTypeService.findAllClientTypes();
    }
    
    public int getClientNumber(){
        return clientList.size();
    }

    public List<Client> getClientList() {

        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Client> getFilteredClients() {
        return filteredClients;
    }

    public void setFilteredClients(List<Client> filteredClients) {
        this.filteredClients = filteredClients;
    }

    public void onEdit(RowEditEvent event) {
        Client clientToEdit = (Client) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Client clientUpdated = clientService.updateClient(clientToEdit);
            int index = clientList.indexOf(clientToEdit);
            clientList.remove(index);
            clientList.add(index, clientUpdated);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("clientTable");

            String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_done");
            FacesMessage msg = new FacesMessage(text, ((Client) event.getObject()).toString());

            context.addMessage(null, msg);
        } catch (Exception e) {
            String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_error");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, e.getMessage());
            context.addMessage(null, msg);
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_cancelled");
        FacesMessage msg = new FacesMessage(text, ((Client) event.getObject()).toString());

        context.addMessage(null, msg);
    }

    public void onEditInit(RowEditEvent event) {
        Client clientToEdit = (Client) event.getObject();
        Client clientIdDb = clientService.findClient(clientToEdit.getId());

        if (clientToEdit.getVersion() != clientIdDb.getVersion()) {
            int index = clientList.indexOf(clientToEdit);
            clientList.remove(index);
            clientList.add(index, clientIdDb);
            RequestContext rcontext = RequestContext.getCurrentInstance();
            rcontext.update("clientTable");

            FacesContext context = FacesContext.getCurrentInstance();
            String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_rowchanged");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, text, clientToEdit.toString());

            context.addMessage(null, msg);
        }
    }

    public void removeClient(Client client) {
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String email = (String) value;

        Client existingClient = clientService.findByEmail(email);
        if (existingClient != null && existingClient.getId() != (Long) component.getAttributes().get("clientID")) {
            ((UIInput) component).setValid(false);
            ResourceBundle bundle = context.getApplication().getResourceBundle(context, "messages");
            String error = bundle.getString("edit_error");
            String text = bundle.getString("label_alreadyExists");
            FacesMessage msg = new FacesMessage(error, email + " " + text);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(component.getClientId(context), msg);
        }
    }
}
