/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.*;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import fr.pfgen.lims.web.util.RedirectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by edouard on 21/02/14.
 */

@Scope("view")
@Component
public class ListEquipmentsBean implements Serializable{

    @Autowired
    EquipmentService equipmentService;

    private List<Equipment> equipmentList;
    private List<EquipmentCategory> categoryList;
    private List<Equipment> filteredEquipments;
    private List<EquipmentStatus> equipmentStatusList;
    private List<PlateformType> equipmentPlateformList;
    private Equipment selectedEquipment;
    private SelectItem[] categoryOptions;
    private SelectItem[] statusOption;
    private SelectItem[] plateformOption;
    private List<Funding> fundings;

    @Autowired
    RedirectBean redirectBean;


    @PostConstruct
    public void init(){
        equipmentList = equipmentService.findAllEquipments();
        equipmentStatusList = Arrays.asList(EquipmentStatus.values()) ;
        statusOption = createFilterOptionsStatus(equipmentStatusList);
        categoryList = Arrays.asList(EquipmentCategory.values());
        categoryOptions = createFilterOptionsCategory(categoryList);
        equipmentPlateformList = Arrays.asList(PlateformType.values());
        plateformOption = createFilterOptionsPlateform(equipmentPlateformList);
        fundings = equipmentService.findAllFundings();
        filteredEquipments = equipmentService.findEquipmentByStatus(EquipmentStatus.ACTIVE);

    }

    public String createNewEquipment(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equipment");
        return redirectBean.getCreateEquipment();
    }

    public String editEquipment(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equipment");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("equipment", selectedEquipment);
        return redirectBean.getEditEquipment() + "&id=" + selectedEquipment.getId();
    }
    public String showEquipment(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("equipment");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("equipment", selectedEquipment);

        return redirectBean.getShowEquipment() + "&id=" +selectedEquipment.getId();
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
        FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_deleteCanceled"), selectedEquipment.toString(), FacesMessage.SEVERITY_INFO);
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

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Equipment> getFilteredEquipments() {
        return filteredEquipments;
    }

    public void setFilteredEquipments(List<Equipment> filteredEquipments) {
        this.filteredEquipments = filteredEquipments;
    }

    public Equipment getSelectedEquipment() {
        return selectedEquipment;
    }

    

    private SelectItem[] createFilterOptionsCategory(List<EquipmentCategory> data)  {
        SelectItem[] options = new SelectItem[data.size() + 1];

        options[0] = new SelectItem("", FacesUtils.getI18nValueInMessages("label_select"));
        for(int i = 0; i < data.size(); i++) {
            options[i + 1] = new SelectItem(data.get(i).getLabel(), data.get(i).getLabel());
        }

        return options;
    }

    public void setSelectedEquipment(Equipment selectedEquipment) {
        this.selectedEquipment = selectedEquipment;
    }

    public int getEquipmentNumberFiltered(){
        return  filteredEquipments.size();
    }
    public int getEquipmentNumber(){
        return equipmentList.size();
    }

    public SelectItem[] getStatusOption() {
        return statusOption;
    }
    public SelectItem[] getPlateformOption(){
        return  plateformOption;
    }

    public SelectItem[] getStatusList() {
        return statusOption;
    }

    private SelectItem[] createFilterOptionsStatus(List<EquipmentStatus> data)  {
        SelectItem[] options = new SelectItem[data.size() ];

        
        for(int i = 0; i < data.size(); i++) {
            options[i ] = new SelectItem(data.get(i).getLabel(), data.get(i).getLabel());
        }

        return options;
    }

    private SelectItem[] createFilterOptionsPlateform(List<PlateformType> data ){
        SelectItem[] options = new SelectItem[data.size() + 1];

        options[0] = new SelectItem("", FacesUtils.getI18nValueInMessages("label_select"));
        for(int i = 0; i < data.size(); i++) {
            options[i + 1] = new SelectItem(data.get(i).getLabel(), data.get(i).getLabel());
        }

        return options;

    }

}
