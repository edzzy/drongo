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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "reagent_batches")
public class ReagentBatch extends AbstractGenericEntity{
    
    @NotNull
    @Column(unique = true)
    @Size(min = 1,max = 50)
    private String batchNumber;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date expiryDate;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date receptionDate;
    
    @NotNull
    @Transient
    private boolean expired;
    
    @NotNull
    private boolean emptied;
    
    @ManyToOne
    @NotNull
    private Reagent reagent;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isEmptied() {
        return emptied;
    }

    public void setEmptied(boolean emptied) {
        this.emptied = emptied;
    }

    public Reagent getReagent() {
        return reagent;
    }

    public void setReagent(Reagent reagent) {
        this.reagent = reagent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.batchNumber);
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
        final ReagentBatch other = (ReagentBatch) obj;
        if (!Objects.equals(this.batchNumber, other.batchNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.reagent.getName()+ "("+this.batchNumber+")";
    }
}
