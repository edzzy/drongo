package fr.pfgen.lims.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Client extends AbstractPerson {

    @ManyToOne
    private ClientType type;

    @Override
    public String toString() {
        return this.getFirstname()+" "+this.getLastname();
    }

    public ClientType getType() {
        return this.type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }
}
