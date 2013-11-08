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

    YEAR("year", 365),
    MONTH("month", 30),
    WEEK("week", 7),
    DAY("day", 1);
    
    private final String type;
    private final int nbDays;

    private TimeType(String type, int nbDays) {
        this.type = type;
        this.nbDays = nbDays;
    }

    public String getType() {
        return type;
    }

    public int toDays(int multiplier) {
        return nbDays * multiplier;
    }
}
