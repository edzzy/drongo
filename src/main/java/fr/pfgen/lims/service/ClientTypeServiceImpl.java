package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.repository.ClientTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientTypeServiceImpl implements ClientTypeService {

    @Autowired
    ClientTypeRepository clientTypeRepository;

    @Override
    public long countAllClientTypes() {
        return clientTypeRepository.count();
    }

    @Override
    public void deleteClientType(ClientType clientType) {
        clientTypeRepository.delete(clientType);
    }

    @Override
    public ClientType findClientType(Long id) {
        return clientTypeRepository.findOne(id);
    }

    @Override
    public List<ClientType> findAllClientTypes() {
        return clientTypeRepository.findAll();
    }

    @Override
    public List<ClientType> findClientTypeEntries(int firstResult, int maxResults) {
        return clientTypeRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveClientType(ClientType clientType) {
        clientTypeRepository.save(clientType);
    }

    @Override
    public ClientType updateClientType(ClientType clientType) {
        return clientTypeRepository.save(clientType);
    }

    @Override
    public ClientType findByName(String value) {
        return clientTypeRepository.findOneByName(value);
    }
}
