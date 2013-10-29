/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author eric
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "contract_params")
public abstract class AbstractContractParam extends AbstractGenericEntity{
    
    //Echantillons
    private Integer nbSamples;
    
    private String sampleType;
    
    private String sampleConditioning;
    
    private String sampleTemperature;
    
    private Integer daysToDestructionSamples;
    
    //Planification
    private Integer daysTilReception;
    
    private Integer daysToAccomplish;
    
    //Rendu et sauvegarde
    private String resultsSentBy;
    
    private Integer daysOfBackupResults;

    public Integer getNbSamples() {
        return nbSamples;
    }

    public void setNbSamples(Integer nbSamples) {
        this.nbSamples = nbSamples;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getSampleConditioning() {
        return sampleConditioning;
    }

    public void setSampleConditioning(String sampleConditioning) {
        this.sampleConditioning = sampleConditioning;
    }

    public String getSampleTemperature() {
        return sampleTemperature;
    }

    public void setSampleTemperature(String sampleTemperature) {
        this.sampleTemperature = sampleTemperature;
    }

    public Integer getDaysToDestructionSamples() {
        return daysToDestructionSamples;
    }

    public void setDaysToDestructionSamples(Integer daysToDestructionSamples) {
        this.daysToDestructionSamples = daysToDestructionSamples;
    }

    public Integer getDaysTilReception() {
        return daysTilReception;
    }

    public void setDaysTilReception(Integer daysTilReception) {
        this.daysTilReception = daysTilReception;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.nbSamples);
        hash = 43 * hash + Objects.hashCode(this.sampleType);
        hash = 43 * hash + Objects.hashCode(this.sampleConditioning);
        hash = 43 * hash + Objects.hashCode(this.sampleTemperature);
        hash = 43 * hash + Objects.hashCode(this.daysToDestructionSamples);
        hash = 43 * hash + Objects.hashCode(this.daysTilReception);
        hash = 43 * hash + Objects.hashCode(this.daysToAccomplish);
        hash = 43 * hash + Objects.hashCode(this.resultsSentBy);
        hash = 43 * hash + Objects.hashCode(this.daysOfBackupResults);
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
        final AbstractContractParam other = (AbstractContractParam) obj;
        if (!Objects.equals(this.nbSamples, other.nbSamples)) {
            return false;
        }
        if (!Objects.equals(this.sampleType, other.sampleType)) {
            return false;
        }
        if (!Objects.equals(this.sampleConditioning, other.sampleConditioning)) {
            return false;
        }
        if (!Objects.equals(this.sampleTemperature, other.sampleTemperature)) {
            return false;
        }
        if (!Objects.equals(this.daysToDestructionSamples, other.daysToDestructionSamples)) {
            return false;
        }
        if (!Objects.equals(this.daysTilReception, other.daysTilReception)) {
            return false;
        }
        if (!Objects.equals(this.daysToAccomplish, other.daysToAccomplish)) {
            return false;
        }
        if (!Objects.equals(this.resultsSentBy, other.resultsSentBy)) {
            return false;
        }
        if (!Objects.equals(this.daysOfBackupResults, other.daysOfBackupResults)) {
            return false;
        }
        return true;
    }
}
