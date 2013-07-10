/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.samples;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "samples")
public class Sample extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 1,max = 50)
    private String name;
    
}
