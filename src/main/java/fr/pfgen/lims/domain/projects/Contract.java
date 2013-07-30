/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 *
 * @author edouard
 */
@Entity
@Table(name="contracts")
public class Contract extends AbstractGenericEntity{
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date signatureDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date beginDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date endDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date dueDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduleBeginDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date scheduleDueDate;
    
    @NotNull
    private ContractStatus status;
    
    @NotNull
    @ManyToOne
    private Project project;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private Set<Activity> activities;
    
    @NotNull
    private int contractNumber;

    public Date getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(Date signatureDate) {
        this.signatureDate = signatureDate;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }
    
}
