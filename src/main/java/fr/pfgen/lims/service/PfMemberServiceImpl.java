package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.AppCredentials;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.repository.AppCredentialsRepository;
import fr.pfgen.lims.repository.PfMemberRepository;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PfMemberServiceImpl implements PfMemberService {

    @Autowired
    PfMemberRepository pfMemberRepository;
    
    @Autowired
    AppCredentialsRepository appCredentialsRepository;

    @Override
    public long countAllPfMembers() {
        return pfMemberRepository.count();
    }

    @Override
    public void deletePfMember(PfMember pfMember) {
        pfMember.setDeleted(true);
        pfMemberRepository.save(pfMember);
    }

    @Override
    public PfMember findPfMember(Long id) {
        return pfMemberRepository.findOne(id);
    }

    @Override
    public List<PfMember> findAllPfMembers() {
        return pfMemberRepository.findAll();
    }

    @Override
    public List<PfMember> findPfMemberEntries(int firstResult, int maxResults) {
        return pfMemberRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void savePfMember(PfMember pfMember) {
        pfMember.setFirstname(WordUtils.capitalizeFully(pfMember.getFirstname(), '-', ' '));
        pfMember.setLastname(WordUtils.capitalizeFully(pfMember.getLastname(), '-', ' '));
        pfMember.setEmail(pfMember.getEmail().toLowerCase());
        if (pfMember.getAppCredentials() != null){
            AppCredentials ac = appCredentialsRepository.findByLogin(pfMember.getAppCredentials().getLogin());
            if (ac != null){
                pfMember.setAppCredentials(ac);
            }else{
                pfMember.getAppCredentials().setSalt("saltTest");
                pfMember.setAppCredentials(appCredentialsRepository.save(pfMember.getAppCredentials()));
            }
        }
        
        pfMemberRepository.save(pfMember);
    }

    @Override
    public PfMember updatePfMember(PfMember pfMember) {
        pfMember.setFirstname(WordUtils.capitalizeFully(pfMember.getFirstname(), '-', ' '));
        pfMember.setLastname(WordUtils.capitalizeFully(pfMember.getLastname(), '-', ' '));
        pfMember.setEmail(pfMember.getEmail().toLowerCase());
        return pfMemberRepository.save(pfMember);
    }

    @Override
    public PfMember findByEmail(String email) {
        return pfMemberRepository.findByEmail(email);
    }

    @Override
    public List<PfMember> findAllActivePfMembers() {
        return pfMemberRepository.findByDeleted(false);
    }
}
