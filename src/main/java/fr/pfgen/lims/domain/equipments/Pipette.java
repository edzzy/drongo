/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pfgen.lims.domain.equipments;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author edouard
 */
public class Pipette extends Equipment {
    
    
    private boolean toCalibrate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pipette")
    private List<Calibration> calibrations;
    
    
     @ManyToOne
    private EquipmentCategory category;

    public boolean isToCalibrate() {
        return toCalibrate;
    }

    public void setToCalibrate(boolean toCalibrate) {
        this.toCalibrate = toCalibrate;
    }

    public List<Calibration> getCalibrations() {
        return calibrations;
    }

    public void setCalibrations(List<Calibration> calibrations) {
        this.calibrations = calibrations;
    }
    
    
    
}
