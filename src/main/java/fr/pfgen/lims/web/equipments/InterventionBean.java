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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author edouard
 */
@Component
@Scope("request")
public class InterventionBean implements Serializable{
    
    @Autowired
    EquipmentService equipmentService;
     private List<Equipment> equipmentList;
     private Equipment selectedEquipment;
     private Date interventionDate;
     private String subject;
     private String comment;
     private Intervention newIntervention;

    


   
    
    
     @PostConstruct
    public void init(){
        newIntervention = new Intervention();
        equipmentList = equipmentService.findAllEquipments();
    
    }
    
   public void onEquipmentSelect(AjaxBehaviorEvent event){
       Equipment e = (Equipment) ((UIOutput) event.getSource()).getValue();
       
   
   }
    
 
   
   public String saveIntervention(){
       try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (newIntervention.getId() == null) {
                equipmentService.saveIntervention(newIntervention);
                System.out.println(newIntervention.getEquipment().getName());
                 FacesUtils.addMessage(null, FacesUtils.getI18nValue("newIntervention_added"), newIntervention.toString(), FacesMessage.SEVERITY_INFO);
            }
          } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            System.out.print(e.getMessage());
            return null;
        }
        return null;
   
   }
   
   public void cancelIntervention(){
   }
   
    public EquipmentService getEquipmentService() {
        return equipmentService;
    }

    public void setEquipmentService(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

     public Equipment getSelectedEquipment() {
        return selectedEquipment;
    }

    public void setSelectedEquipment(Equipment selectedEquipment) {
        this.selectedEquipment = selectedEquipment;
    }

    public Date getInterventionDate() {
        return interventionDate;
    }

    public void setInterventionDate(Date interventionDate) {
        this.interventionDate = interventionDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   public Intervention getNewIntervention() {
        return newIntervention;
    }

    public void setNewIntervention(Intervention newIntervention) {
        this.newIntervention = newIntervention;
    }
}
