package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.PfMember;
import java.util.List;

public interface PfMemberService {

    public abstract long countAllPfMembers();

    public abstract void deletePfMember(PfMember pfMember);

    public abstract PfMember findPfMember(Long id);

    public abstract List<PfMember> findAllPfMembers();
    
    public abstract List<PfMember> findAllActivePfMembers();

    public abstract List<PfMember> findPfMemberEntries(int firstResult, int maxResults);

    public abstract void savePfMember(PfMember pfMember);

    public abstract PfMember updatePfMember(PfMember pfMember);

    public abstract PfMember findByEmail(String email);
}
