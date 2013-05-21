/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "shipping_addresses")
public class ShippingAddress extends Address{
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingAddress")
    private Set<Client> clients = new HashSet<>();

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
