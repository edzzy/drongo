/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.equipments;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "run_devices")
public class RunDevice extends Equipment implements Serializable{

    //TODO Remove image attribut

    @NotNull
    @Lob
    private byte[] image;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usedInDevice")
    private Set<Reagent> reagents;
    
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Reagent> getReagents() {
        return reagents;
    }

    public void setReagents(Set<Reagent> reagents) {
        this.reagents = reagents;
    }
    
    
}
