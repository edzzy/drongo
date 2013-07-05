/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import fr.pfgen.lims.domain.equipments.SmallEquipment;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("request")
@ManagedBean
public class EquipmentCategoryCreateBean {
    
    private EquipmentCategory category = new EquipmentCategory();
    
    @Autowired
    private EquipmentService equipmentService;

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = ((String) value).toLowerCase();

        EquipmentCategory existingCategory = equipmentService.findEquipmentCategoryByName(name);

        if (existingCategory != null) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), null, "\"" + name + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String saveNewCategory() {
        try {
            equipmentService.saveEquipmentCategory(category);
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            SmallEquipment equipment = (SmallEquipment) sessionMap.get("equipment");

            if (equipment != null) {
                equipment.setCategory(category);
                sessionMap.put("wizStep", "otherTab");
                return "smallEquipment?faces-redirect=true";
            } else {
                //TODO redirect to company list when created !!!!
                return null;
            }
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelCategoryCreation() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        SmallEquipment equipment = (SmallEquipment) sessionMap.get("equipment");

        if (equipment != null) {
            sessionMap.put("wizStep", "otherab");
            return "smallEquipment?faces-redirect=true";
        }else{
            //TODO redirect to company list when created !!!!
            return null;
        }
    }

    public EquipmentCategory getCategory() {
        return category;
    }

    public void setCategory(EquipmentCategory category) {
        this.category = category;
    }
}
