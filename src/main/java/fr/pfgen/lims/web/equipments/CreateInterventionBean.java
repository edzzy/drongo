package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.RedirectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by edouard on 06/03/14.
 */
@Component
@Scope("view")
public class CreateInterventionBean implements Serializable{
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

    @Autowired
    RedirectBean redirectBean;


    public void initIntervention(){

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
        return redirectBean.getShowIntervention() +"&id=" + newIntervention.getId();

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

    public RedirectBean getRedirectBean() {
        return redirectBean;
    }

    public void setRedirectBean(RedirectBean redirectBean) {
        this.redirectBean = redirectBean;
    }
}
