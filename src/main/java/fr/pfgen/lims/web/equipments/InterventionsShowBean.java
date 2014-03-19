/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author edouard
 */
@Component
@Scope("view")
public class InterventionsShowBean implements Serializable {

    @Autowired
    EquipmentService equipmentService;

    private List<Intervention> interventionsList;
    private Equipment equipment;

    @PostConstruct
    public void init() {

    }

    public void initInterventions() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            equipment = (Equipment) FacesUtils.getObjectInSessionMap("equipment");
            interventionsList = equipmentService.findAllInterventionsByEquipment(equipment);
            for (Intervention intervention : interventionsList) {
                System.out.println(intervention.getInterventionDate());
            }
            System.out.println("stop");
        }
    }

    public List<Intervention> getInterventionsList() {
        return interventionsList;
    }

    public void setInterventionsList(List<Intervention> interventionsList) {
        this.interventionsList = interventionsList;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

}
