package fr.pfgen.lims.domain.people;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
public class Client extends AbstractPerson{

    @ManyToOne
    private ClientType type;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Address billingAddress;
    
    @NotNull
    private boolean deleted = false;
    
    @ManyToOne
    private ResearchTeam researchTeam;
    
    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ResearchTeam getResearchTeam() {
        return researchTeam;
    }

    public void setResearchTeam(ResearchTeam researchTeam) {
        this.researchTeam = researchTeam;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ClientType getType() {
        return this.type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
