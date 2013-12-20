/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

/**
 *
 * @author eric
 */
public enum ResultsSentByType {
    HARDDRIVE ("sentByType_hardDrive"),
    USBKEY ("sentByType_usbKey"),
    MAIL ("sentByType_mail"),
    FTP ("sentByType_ftp");
    
    private final String label;
    
    private ResultsSentByType(String label){
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
}
