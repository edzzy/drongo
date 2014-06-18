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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author edouard
 */

public enum EquipmentCategory {
    
    MISC("Diver"),
    SCANNER("Scanner"),
    SEQUENCER("Sequencer"),
    AUTOMATE("Automates"),
    QC("QC"),
    ETUVE("Etuves/Bain-maire"),
    CENTRI("Centrifugeuse"),
    WET("Equipement paillasse"),
    COLD("Froid")
    ;
    
    private final String label;
    
    private EquipmentCategory(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
 
}
