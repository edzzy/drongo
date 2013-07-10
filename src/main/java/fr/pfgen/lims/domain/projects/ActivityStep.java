/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

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
@Table(name = "activity_steps")
public class ActivityStep extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 1,max = 50)
    private String name;
    
    @NotNull
    private int orderInWorkflow;

    public int getOrderInWorkflow() {
        return orderInWorkflow;
    }

    public void setOrderInWorkflow(int orderInWorkflow) {
        this.orderInWorkflow = orderInWorkflow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
