/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.equipments;

import fr.pfgen.lims.domain.documents.Document;
import fr.pfgen.lims.domain.people.Organism;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.Structure;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author eric
 */
@Entity

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "equipments")
public class Equipment extends AbstractGenericEntity{


    
    
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    

    @NotNull
    @Size(max = 50)
    private String manufacturer;
    
    @Size(max = 50)
    private String modele;
    
    @Column
    @Size(max = 50)
    private String serialNumber;
    
    @NotNull
    @Size(max = 50)
    private String room;
      
    @Column
    @Size(max = 7)
    private String itx;
   
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date acquisitionDate;

    private String description;

    private String comment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment")
    private Set<Intervention> interventions;

    // pas utile à voir

    @Column
    @Size(max = 20)
    private String internalNumber;

    @Column
    private String ifrNumber;


    
    
    //A deplacer dans d'autre classe

    @NotNull
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @Enumerated(EnumType.STRING)
    private EquipmentCategory category;
    
    @Min(value = 0)
    private Double cost;

    @Min(value = 0)
    private Double maintenanceCost;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MJ")
    private Date maintenance_date;

    @ManyToMany
    private Set<Organism> organisms;

    @ManyToMany
    private Set<Structure> structures;


    @ManyToOne
    private PfMember ref;
    
    @ManyToOne
    private PfMember sup;
    
    private boolean isMAD;
    
    private String maintenanceCharge;

    private Boolean convention;


    @Size(max = 50)
    private String warranty;

    @Size(max = 50)
    private String sav;

    private String software;

    private String licence;

    
    @ManyToOne
    private PfMember responsable;
    
    @ManyToOne
    private PfMember subResponsable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment")
    private Set<Funding> fundings;
    
       
    
    private Maintenance maintenance;
    

/*
    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }
*/
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

   

    public Set<Structure> getStructures() {
        return structures;
    }

    public void setStructures(Set<Structure> structures) {
        this.structures = structures;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

   
    public Set<Intervention> getInterventions() {
        return interventions;
    }

 
    public void setInterventions(Set<Intervention> interventions) {
        this.interventions = interventions;
    }

    public Double getMaintenance_cost() {
        return maintenanceCost;
    }

    public void setMaintenance_cost(Double maintenance_cost) {
        this.maintenanceCost = maintenance_cost;
    }

    public String getMainteance_charge() {
        return maintenanceCharge;
    }

    public void setMainteance_charge(String mainteance_charge) {
        this.maintenanceCharge = mainteance_charge;
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

    public String getMaintenanceCharge() {
        return maintenanceCharge;
    }

    public void setMaintenanceCharge(String maintenanceCharge) {
        this.maintenanceCharge = maintenanceCharge;
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
/*
    public String getIfrNumber() {
        return ifrNumber;
    }

    public void setIfrNumber(String ifrNumber) {
        this.ifrNumber = ifrNumber;
    }
*/
    public String getSofware() {
        return software;
    }

    public void setSofware(String sofware) {
        this.software = sofware;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Double maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public EquipmentCategory getCategory() {
        return category;
    }

    public void setCategory(EquipmentCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainteanceCharge() {
        return maintenanceCharge;
    }

    public void setMainteanceCharge(String mainteanceCharge) {
        this.maintenanceCharge = mainteanceCharge;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public Set<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(Set<Organism> organisms) {
        this.organisms = organisms;
    }

    public PfMember getRef() {
        return ref;
    }

    public void setRef(PfMember ref) {
        this.ref = ref;
    }

    public PfMember getSup() {
        return sup;
    }

    public void setSup(PfMember sup) {
        this.sup = sup;
    }

    public boolean isIsMAD() {
        return isMAD;
    }

    public void setIsMAD(boolean isMAD) {
        this.isMAD = isMAD;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.serialNumber);
        return hash;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipment other = (Equipment) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        return true;
    }

    public Set<Funding> getFundings() {
        return fundings;
    }

    public void setFundings(Set<Funding> fundings) {
        this.fundings = fundings;
    }

    @Override
    public String toString() {
        return this.name+" ("+serialNumber+")";
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    public String getItx() {
        return itx;
    }

    public void setItx(String itx) {
        this.itx = itx;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
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

    public PfMember getResponsable() {
        return responsable;
    }

    public void setResponsable(PfMember responsable) {
        this.responsable = responsable;
    }

    public PfMember getSubResponsable() {
        return subResponsable;
    }

    public void setSubResponsable(PfMember subResponsable) {
        this.subResponsable = subResponsable;
    }
    
    
    
}
