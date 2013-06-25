package fr.pfgen.lims.domain.people;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_credentials")
public class AppCredentials implements Serializable {

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 30)
    private String login;
    
    @NotNull
    private String password;
    
    @NotNull
    private String salt;
    
    private String appTheme;
    
    private String appLocale;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appCredentials")
    private Set<AbstractPerson> persons = new HashSet<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return this.login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAppTheme() {
        return this.appTheme;
    }

    public void setAppTheme(String appTheme) {
        this.appTheme = appTheme;
    }

    public String getAppLocale() {
        return this.appLocale;
    }

    public void setAppLocale(String appLocale) {
        this.appLocale = appLocale;
    }

    public Set<AbstractPerson> getPersons() {
        return this.persons;
    }

    public void setPersons(Set<AbstractPerson> persons) {
        this.persons = persons;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final AppCredentials other = (AppCredentials) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
