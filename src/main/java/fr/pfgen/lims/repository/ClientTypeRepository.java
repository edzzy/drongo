package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Long>, JpaSpecificationExecutor<ClientType> {
}
