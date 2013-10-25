package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.projects.Contract;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainClient")
    private Set<Contract> mainClientForContracts;
    
    @ManyToMany
    @JoinTable(
      name="contracts_clients",
      joinColumns={@JoinColumn(name="client_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="contract_id", referencedColumnName="id")})
    private Set<Contract> involvedInContracts;

    public Set<Contract> getMainClientForContracts() {
        return mainClientForContracts;
    }

    public void setMainClientForContracts(Set<Contract> mainClientForContracts) {
        this.mainClientForContracts = mainClientForContracts;
    }

    public Set<Contract> getInvolvedInContracts() {
        return involvedInContracts;
    }

    public void setInvolvedInContracts(Set<Contract> involvedInContracts) {
        this.involvedInContracts = involvedInContracts;
    }
    
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
        if (this.company != null){
            return super.toString()+" ("+this.company+")";
        }else if (this.researchTeam != null){
            return super.toString()+" ("+this.researchTeam+")";
        }else{
            return super.toString();
        }
    }
}
