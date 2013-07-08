/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author edouard
 */
@Entity
@Table(name = "projects")
public class Project extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date signatureDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date beginDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date endDate;
    
    @NotNull
    @ManyToOne
    private Client responsable; 

    @ManyToMany
    private Set<Client> clients;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Activity> activities;

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
    
    public Client getResponsable() {
        return responsable;
    }

    public void setResponsable(Client responsable) {
        this.responsable = responsable;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.responsable);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.responsable, other.responsable)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.name + " - " + this.responsable;
    }
}
