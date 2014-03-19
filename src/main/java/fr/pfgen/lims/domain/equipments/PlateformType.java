/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pfgen.lims.domain.equipments;

/**
 *
 * @author edouard
 */
public enum PlateformType {
    
    GENOMIQUE("Genomique"),
    GENETIQUE("Genetique"),
    BIOINFO("Bioinfo");
    
    private final String label;
    
    private PlateformType(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
            
        
}
