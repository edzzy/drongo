/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.domain.equipments.SmallEquipment;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
public class SmallEquipmentBean {
    
    @Autowired
    EquipmentService equipmentService;
    private SmallEquipment equipment;
    private String wizStep;
    private boolean isNewEquipment;
    private List<EquipmentCategory> equipmentCategoryList;
    
    @PostConstruct
    public void init() {
        equipmentCategoryList = equipmentService.findAllEquipmentCategories();
    }

    public void initEquipment() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            equipment = (SmallEquipment) sessionMap.get("equipment");
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

    public SmallEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(SmallEquipment equipment) {
        this.equipment = equipment;
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
}
