package fr.pfgen.lims.domain.people;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pf_members")
public class PfMember extends AbstractPerson {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date memberSince;

    @Override
    public String toString() {
        return this.getFirstname()+" "+this.getLastname();
    }

    public Date getMemberSince() {
        return this.memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }
}
