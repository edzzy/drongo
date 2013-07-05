package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
public abstract class AbstractPerson extends AbstractGenericEntity{

    @NotNull
    @Size(min = 2, max = 30)
    private String firstname;
    
    @NotNull
    @Size(min = 2, max = 30)
    private String lastname;
    
    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 60)
    private String email;
    
    @Size(max = 16)
    private String phone;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date registeredOn;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private AppCredentials appCredentials;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppAction> appActions = new HashSet<>();

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegisteredOn() {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public AppCredentials getAppCredentials() {
        return this.appCredentials;
    }

    public void setAppCredentials(AppCredentials appCredentials) {
        this.appCredentials = appCredentials;
    }

    public Set<AppAction> getAppActions() {
        return this.appActions;
    }

    public void setAppActions(Set<AppAction> appActions) {
        this.appActions = appActions;
    }

    @Override
    public String toString() {
        return this.getFirstname()+" "+this.getFirstname();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.email);
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
        final AbstractPerson other = (AbstractPerson) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
}
