package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Organism;

import java.util.List;
import java.util.Set;

/**
 * Created by edouard on 12/03/14.
 */
public interface OrganismService {

    public abstract Organism findOrganismById(Long aLong);

    public abstract List<Organism> findAllOrganisms();
}
