/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.util;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;

/**
 *
 * @author eric
 */
public class TimeUtil {
    
    private TimeType timeType;
    private Integer units;
    
    public TimeUtil(Integer units, TimeType type){
        this.timeType = type;
        this.units = units;
    };
    
    public Integer getNbDays(){
        if (this.timeType==null) return null;
        if (this.units==0) return 0;
        return this.timeType.toDays(units);
    }
    
    public static Date calculateEndDate(Date start, int nbDays){
        DateTime dateTime = start==null?null:new DateTime(start);
        if (dateTime==null) return null;
        return dateTime.plus(new Period(0, 0, 0, nbDays, 0, 0, 0, 0)).toDate();
    }
    
    public static int daysBetween(Date start, Date end){
        DateTime s = start==null?null:new DateTime(start);
        DateTime e = end==null?null:new DateTime(end);
        return Days.daysBetween(s, e).getDays();
    }
    
    public static TimeUtil days2TimeUtil(Integer days){
        if (days==null || days==0) return null;
        for (TimeType t : TimeType.values()) {
            if (days%t.nbDays()==0){
                return new TimeUtil(days/t.nbDays(), t);
            }
        }
        return null;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
}
