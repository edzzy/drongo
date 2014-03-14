package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import fr.pfgen.lims.domain.equipments.Funding;
import fr.pfgen.lims.domain.equipments.PlateformType;
import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.Organism;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.repository.FundingRepository;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.service.FundingService;
import fr.pfgen.lims.service.OrganismService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.RedirectBean;
import org.hibernate.sql.Select;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.*;

/**
 * Created by edouard on 06/03/14.
 */
@Component
@Scope("view")
public class CreateEquipmentBean implements Serializable{

    private Equipment newEquipment;
    private PlateformType plateformType;
    private List<String> manufacterList;
    private List<Organism> organisms;
    private Set<PfMember> responsable;
    private String context;
    private Double percent;
    private Organism organism;
    private List<Funding> fundings;
    private Double minPercent;
    private Double maxPercent;




    @Autowired
    EquipmentService equipmentService;
    @Autowired
    OrganismService organismService;
    @Autowired
    RedirectBean redirectBean;
    @Autowired
    FundingService fundingService;

    @PostConstruct
    public void init(){
        newEquipment = new Equipment();
        organisms = organismService.findAllOrganisms();
        fundings = new ArrayList<Funding>();
        minPercent = 1.0;
        maxPercent = 100.0;
        percent = 100.0;

    }

    public void initEquipment() {
        if (!FacesContext.getCurrentInstance().isPostback()) {


        }
    }

    public Equipment getNewEquipment() {
        return newEquipment;
    }

    public void setNewEquipment(Equipment newEquipment) {
        this.newEquipment = newEquipment;
    }

    public PlateformType getPlateformType() {
        return plateformType;
    }

    public void setPlateformType(PlateformType plateformType) {
        this.plateformType = plateformType;
    }

    public List<String> getManufacterList() {
        return manufacterList;
    }

    public void setManufacterList(List<String> manufacterList) {
        this.manufacterList = manufacterList;
    }

    public List<Organism> getOrganisms() {
        return organisms;

    }

    public void setOrganisms(List<Organism> organisms) {
        this.organisms = organisms;
    }

    public Set<PfMember> getResponsable() {
        return responsable;
    }

    public void setResponsable(Set<PfMember> responsable) {
        this.responsable = responsable;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getMinPercent() {
        return minPercent;
    }

    public void setMinPercent(Double minPercent) {
        this.minPercent = minPercent;
    }

    public Double getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(Double maxPercent) {
        this.maxPercent = maxPercent;
    }

    private SelectItem[] createFilterOptionsStatus(List<EquipmentStatus> data)  {
        SelectItem[] options = new SelectItem[data.size() + 1];

        options[0] = new SelectItem("", FacesUtils.getI18nValue("label_select"));
        for(int i = 0; i < data.size(); i++) {
            options[i + 1] = new SelectItem(data.get(i).getLabel(), data.get(i).getLabel());
        }

        return options;
    }

    public EquipmentStatus[] getEquipmentStatusTypes(){
        return EquipmentStatus.values();
    }

    public PlateformType[] getEquipmentPlateformTypes(){
        return PlateformType.values();
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public String saveEquipment(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (newEquipment.getId() == null) {
                Set<Funding> fundingSet = new HashSet<Funding>(fundings);

                newEquipment.setFundings(fundingSet);
                equipmentService.saveEquipment(newEquipment);

                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newEquipment_added"), newEquipment.toString(), FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            System.out.print(e.getMessage());
            return null;
        }
        return redirectBean.getShowEquipment()+"id="+newEquipment.getId();

    }

    public void addFunding(ActionEvent event){

        Funding funding = new Funding();
        //funding.setEquipment(newEquipment);
        funding.setContext(context);
        funding.setOrganism(organism);
        funding.setPercent(percent);
        fundings.add(funding);
        context = "";
        organism = null;
        maxPercent = maxPercent - percent;
        percent = maxPercent;

    }

    public List<Funding> getFundings() {
        return fundings;
    }

    public void setFundings(List<Funding> fundings) {
        this.fundings = fundings;
    }

}
