/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "application_params")
public class ApplicationParams extends AbstractApplicationParams{
    
    @ManyToOne
    private Activity experimentalActivity;
    
    @ManyToOne
    private Activity analysisActivity; 

    public Activity getExperimentalActivity() {
        return experimentalActivity;
    }

    public void setExperimentalActivity(Activity experimentalActivity) {
        this.experimentalActivity = experimentalActivity;
    }

    public Activity getAnalysisActivity() {
        return analysisActivity;
    }

    public void setAnalysisActivity(Activity analysisActivity) {
        this.analysisActivity = analysisActivity;
    }      
}
