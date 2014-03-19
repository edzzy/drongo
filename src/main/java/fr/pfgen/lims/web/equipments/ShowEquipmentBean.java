package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.*;
import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.RedirectBean;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by edouard on 21/02/14.
 */

@Scope("request")
@Component
public class ShowEquipmentBean implements Serializable{

    @Autowired
    EquipmentService equipmentService;
    private Equipment equipment;
    private String wizStep;
    private boolean isNewEquipment;
    private List<EquipmentCategory> equipmentCategoryList;
    private String equipmentStatusList;
    private PieChartModel pieModel;
    private List<Funding> fundingList;
    private List<Intervention> interventionList;
    private Intervention selectedIntervention;

    @Autowired
    RedirectBean redirectBean;

    @PostConstruct
    public void init() {
        equipmentCategoryList = equipmentService.findAllEquipmentCategories();
        equipment = (Equipment) FacesUtils.getObjectInSessionMap("equipment");
        fundingList = equipmentService.findAllFundingsByEquipments(equipment);
        interventionList = equipmentService.findAllInterventionsByEquipment(equipment);
        createPieModel();

    }



    private void createPieModel() {
        if(!fundingList.isEmpty()){
        pieModel = new PieChartModel();

        for (Funding funding : fundingList){
            pieModel.set(funding.getOrganism().getName(), funding.getPercent());
        }
        }
     }

    public void showEquipment(){
        equipment = (Equipment) FacesUtils.getObjectInSessionMap("equipment");
        fundingList = equipmentService.findAllFundingsByEquipments(equipment);
        interventionList = equipmentService.findAllInterventionsByEquipment(equipment);
        createPieModel();
    }
    public void initEquipment() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            equipment = (Equipment) sessionMap.get("equipment");
            wizStep = (String) sessionMap.get("wizStep");
            sessionMap.remove("wizStep");
            if (wizStep == null) {
                wizStep = "descriptionTab"; //first tab
            }

            if (equipment == null) {
                equipment = new SmallEquipment();
                isNewEquipment = true;
                sessionMap.put("equipment", equipment);
            } else {
                isNewEquipment = false;
            }
        }
    }

    public String editIntervention(){

        return redirectBean.getEditIntervention() +"&id=" + selectedIntervention.getId();


    }

    public String createIntervention(){
        return redirectBean.getCreateIntervention()+"&equipment=" + equipment.getId();
    }

    public String saveEquipment() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (equipment.getId() == null) {
                equipmentService.saveEquipment(equipment);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newEquipment_added"), equipment.toString(), FacesMessage.SEVERITY_INFO);

            } else {
                equipmentService.updateEquipment(equipment);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), equipment.toString(), FacesMessage.SEVERITY_INFO);
            }
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "smallEquipments?faces-redirect=true";
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelEquipment() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (equipment.getId() == null) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_createCanceled"), null, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), equipment.toString(), FacesMessage.SEVERITY_INFO);
        }
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "smallEquipments?faces-redirect=true";
    }

    public void validateSerial(FacesContext context, UIComponent component, Object value) {
        String serial = (String) value;

        Equipment existingEquipment = equipmentService.findEquipmentBySerial(serial);

        if ((existingEquipment != null && existingEquipment.getId() != equipment.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\"" + serial + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void validateItx(FacesContext context, UIComponent component, Object value) {
        String itx = (String) value;
        if (itx.isEmpty()) return;

        Equipment existingEquipment = equipmentService.findEquipmentByItx(itx);

        if ((existingEquipment != null && existingEquipment.getId() != equipment.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\"" + itx + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String createNewEquipmentCategory(){
        return "equipmentCategoryCreate?faces-redirect=true";
    }

    public String getSaveOrEditLabel() {
        if (equipment.getId() != null) {
            return FacesUtils.getI18nValue("label_edit");
        } else {
            return FacesUtils.getI18nValue("label_save");
        }
    }

    public String getPlusOrPen() {
        if (equipment.getId() != null) {
            return "ui-icon-pencil";
        } else {
            return "ui-icon-disk";
        }
    }

    public List<EquipmentCategory> getEquipmentCategoryList() {
        return equipmentCategoryList;
    }

    public void setEquipmentCategoryList(List<EquipmentCategory> equipmentCategoryList) {
        this.equipmentCategoryList = equipmentCategoryList;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public List<Intervention> getInterventionList() {
        return interventionList;
    }

    public void setInterventionList(List<Intervention> interventionList) {
        this.interventionList = interventionList;
    }

    public String getWizStep() {
        return wizStep;
    }

    public void setWizStep(String wizStep) {
        this.wizStep = wizStep;
    }

    public boolean isIsNewEquipment() {
        return isNewEquipment;
    }

    public void setIsNewEquipment(boolean isNewEquipment) {
        this.isNewEquipment = isNewEquipment;
    }

    public String getEquipmentStatusList() {
        return equipmentStatusList;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<Funding> getFundingList() {
        return fundingList;
    }

    public void setFundingList(List<Funding> fundingList) {
        this.fundingList = fundingList;
    }

    public Intervention getSelectedIntervention() {
        return selectedIntervention;
    }



    public void setSelectedIntervention(Intervention selectedIntervention) {
        this.selectedIntervention = selectedIntervention;
    }
}
