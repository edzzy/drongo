/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

/**
 *
 * @author eric
 */
public enum ActivityType {
    EXPERIMENTAL ("applicationType_experimental"),
    ANALYSIS ("applicationType_analysis"),
    BOTH ("applicationType_expAndAna");
    
    private final String label;
    
    private ActivityType(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
}
