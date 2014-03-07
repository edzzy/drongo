package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Created by edouard on 03/03/14.
 */
@Scope("view")
@Component
public class ListInterventionsBean implements Serializable{

    @Autowired
    EquipmentService equipmentService;

    private List<Intervention> interventionList;
    private List<Intervention> filteredInterventions;
    private Intervention selectedInterventions;
    private Integer numberInterventions;


    @PostConstruct
    public void init(){
        interventionList =equipmentService.findAllInterventions();
        numberInterventions = interventionList.size();
    }

    public Intervention getSelectedInterventions() {
        return selectedInterventions;
    }

    public void setSelectedInterventions(Intervention selectedInterventions) {
        this.selectedInterventions = selectedInterventions;
    }

    public List<Intervention> getFilteredInterventions() {
        return filteredInterventions;
    }

    public void setFilteredInterventions(List<Intervention> filterdInterventions) {
        this.filteredInterventions = filterdInterventions;
    }

    public List<Intervention> getInterventionList() {
        return interventionList;
    }

    public void setInterventionList(List<Intervention> interventionList) {
        this.interventionList = interventionList;
    }

    public Integer getNumberInterventions() {

        return numberInterventions;
    }
}
