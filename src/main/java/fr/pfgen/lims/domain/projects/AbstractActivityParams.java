/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author eric
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "abstract_activity_params")
public abstract class AbstractActivityParams extends AbstractGenericEntity{
    
    @Size(max = 50)
    private String sampleType;
    
    private Integer daysToDestructionSamples;
    
    private Integer daysToAccomplish;

    @Size(max = 50)
    private String resultsSentBy;
    
    private Integer daysOfBackupResults;

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Integer getDaysToDestructionSamples() {
        return daysToDestructionSamples;
    }

    public void setDaysToDestructionSamples(Integer daysToDestructionSamples) {
        this.daysToDestructionSamples = daysToDestructionSamples;
    }
    
    public Integer getDaysToAccomplish() {
        return daysToAccomplish;
    }

    public void setDaysToAccomplish(Integer daysToAccomplish) {
        this.daysToAccomplish = daysToAccomplish;
    }

    public String getResultsSentBy() {
        return resultsSentBy;
    }

    public void setResultsSentBy(String resultsSentBy) {
        this.resultsSentBy = resultsSentBy;
    }

    public Integer getDaysOfBackupResults() {
        return daysOfBackupResults;
    }

    public void setDaysOfBackupResults(Integer daysOfBackupResults) {
        this.daysOfBackupResults = daysOfBackupResults;
    }
}
