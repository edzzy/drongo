/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.equipments;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public abstract class Equipment extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @NotNull
    @Size(max = 50)
    private String Manufacturer;
    
    @Size(max = 50)
    private String type;
    
    @NotNull
    @Column(unique = true)
    @Size(max = 50)
    private String serialNumber;
    
    @NotNull
    @Column(unique = true)
    @Size(max = 7)
    private String internalNumber;
    
    @NotNull
    @Size(max = 15)
    private String room;
    
    @Column(unique = true)
    @Size(max = 7)
    private String itx;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date acquisitionDate;
    
    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

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

    public String getItx() {
        return itx;
    }

    public void setItx(String itx) {
        this.itx = itx;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.serialNumber);
        return hash;
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

    @Override
    public String toString() {
        return this.name+" ("+this.internalNumber+")";
    }
}
