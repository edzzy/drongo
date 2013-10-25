/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.projects.Contract;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ProjectService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ContractFlow;
import fr.pfgen.lims.web.util.flows.FlowType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("view")
public class ContractBean extends ContractFlow implements Serializable{
    @Autowired
    ProjectService projectService;
    
    @Autowired
    ClientService clientService;
    
    private Contract contract = new Contract();
    private List<Client> clientList;
    private List<Client> clientSource = new ArrayList<>();
    private List<Client> clientTarget = new ArrayList<>();
    private DualListModel<Client> allClients;
    
    public void initContract(){
        if (!FacesContext.getCurrentInstance().isPostback()) {
            contract = (Contract) FacesUtils.getObjectInSessionMap("contract");
            if (contract==null){
                contract = new Contract();
                FacesUtils.putObjectInSessionMap("contract", contract);
            }
        }
    }

    @PostConstruct
    public void init() {
        clientList = clientService.findAllActiveClients();
        clientSource.addAll(clientList);
        allClients = new DualListModel<>(clientSource, clientTarget);
    }
    
    public void onMainClientSelect(AjaxBehaviorEvent event){
        clientSource.clear();
        clientSource.addAll(clientList);
        clientSource.remove((Client) ((UIOutput) event.getSource()).getValue());
    }
    
    public String createNewClient(){
        return enterFlow(FlowType.CLIENT);
    }
    
    public String createNewProject(){
        return enterFlow(FlowType.PROJECT);
    }
    
    public String saveNewContract(){
        try{
            if (contract.getId() == null){
                projectService.saveContract(contract);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newContract_added"), contract.toString(), FacesMessage.SEVERITY_INFO);
            }else{
                projectService.updateContract(contract);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), contract.toString(), FacesMessage.SEVERITY_INFO);
            }
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        }catch(Exception e){
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }
    
    public String onFlowProcess(FlowEvent event) {  
        if (event.getOldStep().equalsIgnoreCase("clientsTab")){
            contract.setInvolvedClients(new HashSet<>(allClients.getTarget()));
        }
        return event.getNewStep();
    }  

    public List<Client> getClientTarget() {
        return clientTarget;
    }

    public void setClientTarget(List<Client> clientTarget) {
        this.clientTarget = clientTarget;
    }

    public List<Client> getClientSource() {
        return clientSource;
    }

    public void setClientSource(List<Client> clientSource) {
        this.clientSource = clientSource;
    }

    public DualListModel<Client> getAllClients() {
        return allClients;
    }

    public void setAllClients(DualListModel<Client> allClients) {
        this.allClients = allClients;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
