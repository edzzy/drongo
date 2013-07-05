package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Address;
import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.repository.AddressRespository;
import fr.pfgen.lims.repository.ClientRepository;
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
    AddressRespository addressRepository;

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
        
        if (client.getBillingAddress() != null){
            Address ad = addressRepository.findByAddress(client.getBillingAddress().getAddress());
            if (ad != null){
                client.setBillingAddress(ad);
            }else{
                client.setBillingAddress(addressRepository.save(client.getBillingAddress()));
            }
        }
        
        if (client.getShippingAddress()!= null){
            Address ad = addressRepository.findByAddress(client.getShippingAddress().getAddress());
            if (ad != null){
                client.setShippingAddress(ad);
            }else{
                client.setShippingAddress(addressRepository.save(client.getShippingAddress()));
            }
        }
        
        clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        client.setFirstname(WordUtils.capitalizeFully(client.getFirstname(), '-', ' '));
        client.setLastname(WordUtils.capitalizeFully(client.getLastname(), '-', ' '));
        client.setEmail(client.getEmail().toLowerCase());
        
        if (client.getBillingAddress() != null){
            Address ad = addressRepository.findByAddress(client.getBillingAddress().getAddress());
            if (ad != null){
                client.setBillingAddress(ad);
            }else{
                client.setBillingAddress(addressRepository.save(client.getBillingAddress()));
            }
        }
        
        if (client.getShippingAddress()!= null){
            Address ad = addressRepository.findByAddress(client.getShippingAddress().getAddress());
            if (ad != null){
                client.setShippingAddress(ad);
            }else{
                client.setShippingAddress(addressRepository.save(client.getShippingAddress()));
            }
        }
        
        return clientRepository.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);    
    }
}
