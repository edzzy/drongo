package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.AppAction;
import java.util.List;

public interface AppActionService {

	public abstract long countAllAppActions();


	public abstract void deleteAppAction(AppAction appAction);


	public abstract AppAction findAppAction(Long id);


	public abstract List<AppAction> findAllAppActions();


	public abstract List<AppAction> findAppActionEntries(int firstResult, int maxResults);


	public abstract void saveAppAction(AppAction appAction);


	public abstract AppAction updateAppAction(AppAction appAction);

}
