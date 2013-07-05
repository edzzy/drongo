package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.ClientType;
import java.util.List;

public interface ClientTypeService {

    public abstract long countAllClientTypes();

    public abstract void deleteClientType(ClientType clientType);

    public abstract ClientType findClientType(Long id);

    public abstract List<ClientType> findAllClientTypes();

    public abstract List<ClientType> findClientTypeEntries(int firstResult, int maxResults);

    public abstract void saveClientType(ClientType clientType);

    public abstract ClientType updateClientType(ClientType clientType);

    public ClientType findByName(String value);
}
