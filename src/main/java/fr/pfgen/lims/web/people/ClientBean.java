/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@ManagedBean
@RequestScoped
public class ClientBean {

    @Autowired
    ClientService clientService;
    
    @Autowired
    ClientTypeService clientTypeService;
    
    private List<Client> clientList;
    private List<ClientType> clientTypeList;
    private List<Client> filteredClients;
    private Client selectedClient;
    private Client[] selectedClients;
    private List<Client> clients;

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    
    @PostConstruct
    public void init(){
        clientList = clientService.findAllClients();
        clientTypeList = clientTypeService.findAllClientTypes();
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

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public Client[] getSelectedClients() {
        return selectedClients;
    }

    public void setSelectedClients(Client[] selectedClients) {
        this.selectedClients = selectedClients;
    }
    
    public void onEdit(RowEditEvent event) {
        Client clientToEdit = (Client) event.getObject();
        Client clientUpdated = clientService.updateClient(clientToEdit);
        int index = clientList.indexOf(clientToEdit);
        clientList.remove(index);
        clientList.add(index, clientUpdated);
        RequestContext rcontext = RequestContext.getCurrentInstance(); 
        rcontext.update("clientTable");
        
        FacesContext context = FacesContext.getCurrentInstance();
        String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_done");
        FacesMessage msg = new FacesMessage(text, ((Client) event.getObject()).toString());  
  
        context.addMessage(null, msg);
        //clientList = clientService.findAllClients();
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesContext context = FacesContext.getCurrentInstance();
        String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_cancelled");
        FacesMessage msg = new FacesMessage(text, ((Client) event.getObject()).toString());  
  
        context.addMessage(null, msg);  
    }
    
    public void onEditInit(RowEditEvent event){
       Client clientToEdit = (Client) event.getObject();
       Client clientIdDb = clientService.findClient(clientToEdit.getId());
       
       if (clientToEdit.getVersion()!=clientIdDb.getVersion()){
           //clientList = clientService.findAllClients();
           int index = clientList.indexOf(clientToEdit);
           clientList.remove(index);
           clientList.add(index, clientIdDb);
           RequestContext rcontext = RequestContext.getCurrentInstance(); 
           rcontext.update("clientTable");
           
           FacesContext context = FacesContext.getCurrentInstance();
           String text = context.getApplication().getResourceBundle(context, "messages").getString("edit_rowchanged");
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN ,text, clientToEdit.toString());  
           
           context.addMessage(null, msg);  
       }
    }
    
    public void removeClient(Client client){
        
    }
}
