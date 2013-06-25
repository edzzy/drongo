package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.people.AppCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCredentialsRepository extends JpaSpecificationExecutor<AppCredentials>, JpaRepository<AppCredentials, Long> {

    public AppCredentials findByLogin(String login);
}
