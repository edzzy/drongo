/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.equipments;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "reagents")
public class Reagent extends AbstractGenericEntity{
    
    @NotNull
    @Column(unique = true)
    @Size(min = 2,max = 30)
    private String name;
    
    @ManyToOne
    private RunDevice usedInDevice;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reagent")
    private Set<ReagentBatch> batches;

    public Set<ReagentBatch> getBatches() {
        return batches;
    }

    public void setBatches(Set<ReagentBatch> batches) {
        this.batches = batches;
    }

    public RunDevice getUsedInDevice() {
        return usedInDevice;
    }

    public void setUsedInDevice(RunDevice usedInDevice) {
        this.usedInDevice = usedInDevice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Reagent other = (Reagent) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
