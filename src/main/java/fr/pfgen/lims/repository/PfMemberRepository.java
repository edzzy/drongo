package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.people.PfMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PfMemberRepository extends JpaRepository<PfMember, Long>, JpaSpecificationExecutor<PfMember> {
    
    public PfMember findByEmail(String email);
}
