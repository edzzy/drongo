package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.*;
import fr.pfgen.lims.domain.people.Organism;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.service.FundingService;
import fr.pfgen.lims.service.OrganismService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.RedirectBean;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.*;

/**
 * Created by edouard on 06/03/14.
 */
@Component()
@Scope("view")
public class EditEquipmentBean implements Serializable{
   

    private Equipment equipment;
    private PlateformType plateformType;
    private EquipmentCategory equipmentCategory;
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

    public Equipment getequipment() {
        return equipment;
    }

    public void setequipment(Equipment equipment) {
        this.equipment = equipment;
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

        options[0] = new SelectItem("", FacesUtils.getI18nValueInMessages("label_select"));
        for(int i = 0; i < data.size(); i++) {
            options[i + 1] = new SelectItem(data.get(i).getLabel(), data.get(i).getLabel());
        }

        return options;
    }
    
     private SelectItem[] createFilterOptionsCategory(List<EquipmentCategory> data){
         SelectItem[] options = new SelectItem[data.size() + 1];

        options[0] = new SelectItem("", FacesUtils.getI18nValue("messages","label_select"));
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
    
    public EquipmentCategory[] getEquipmentCategoryTypes(){
        return EquipmentCategory.values();
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
              
            //Set<Funding> fundingSet = new HashSet<Funding>(fundings);
                
             //equipment.setFundings(fundingSet);
             
             equipmentService.updateEquipment(equipment);

             FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("equipment_updated"), equipment.toString(), FacesMessage.SEVERITY_INFO);
             
             FacesUtils.keepMessageInFlash();
            
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            System.out.print(e.getMessage());
            return null;
        }
        
        return redirectBean.getShowEquipment()+"id="+equipment.getId();

    }

    public void addFunding(ActionEvent event){

        Funding funding = new Funding();
        //funding.setEquipment(equipment);
        funding.setContext(context);
        funding.setOrganism(organism);
        funding.setPercent(percent);
        fundings.add(funding);
        context = "";
        organism = null;
        maxPercent = maxPercent - percent;
        percent = maxPercent;

    }

    public EquipmentCategory getEquipmentCategory() {
        return equipmentCategory;
    }

    public void setEquipmentCategory(EquipmentCategory equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }
    

    public List<Funding> getFundings() {
        return fundings;
    }

    public void setFundings(List<Funding> fundings) {
        this.fundings = fundings;
    }

}
