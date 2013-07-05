package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Client;
import java.util.List;

public interface ClientService {

    public abstract long countAllClients();

    public abstract void deleteClient(Client client);

    public abstract Client findClient(Long id);

    public abstract List<Client> findAllClients();
    
    public abstract List<Client> findAllActiveClients();

    public abstract List<Client> findClientEntries(int firstResult, int maxResults);

    public abstract void saveClient(Client client);

    public abstract Client updateClient(Client client);

    public abstract Client findByEmail(String email);
   
}
