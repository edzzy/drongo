package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Funding;
import fr.pfgen.lims.repository.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * Created by edouard on 14/03/14.
 */
@Service
@Transactional
public class FundingImpl implements FundingService{

    @Autowired
    FundingRepository fundingRepository;

    @Override
    public List<Funding> findAllFundings() {
        return null;
    }

    @Override
    public void saveFunding(Funding funding) {
        fundingRepository.save(funding);
    }
}
