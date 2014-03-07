package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Funding;

import java.util.List;

/**
 * Created by edouard on 04/03/14.
 */
public interface FundingService {

    public abstract List<Funding> findAllFundings();


}
