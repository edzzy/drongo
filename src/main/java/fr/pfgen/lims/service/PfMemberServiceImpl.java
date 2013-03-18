package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.PfMember;
import fr.pfgen.lims.repository.PfMemberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PfMemberServiceImpl implements PfMemberService {

	@Autowired
    PfMemberRepository pfMemberRepository;

	@Override
	public long countAllPfMembers() {
        return pfMemberRepository.count();
    }

	@Override
	public void deletePfMember(PfMember pfMember) {
        pfMemberRepository.delete(pfMember);
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
        pfMemberRepository.save(pfMember);
    }

	@Override
	public PfMember updatePfMember(PfMember pfMember) {
        return pfMemberRepository.save(pfMember);
    }
}
