package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Organism;
import fr.pfgen.lims.repository.OrganismRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by edouard on 12/03/14.
 */
@Service
@Transactional
public class OrganismServiceImpl implements  OrganismService{

    @Autowired
    OrganismRepository organismRepository;

    @Override
    public Organism findOrganismById(Long id) {

        return organismRepository.findById(id);
    }

    @Override
    public List<Organism> findAllOrganisms() {
        return organismRepository.findAll();
    }
}
