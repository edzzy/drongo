/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("view")
public class DevicesBean implements Serializable{
    
    @Autowired
    EquipmentService equipmentService;
    
    private List<RunDevice> deviceList;
    private List<RunDevice> filteredDevices;
    private RunDevice selectedDevice;
    private List<Intervention> interventionList;
    
    @PostConstruct
    public void init(){
        deviceList = equipmentService.findAllDevices();
        
        for (RunDevice runDevice : deviceList) {
            for (Intervention i : runDevice.getInterventions()) {
                //System.out.print(i);
            }
        }
        
    }
    
    public String createNewDevice(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("device");
        return "device?faces-redirect=true";
    }
    
    public String editDevice(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("device");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("device", selectedDevice);
        return "device?faces-redirect=true";
    }
    
    public String showDevice(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("device");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("device", selectedDevice);
        return "deviceShow?faces-redirect=true";
    }
    
    public String showDeviceInterventions(){
        
        FacesUtils.putObjectInSessionMap("equipment", selectedDevice);
        return "interventionsEquipment?faces-redirect=true";
    }
    
    public void deleteDevice() {
        /*
        try {
            clientService.deleteClient(selectedClient);
            clientList.remove(selectedClient);
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteDone"), selectedClient.toString(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        */ 
    }
    
    public void cancelDeletion() {
        FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_deleteCanceled"), selectedDevice.toString(), FacesMessage.SEVERITY_INFO);
    }

    public List<RunDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<RunDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public List<RunDevice> getFilteredDevices() {
        return filteredDevices;
    }

    public void setFilteredDevices(List<RunDevice> filteredDevices) {
        this.filteredDevices = filteredDevices;
    }

    public RunDevice getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(RunDevice selectedDevice) {
        this.selectedDevice = selectedDevice;
    } 
    
    public int getDeviceNumber(){
        return deviceList.size();
    }
    
    public List getAllManufacturers(){
        return null;
    }
    
    public void populateInterventionList(ToggleEvent event){
        
        System.out.println((RunDevice) event.getData());
        interventionList = equipmentService.findAllInterventionsByEquipment((RunDevice) event.getData());
    }

    public List<Intervention> getInterventionList() {
        return interventionList;
    }

    public void setInterventionList(List<Intervention> interventionList) {
        this.interventionList = interventionList;
    }
    
    
  
}
