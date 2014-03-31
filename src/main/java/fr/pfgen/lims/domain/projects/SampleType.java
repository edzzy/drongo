/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

/**
 *
 * @author eric
 */
public enum SampleType {
    DNA ("sampleType_dna"),
    RNA ("sampleType_rna"),
    PCRPRODUCT ("sampleType_pcrProduct"),
    PLASMID ("sampleType_plasmid"),
    DATAFILE ("sampleType_dataFile");
    
    private final String label;
    
    private SampleType(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
}
