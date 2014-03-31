/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.project;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityStep;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.domain.projects.Contract;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.service.ProjectService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.ContractFlow;
import fr.pfgen.lims.web.util.flows.FlowType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("view")
public class ContractBean extends ContractFlow implements Serializable {

    @Autowired
    ProjectService projectService;
    @Autowired
    ClientService clientService;
    @Autowired
    PfMemberService pfMemberService;
    @Autowired
    ApplicationService applicationService;
    private Contract contract = new Contract();
    private List<Client> clientList;
    private List<Client> clientSource = new ArrayList<>();
    private List<Client> clientTarget = new ArrayList<>();
    private DualListModel<Client> allClients;
    private List<PfMember> pfMemberList;
    private String wizStep;
    private List<SelectItem> availableApplications;
    private Application selectedApplication;
    private Activity selectedActivity;
    private List<Activity> activityList;
    private List<ActivityStep> activitySteps;

    public void initContract() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            contract = (Contract) FacesUtils.getObjectInSessionMap("contract");
            wizStep = (String) FacesUtils.getObjectInSessionMap("wizStep");

            FacesUtils.removeObjectFromSessionMap("wizStep");

            if (wizStep == null) {
                wizStep = "clientsTab";
            }

            if (contract == null) {
                contract = new Contract();
                FacesUtils.putObjectInSessionMap("contract", contract);
                wizStep = "clientsTab";
            }

            if (contract.getMainClient() != null) {
                clientSource.clear();
                clientSource.addAll(clientList);
                clientSource.remove(contract.getMainClient());
            }
        }
    }

    @PostConstruct
    public void init() {
        populateApplicationLists();
        clientList = clientService.findAllActiveClients();
        pfMemberList = pfMemberService.findAllActivePfMembers();
        clientSource.addAll(clientList);
        allClients = new DualListModel<>(clientSource, clientTarget);
    }

    public void onMainClientSelect(AjaxBehaviorEvent event) {
        clientSource.clear();
        clientSource.addAll(clientList);
        clientSource.remove((Client) ((UIOutput) event.getSource()).getValue());
    }

    public String createNewClient() {
        return enterFlow(FlowType.CLIENT);
    }

    public String createNewProject() {
        return enterFlow(FlowType.PROJECT);
    }

    public String saveNewContract() {
        try {
            if (contract.getId() == null) {
                projectService.saveContract(contract);
                FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("newContract_added"), contract.toString(), FacesMessage.SEVERITY_INFO);
            } else {
                projectService.updateContract(contract);
                FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("edit_done"), contract.toString(), FacesMessage.SEVERITY_INFO);
            }
            FacesUtils.keepMessageInFlash();
            return endFlowAndRedirect();
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (event.getOldStep().equalsIgnoreCase("clientsTab")) {
            contract.setInvolvedClients(new HashSet<>(allClients.getTarget()));
        }
        return event.getNewStep();
    }

    public void onAppChanged(AjaxBehaviorEvent event) {
        activityList = applicationService.findActivitiesForApplication(selectedApplication);
        Collections.sort(activityList, new Comparator<Activity>() {
            @Override
            public int compare(Activity a1, Activity a2) {
                return a1.getType().compareTo(a2.getType());
            }
        });
        System.out.println("actList: "+activityList);
        selectedActivity = null;
        activitySteps = null;
    }

    public void onActChanged(AjaxBehaviorEvent event) {
        //Activity act = (Activity) ((UIOutput) event.getSource()).getValue();
        activitySteps = applicationService.findActivityStepsForActivity(selectedActivity);
    }

    public Activity getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedActivity(Activity selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    public List<ActivityStep> getActivitySteps() {
        return activitySteps;
    }

    public void setActivitySteps(List<ActivityStep> activitySteps) {
        this.activitySteps = activitySteps;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public Application getSelectedApplication() {
        return selectedApplication;
    }

    public void setSelectedApplication(Application selectedApplication) {
        this.selectedApplication = selectedApplication;
    }

    public List<SelectItem> getAvailableApplications() {
        return availableApplications;
    }

    public void setAvailableApplications(List<SelectItem> availableApplications) {
        this.availableApplications = availableApplications;
    }

    public List<Client> getClientTarget() {
        return clientTarget;
    }

    public void setClientTarget(List<Client> clientTarget) {
        this.clientTarget = clientTarget;
    }

    public List<Client> getClientSource() {
        return clientSource;
    }

    public void setClientSource(List<Client> clientSource) {
        this.clientSource = clientSource;
    }

    public DualListModel<Client> getAllClients() {
        return allClients;
    }

    public void setAllClients(DualListModel<Client> allClients) {
        this.allClients = allClients;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<PfMember> getPfMemberList() {
        return pfMemberList;
    }

    public void setPfMemberList(List<PfMember> pfMemberList) {
        this.pfMemberList = pfMemberList;
    }

    public String getWizStep() {
        return wizStep;
    }

    public void setWizStep(String wizStep) {
        this.wizStep = wizStep;
    }

    private void populateApplicationLists() {
        List<Application> appList = applicationService.findAllApplications();


        Map<ApplicationCategory, List<Application>> expMap = getAppCat2AppList(appList);

        availableApplications = new ArrayList<>();
        for (ApplicationCategory appCat : expMap.keySet()) {
            SelectItemGroup group = new SelectItemGroup(appCat.getName());
            SelectItem[] items = new SelectItem[expMap.get(appCat).size()];
            for (int i = 0; i < expMap.get(appCat).size(); i++) {
                SelectItem item = new SelectItem(expMap.get(appCat).get(i));
                items[i] = item;
            }
            group.setSelectItems(items);
            availableApplications.add(group);
        }
    }

    private Map<ApplicationCategory, List<Application>> getAppCat2AppList(List<Application> appList) {
        Map<ApplicationCategory, List<Application>> map = new HashMap<>();

        ApplicationCategory otherCat = new ApplicationCategory();
        otherCat.setName(FacesUtils.getI18nValueInMessages("label_other"));
        for (Application app : appList) {
            if (app.getCategory() == null) {
                if (map.containsKey(otherCat)) {
                    map.get(otherCat).add(app);
                } else {
                    List<Application> newList = new ArrayList<>();
                    newList.add(app);
                    map.put(otherCat, newList);
                }
            } else {
                if (map.containsKey(app.getCategory())) {
                    map.get(app.getCategory()).add(app);
                } else {
                    List<Application> newList = new ArrayList<>();
                    newList.add(app);
                    map.put(app.getCategory(), newList);
                }
            }
        }

        return map;
    }
}
