/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.util;

/**
 *
 * @author eric
 */
public enum TimeType {

    YEAR("timeType_year", 365),
    MONTH("timeType_month", 30),
    WEEK("timeType_week", 7),
    DAY("timeType_day", 1);
    
    private final String label;
    private final int nbDays;

    private TimeType(String label, int nbDays) {
        this.label = label;
        this.nbDays = nbDays;
    }

    public String getLabel() {
        return label;
    }
    
    public int nbDays(){
        return nbDays;
    }

    public int toDays(int multiplier) {
        return nbDays * multiplier;
    }
}
