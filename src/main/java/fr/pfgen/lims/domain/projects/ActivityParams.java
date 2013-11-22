/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "activity_params")
public class ActivityParams extends AbstractActivityParams{
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduledSampleReceptionDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduledBeginDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduledDueDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date beginDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date endDate;
    
    @OneToOne(optional = false, mappedBy = "activityParams", cascade = CascadeType.ALL)
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    
    public Date getScheduledBeginDate() {
        return scheduledBeginDate;
    }

    public Date getScheduledSampleReceptionDate() {
        return scheduledSampleReceptionDate;
    }

    public void setScheduledSampleReceptionDate(Date scheduledSampleReceptionDate) {
        this.scheduledSampleReceptionDate = scheduledSampleReceptionDate;
    }

    public void setScheduledBeginDate(Date scheduledBeginDate) {
        this.scheduledBeginDate = scheduledBeginDate;
    }

    public Date getScheduledDueDate() {
        return scheduledDueDate;
    }

    public void setScheduledDueDate(Date scheduledDueDate) {
        this.scheduledDueDate = scheduledDueDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
