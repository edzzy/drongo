package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.AppCredentials;
import fr.pfgen.lims.repository.AppCredentialsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppCredentialsServiceImpl implements AppCredentialsService {

    @Autowired
    AppCredentialsRepository appCredentialsRepository;

    @Override
    public long countAllAppCredentials() {
        return appCredentialsRepository.count();
    }

    @Override
    public void deleteAppCredentials(AppCredentials appCredentials) {
        appCredentialsRepository.delete(appCredentials);
    }

    @Override
    public AppCredentials findAppCredentials(Long id) {
        return appCredentialsRepository.findOne(id);
    }

    @Override
    public List<AppCredentials> findAllAppCredentials() {
        return appCredentialsRepository.findAll();
    }

    @Override
    public List<AppCredentials> findAppCredentialsEntries(int firstResult, int maxResults) {
        return appCredentialsRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveAppCredentials(AppCredentials appCredentials) {
        appCredentialsRepository.save(appCredentials);
    }

    @Override
    public AppCredentials updateAppCredentials(AppCredentials appCredentials) {
        return appCredentialsRepository.save(appCredentials);
    }

    @Override
    public AppCredentials findAppCredentialsByLogin(String login) {
        return appCredentialsRepository.findByLogin(login);
    }
}
