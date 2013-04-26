package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaSpecificationExecutor<Client>, JpaRepository<Client, Long>{

    public Client findByEmail(String email);
    
}
