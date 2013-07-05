/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "projects")
public class Project extends AbstractGenericEntity{
    
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date registeredOn;
      
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date begin_date;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date due_date;
    
    @NotNull
    @ManyToOne
    private Client responsable; 
    
    public Date getDue_date() {
        return due_date;
    }

    public Client getResponsable() {
        return responsable;
    }

    public void setResponsable(Client responsable) {
        this.responsable = responsable;
    }
    
    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
    
    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }
    
    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
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
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.responsable);
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
        return this.name +" " + this.responsable;
    }
}
