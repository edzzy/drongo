/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
public class DevicesBean implements Serializable{
    
    @Autowired
    EquipmentService equipmentService;
    
    private List<RunDevice> deviceList;
    private List<RunDevice> filteredDevices;
    private RunDevice selectedDevice;
    
    @PostConstruct
    public void init(){
        deviceList = equipmentService.findAllDevices();
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
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteCanceled"), selectedDevice.toString(), FacesMessage.SEVERITY_INFO);
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
}
