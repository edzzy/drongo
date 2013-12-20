/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import fr.pfgen.lims.domain.util.TimeUtil;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    
    @Transient
    private TimeUtil tuTimeToDestructionSamples;
    
    private String timeToDestructionSamples;
    
    @Transient
    private TimeUtil tuTimeToAccomplish;
    
    private String timeToAccomplish;

    @Size(max = 50)
    private String resultsSentBy;
    
    @Transient
    private TimeUtil tuTimeOfBackupResults;
    
    private String timeOfBackupResults;

    @Override
    protected void onCreate() {
        super.onCreate();
        if (tuTimeOfBackupResults!=null){
            timeOfBackupResults = tuTimeOfBackupResults.toString();
        }
        if (tuTimeToAccomplish!=null){
            timeToAccomplish = tuTimeToAccomplish.toString();
        }
        if (tuTimeToDestructionSamples!=null){
            timeToDestructionSamples = tuTimeToDestructionSamples.toString();
        }
    }

    @Override
    protected void onUpdate() {
        super.onUpdate();
        if (tuTimeOfBackupResults!=null){
            timeOfBackupResults = tuTimeOfBackupResults.toString();
        }
        if (tuTimeToAccomplish!=null){
            timeToAccomplish = tuTimeToAccomplish.toString();
        }
        if (tuTimeToDestructionSamples!=null){
            timeToDestructionSamples = tuTimeToDestructionSamples.toString();
        }
    }
    
    @PostLoad
    private void postLoad(){
        if (timeOfBackupResults!=null && !timeOfBackupResults.isEmpty()){
            tuTimeOfBackupResults = TimeUtil.string2TimeUtil(timeOfBackupResults);
        }
        if (timeToAccomplish!=null && !timeToAccomplish.isEmpty()){
            tuTimeToAccomplish = TimeUtil.string2TimeUtil(timeToAccomplish);
        }
        if (timeToDestructionSamples!=null && !timeToDestructionSamples.isEmpty()){
            tuTimeToDestructionSamples = TimeUtil.string2TimeUtil(timeToDestructionSamples);
        }
    }

    public TimeUtil getTuTimeToDestructionSamples() {
        return tuTimeToDestructionSamples;
    }

    public void setTuTimeToDestructionSamples(TimeUtil tuTimeToDestructionSamples) {
        this.tuTimeToDestructionSamples = tuTimeToDestructionSamples;
    }

    public TimeUtil getTuTimeToAccomplish() {
        return tuTimeToAccomplish;
    }

    public void setTuTimeToAccomplish(TimeUtil tuTimeToAccomplish) {
        this.tuTimeToAccomplish = tuTimeToAccomplish;
    }

    public TimeUtil getTuTimeOfBackupResults() {
        return tuTimeOfBackupResults;
    }

    public void setTuTimeOfBackupResults(TimeUtil tuTimeOfBackupResults) {
        this.tuTimeOfBackupResults = tuTimeOfBackupResults;
    }
   
    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getResultsSentBy() {
        return resultsSentBy;
    }

    public void setResultsSentBy(String resultsSentBy) {
        this.resultsSentBy = resultsSentBy;
    }

    /*
    public String getTimeToDestructionSamples() {
        return timeToDestructionSamples;
    }
    

    public void setTimeToDestructionSamples(String timeToDestructionSamples) {
        this.timeToDestructionSamples = timeToDestructionSamples;
    }

    
    public String getTimeToAccomplish() {
        return timeToAccomplish;
    }
    

    public void setTimeToAccomplish(String timeToAccomplish) {
        this.timeToAccomplish = timeToAccomplish;
    }

    
    public String getTimeOfBackupResults() {
        return timeOfBackupResults;
    }
    

    public void setTimeOfBackupResults(String timeOfBackupResults) {
        this.timeOfBackupResults = timeOfBackupResults;
    }
    */ 
}
