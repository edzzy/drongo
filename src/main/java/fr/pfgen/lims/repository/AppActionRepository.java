package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.people.AppAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppActionRepository extends JpaSpecificationExecutor<AppAction>, JpaRepository<AppAction, Long> {
}
