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
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 *
 * @author edouard
 */
@Entity
@Table(name = "sampleQuality")
public class SampleQuality extends AbstractGenericEntity{
    /*
    @NotNull
    @OneToOne
    @Size(min=2, max=50)
    private String name;
    */
    
}
