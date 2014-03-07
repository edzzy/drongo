package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Funding;
import fr.pfgen.lims.domain.people.Organism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by edouard on 04/03/14.
 */
@Repository
public interface FundingRepository extends JpaSpecificationExecutor<Funding>, JpaRepository<Funding, Long> {

    public List<Funding> findByEquipment(Equipment equipment);

    public List<Funding> findByOrganism(Organism organism);
}
