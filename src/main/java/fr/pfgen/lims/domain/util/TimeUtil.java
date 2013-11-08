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
}
