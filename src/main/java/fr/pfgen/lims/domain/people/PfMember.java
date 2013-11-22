package fr.pfgen.lims.domain.people;

import fr.pfgen.lims.domain.projects.Contract;
import fr.pfgen.lims.domain.runs.AbstractRun;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pf_members")
public class PfMember extends AbstractPerson{

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date memberSince;
    
    @NotNull
    private boolean deleted = false;
    
    @NotNull
    @Size(max = 10)
    private String office;
    
    @OneToMany(mappedBy = "launchedBy")
    private Set<AbstractRun> runs;

    public Set<AbstractRun> getRuns() {
        return runs;
    }

    public void setRuns(Set<AbstractRun> runs) {
        this.runs = runs;
    }
    
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    public Date getMemberSince() {
        return this.memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
