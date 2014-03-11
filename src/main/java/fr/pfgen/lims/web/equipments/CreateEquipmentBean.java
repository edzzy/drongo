package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import fr.pfgen.lims.domain.equipments.PlateformType;
import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.Organism;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import org.hibernate.sql.Select;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by edouard on 06/03/14.
 */
@Component
@Scope("view")
public class CreateEquipmentBean implements Serializable{

    private Equipment newEquipment;
    private PlateformType plateformType;
    private String name;
    private String manufacter;
    private List<String> manufacterList;
    private Date acquisitionDate;
    private Date constructedDate;
    private Double cost;
    private String room;
    private String serialNumber;
    private String internalNumber;
    private String ifrNumber;
    private String itx;
    private Double maintenanceCost;
    private Set<Organism> organisms;
    private String description;
    private String maintenanceCharge;
    private Boolean convention;
    private String comment;
    private Date maintenance_date;
    private String warranty;
    private String sav;
    private String software;
    private String licence;
    private SelectItem[] statusOption;
    private Set<PfMember> responsable;
    private List<EquipmentStatus> equipmentStatusList;
    private String wizStep;


    @Autowired
    EquipmentService equipmentService;

    @PostConstruct
    public void init(){

        equipmentStatusList = Arrays.asList(EquipmentStatus.values()) ;
        statusOption = createFilterOptionsStatus(equipmentStatusList);
    }

    public void initEquipment() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            newEquipment = (Equipment) FacesUtils.getObjectInSessionMap("equipment");

            wizStep = (String) FacesUtils.getObjectInSessionMap("wizStep");

            FacesUtils.removeObjectFromSessionMap("wizStep");

            if (wizStep == null) {
                wizStep = "descriptionTab";
            }
            newEquipment = new Equipment();
            FacesUtils.putObjectInSessionMap("equipment", newEquipment);
            wizStep = "descriptionTab";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacter() {
        return manufacter;
    }

    public void setManufacter(String manufacter) {
        this.manufacter = manufacter;
    }

    public List<String> getManufacterList() {
        return manufacterList;
    }

    public void setManufacterList(List<String> manufacterList) {
        this.manufacterList = manufacterList;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public Date getConstructedDate() {
        return constructedDate;
    }

    public void setConstructedDate(Date constructedDate) {
        this.constructedDate = constructedDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getIfrNumber() {
        return ifrNumber;
    }

    public void setIfrNumber(String ifrNumber) {
        this.ifrNumber = ifrNumber;
    }

    public String getItx() {
        return itx;
    }

    public void setItx(String itx) {
        this.itx = itx;
    }

    public Double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Double maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public Set<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(Set<Organism> organisms) {
        this.organisms = organisms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaintenanceCharge() {
        return maintenanceCharge;
    }

    public void setMaintenanceCharge(String maintenanceCharge) {
        this.maintenanceCharge = maintenanceCharge;
    }

    public Boolean getConvention() {
        return convention;
    }

    public void setConvention(Boolean convention) {
        this.convention = convention;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getMaintenance_date() {
        return maintenance_date;
    }

    public void setMaintenance_date(Date maintenance_date) {
        this.maintenance_date = maintenance_date;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getSav() {
        return sav;
    }

    public void setSav(String sav) {
        this.sav = sav;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }


    public Set<PfMember> getResponsable() {
        return responsable;
    }

    public void setResponsable(Set<PfMember> responsable) {
        this.responsable = responsable;
    }

    public String getWizStep() {
        return wizStep;
    }

    public void setWizStep(String wizStep) {
        this.wizStep = wizStep;
    }

    public List<EquipmentStatus> getEquipmentStatusList() {
        return equipmentStatusList;
    }

    public void setEquipmentStatusList(List<EquipmentStatus> equipmentStatusList) {
        this.equipmentStatusList = equipmentStatusList;
    }

    public String onFlowProcess(FlowEvent event){


        return event.getNewStep();
    }

    public SelectItem[] getStatusOption() {
        return statusOption;
    }

    public void setStatusOption(SelectItem[] statusOption) {
        this.statusOption = statusOption;
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
}
