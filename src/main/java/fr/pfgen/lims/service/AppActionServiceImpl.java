package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.AppAction;
import fr.pfgen.lims.repository.AppActionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AppActionServiceImpl implements AppActionService {

	@Autowired
    AppActionRepository appActionRepository;

	@Override
	public long countAllAppActions() {
        return appActionRepository.count();
    }

	@Override
	public void deleteAppAction(AppAction appAction) {
        appActionRepository.delete(appAction);
    }

	@Override
	public AppAction findAppAction(Long id) {
        return appActionRepository.findOne(id);
    }

	@Override
	public List<AppAction> findAllAppActions() {
        return appActionRepository.findAll();
    }

	@Override
	public List<AppAction> findAppActionEntries(int firstResult, int maxResults) {
        return appActionRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	@Override
	public void saveAppAction(AppAction appAction) {
        appActionRepository.save(appAction);
    }

	@Override
	public AppAction updateAppAction(AppAction appAction) {
        return appActionRepository.save(appAction);
    }
}
