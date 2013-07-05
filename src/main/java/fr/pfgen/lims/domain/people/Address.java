/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "addresses")
public class Address extends AbstractGenericEntity{
   
    @NotNull
    @Column(unique = true)
    private String address;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingAddress")
    private Set<Client> shipToClients = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billingAddress")
    private Set<Client> billToClients = new HashSet<>();

    public Set<Client> getShipToClients() {
        return shipToClients;
    }

    public void setShipToClients(Set<Client> shipToClients) {
        this.shipToClients = shipToClients;
    }

    public Set<Client> getBillToClients() {
        return billToClients;
    }

    public void setBillToClients(Set<Client> billToClients) {
        this.billToClients = billToClients;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.address);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.address;
    }
}
