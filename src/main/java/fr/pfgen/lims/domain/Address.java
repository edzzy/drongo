/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain;

import java.io.Serializable;
import java.util.HashSet;
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

/**
 *
 * @author eric
 */
@Entity
@Table(name = "addresses")
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
   
    @NotNull
    @Column(unique = true)
    private String address;
    
    @Version
    @Column(name = "version")
    private Integer version;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.address;
    }
    
}
