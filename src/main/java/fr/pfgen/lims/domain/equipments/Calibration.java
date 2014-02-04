/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pfgen.lims.domain.equipments;

import java.text.SimpleDateFormat;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
/**
 *
 * @author edouard
 */
@EnableScheduling
public class Calibration extends Intervention{
    
    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Pipette pipette;
    
    private SimpleDateFormat newCalibrateDate = new SimpleDateFormat("yy:MM");
    
    
    @Scheduled(cron = "0 0 1 1-12/6 ")
    public void rememberCalibration(){
    
        
        
    }

    public Pipette getPipette() {
        return pipette;
    }

    public void setPipette(Pipette pipette) {
        this.pipette = pipette;
    }

    public SimpleDateFormat getNewCalibrateDate() {
        return newCalibrateDate;
    }

    public void setNewCalibrateDate(SimpleDateFormat newCalibrateDate) {
        this.newCalibrateDate = newCalibrateDate;
    }
}
