/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

/**
 *
 * @author eric
 */
public enum ApplicationType {
    EXPERIMENTAL ("applicationType_experimental"),
    BIOINFORMATICS ("applicationType_bioinformatics");
    
    private final String label;
    
    private ApplicationType(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
}
