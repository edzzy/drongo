/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import fr.pfgen.lims.domain.equipments.SmallEquipment;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
public class SmallEquipmentsBean {
    
    @Autowired
    EquipmentService equipmentService;
    
    private List<SmallEquipment> equipmentList;
    private List<EquipmentCategory> categoryList;
    private List<SmallEquipment> filteredEquipments;
    private SmallEquipment selectedEquipment;
    private SelectItem[] categoryOptions;
    
    @PostConstruct
    public void init(){
        equipmentList = equipmentService.findAllSmallEquipments();
        categoryList = equipmentService.findAllEquipmentCategories();
        categoryOptions = createFilterOptions(categoryList);
    }
    
    public String createNewEquipment(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equipment");
        return "smallEquipment?faces-redirect=true";
    }
    
    public String editEquipment(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equipment");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("equipment", selectedEquipment);
        return "smallEquipment?faces-redirect=true";
    }
    
    public void deleteEquipment() {
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
        FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_deleteCanceled"), selectedEquipment.toString(), FacesMessage.SEVERITY_INFO);
    }

    public List<EquipmentCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<EquipmentCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public SelectItem[] getCategoryOptions() {
        return categoryOptions;
    }

    public List<SmallEquipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<SmallEquipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<SmallEquipment> getFilteredEquipments() {
        return filteredEquipments;
    }

    public void setFilteredEquipments(List<SmallEquipment> filteredEquipments) {
        this.filteredEquipments = filteredEquipments;
    }

    public SmallEquipment getSelectedEquipment() {
        return selectedEquipment;
    }

    public void setSelectedEquipment(SmallEquipment selectedEquipment) {
        this.selectedEquipment = selectedEquipment;
    }
    
    private SelectItem[] createFilterOptions(List<EquipmentCategory> data)  {  
        SelectItem[] options = new SelectItem[data.size() + 1];  
  
        options[0] = new SelectItem("", FacesUtils.getI18nValue("label_select"));  
        for(int i = 0; i < data.size(); i++) { 
            options[i + 1] = new SelectItem(data.get(i).getName(), data.get(i).getName());  
        }  
  
        return options;  
    }  
    
    public int getEquipmentNumber(){
        return equipmentList.size();
    }
}
