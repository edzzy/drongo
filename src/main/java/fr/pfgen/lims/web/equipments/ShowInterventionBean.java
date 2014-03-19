package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.repository.InterventionRepository;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.RedirectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by edouard on 06/03/14.
 */
@Component
@Scope("view")
public class ShowInterventionBean implements Serializable {

    @Autowired
    RedirectBean redirectBean;
    @Autowired
    EquipmentService equipmentService;
    private Intervention intervention;

    public String returnEquipment(){
        return redirectBean.getShowEquipment() + "&id=" + intervention.getEquipment().getId();
    }

    public String updateIntervention(){
        return redirectBean.getShowEquipment() + "&id=" + intervention.getId();
    }
    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}
