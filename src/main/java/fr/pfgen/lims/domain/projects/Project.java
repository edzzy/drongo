/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    
    @NotNull
    @ManyToOne
    private Client mainClient; 

    @ManyToMany
    private Set<Client> clients;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Contract> contracts;

    
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Client getMainClient() {
        return mainClient;
    }

    public void setMainClient(Client mainClient) {
        this.mainClient = mainClient;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.mainClient);
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
        if (!Objects.equals(this.mainClient, other.mainClient)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.name + " - " + this.mainClient;
    }
}
