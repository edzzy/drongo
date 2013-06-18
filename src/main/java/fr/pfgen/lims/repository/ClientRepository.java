package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.people.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaSpecificationExecutor<Client>, JpaRepository<Client, Long>{

    public Client findByEmail(String email);
    
    public List<Client> findByDeleted(Boolean deleted);
    
}
