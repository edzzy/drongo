package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.repository.BillingAddressRepository;
import fr.pfgen.lims.repository.ClientRepository;
import fr.pfgen.lims.repository.ShippingAddressRepository;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    ShippingAddressRepository shippingAddressRepository;
    
    @Autowired
    BillingAddressRepository billingAddressRepository;

    @Override
    public long countAllClients() {
        return clientRepository.count();
    }

    @Override
    public void deleteClient(Client client) {
        client.setDeleted(true);
        clientRepository.save(client);
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
    public List<Client> findAllActiveClients(){
        return clientRepository.findByDeleted(false);
    }
    
    @Override
    public List<Client> findClientEntries(int firstResult, int maxResults) {
        return clientRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveClient(Client client) {
        client.setRegisteredOn(new Date());
        client.setFirstname(WordUtils.capitalizeFully(client.getFirstname(), '-', ' '));
        client.setLastname(WordUtils.capitalizeFully(client.getLastname(), '-', ' '));
        client.setEmail(client.getEmail().toLowerCase());
        
        clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        client.setFirstname(WordUtils.capitalizeFully(client.getFirstname(), '-', ' '));
        client.setLastname(WordUtils.capitalizeFully(client.getLastname(), '-', ' '));
        client.setEmail(client.getEmail().toLowerCase());
        return clientRepository.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);    
    }
}
