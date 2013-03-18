package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
    ClientRepository clientRepository;

	@Override
	public long countAllClients() {
        return clientRepository.count();
    }

	@Override
	public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

	@Override
	public Client findClient(Long id) {
        return clientRepository.findOne(id);
    }

	@Override
	public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

	@Override
	public List<Client> findClientEntries(int firstResult, int maxResults) {
        return clientRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	@Override
	public void saveClient(Client client) {
        clientRepository.save(client);
    }

	@Override
	public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
}
