package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.AppCredentials;
import java.util.List;

public interface AppCredentialsService {

    public abstract long countAllAppCredentials();

    public abstract void deleteAppCredentials(AppCredentials appCredentials);

    public abstract AppCredentials findAppCredentials(Long id);
    
    public abstract AppCredentials findAppCredentialsByLogin(String login);

    public abstract List<AppCredentials> findAllAppCredentials();

    public abstract List<AppCredentials> findAppCredentialsEntries(int firstResult, int maxResults);

    public abstract void saveAppCredentials(AppCredentials appCredentials);

    public abstract AppCredentials updateAppCredentials(AppCredentials appCredentials);
}
