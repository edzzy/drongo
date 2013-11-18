/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.util.AbstractGenericEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
/**
 *
 * @author edouard
 */
@Entity
@Table(name="contracts")
public class Contract extends AbstractGenericEntity{
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date signatureDate;
    
    @NotNull
    @Column(unique = true)
    private String contractNumber;
    
    @ManyToOne
    @NotNull
    private Client mainClient;
    
    @NotNull
    @Size(min = 3,max = 100)
    private String title;
    
    @ManyToMany(mappedBy = "involvedInContracts")
    private Set<Client> involvedClients;
    
    @Lob
    @NotNull
    private String description;
    
    @NotNull
    @ManyToOne
    private PfMember pilot;
    
    @NotNull
    @Size(min = 2,max = 20)
    private String keyword;
    
    @Size(max = 50)
    private String reminderKeywords;
    
    @NotNull
    private ContractStatus status = ContractStatus.PENDING;

    public String getReminderKeywords() {
        return reminderKeywords;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public void setReminderKeywords(String reminderKeywords) {
        this.reminderKeywords = reminderKeywords;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public PfMember getPilot() {
        return pilot;
    }

    public void setPilot(PfMember pilot) {
        this.pilot = pilot;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSigned() {
        if (signatureDate == null){
            return false;
        }else{
            return true;
        }
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Client getMainClient() {
        return mainClient;
    }

    public void setMainClient(Client mainClient) {
        this.mainClient = mainClient;
    }

    public Set<Client> getInvolvedClients() {
        return involvedClients;
    }

    public void setInvolvedClients(Set<Client> involvedClients) {
        this.involvedClients = involvedClients;
    }

    public Date getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(Date signatureDate) {
        this.signatureDate = signatureDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.contractNumber);
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
        final Contract other = (Contract) obj;
        if (!Objects.equals(this.contractNumber, other.contractNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.contractNumber;
    }
}
