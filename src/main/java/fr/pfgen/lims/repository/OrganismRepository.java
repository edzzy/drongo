package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.people.Organism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by edouard on 12/03/14.
 */
@Repository
public interface OrganismRepository extends JpaSpecificationExecutor<Organism>, JpaRepository<Organism, Long> {

    public Organism findById(Long id);

    public List<Organism> findAll();
}
