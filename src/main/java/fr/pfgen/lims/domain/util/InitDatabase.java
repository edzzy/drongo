/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.util;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.domain.people.Company;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.domain.projects.Activity;
import fr.pfgen.lims.domain.projects.ActivityParams;
import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.domain.projects.ActivityType;
import fr.pfgen.lims.service.ApplicationService;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.CompanyService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.service.ResearchTeamService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * @author eric
 */
public class InitDatabase implements ServletContextListener{

    @Autowired
    ClientTypeService clientTypeService;
    
    @Autowired
    ResearchTeamService researchTeamService;
    
    @Autowired
    CompanyService companyService;
    
    @Autowired
    ClientService clientService;
    
    @Autowired
    PfMemberService pfMemberService;
    
    @Autowired
    ApplicationService applicationService;
    
    @Override
    public void contextDestroyed(ServletContextEvent sce)
        {
            
        }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        WebApplicationContextUtils
        .getRequiredWebApplicationContext(sce.getServletContext())
        .getAutowireCapableBeanFactory()
        .autowireBean(this);
        
        
        //clientTypes
        List<ClientType> clientTypeList = new ArrayList<>();
        
        ClientType ct1 = new ClientType();
        ct1.setName("interne");
        clientTypeList.add(ct1);
        ClientType ct2 = new ClientType();
        ct2.setName("académique");
        clientTypeList.add(ct2);
        ClientType ct3 = new ClientType();
        ct3.setName("privé");
        clientTypeList.add(ct3);
        
        for (ClientType clientType : clientTypeList) {
            clientTypeService.saveClientType(clientType);
        }
        
        //research teams
        List<ResearchTeam> teamList = new ArrayList<>();
        
        ResearchUnit u1 = new ResearchUnit();
        u1.setName("UMR 1087");
        ResearchUnit u2 = new ResearchUnit();
        u2.setName("U892");
        ResearchUnit u3 = new ResearchUnit();
        u3.setName("LINA");
        
        
        ResearchTeam t1 = new ResearchTeam();
        t1.setName("Equipe 4 - RR");
        t1.setResearchUnit(u1);
        teamList.add(t1);
        ResearchTeam t2 = new ResearchTeam();
        t2.setName("Equipe 3 - JJS");
        t2.setResearchUnit(u1);
        teamList.add(t2);
        ResearchTeam t3 = new ResearchTeam();
        t3.setName("Equipe 8");
        t3.setResearchUnit(u2);
        teamList.add(t3);
        ResearchTeam t4 = new ResearchTeam();
        t4.setName("Equipe 9");
        t4.setResearchUnit(u2);
        teamList.add(t4);
        ResearchTeam t5 = new ResearchTeam();
        t5.setName("Equipe COD - CS");
        t5.setResearchUnit(u3);
        teamList.add(t5);
        ResearchTeam t6 = new ResearchTeam();
        t6.setName("Equipe COMBI - JB");
        t6.setResearchUnit(u3);
        teamList.add(t6);
        
        /*
        for (ResearchTeam researchTeam : teamList) {
            researchTeamService.saveResearchUnit(researchTeam.getResearchUnit());
            researchTeamService.saveResearchTeam(researchTeam);
        }
        */
        
        //companies
        List<Company> companyList = new ArrayList<>();
        
        Company co1 = new Company();
        co1.setName("Clean Cells");
        companyList.add(co1);
        Company co2 = new Company();
        co2.setName("Affymetrix");
        companyList.add(co2);
        
        /*
        for (Company company : companyList) {
            companyService.saveCompany(company);
        }
        */
        
        //clients
        List<Client> clientList = new ArrayList<>();
        
        Client c1 = new Client();
        c1.setFirstname("Jean-Paul");
        c1.setLastname("Mira");
        c1.setEmail("jp-mira@free.fr");
        c1.setType(ct2);
        c1.setResearchTeam(t3);
        clientList.add(c1);
        
        Client c2 = new Client();
        c2.setFirstname("Gilles");
        c2.setLastname("Toumaniantz");
        c2.setEmail("Gilles.toumaniantz@univ-nantes.fr");
        c2.setType(ct1);
        c2.setResearchTeam(t2);
        clientList.add(c2);
        
        Client c3 = new Client();
        c3.setFirstname("Tarte");
        c3.setLastname("en pion");
        c3.setEmail("tartenpion@gergerb.br");
        c3.setType(ct3);
        c3.setCompany(co1);
        clientList.add(c3);
        
        for (Client client : clientList) {
            clientService.saveClient(client);
        }
        
        //pfmembers
        List<PfMember> pfMemberList = new ArrayList<>();
        
        PfMember p1 = new PfMember();
        p1.setFirstname("eric");
        p1.setLastname("charpentier");
        p1.setEmail("eric.charpentier@univ-nantes.fr");
        p1.setMemberSince(new DateTime(2011, 2, 1, 0, 0).toDate());
        p1.setOffice("218");
        pfMemberList.add(p1);
        
        PfMember p2 = new PfMember();
        p2.setFirstname("edouard");
        p2.setLastname("hirchaud");
        p2.setEmail("edouard.hirchaud@univ-nantes.fr");
        p2.setMemberSince(new DateTime(2010, 1, 4, 0, 0).toDate());
        p2.setOffice("218");
        pfMemberList.add(p2);
        
        PfMember p3 = new PfMember();
        p3.setFirstname("Laetitia");
        p3.setLastname("Duboscq-Bidot");
        p3.setEmail("Laetitia.Duboscq@univ-nantes.fr");
        p3.setMemberSince(new DateTime(2012, 5, 2, 0, 0).toDate());
        p3.setOffice("222");
        pfMemberList.add(p3);
        
        PfMember p4 = new PfMember();
        p4.setFirstname("Marine");
        p4.setLastname("Cornec");
        p4.setEmail("Marine.Cornec@univ-nantes.fr");
        p4.setMemberSince(new DateTime(2010, 3, 25, 0, 0).toDate());
        p4.setOffice("221");
        pfMemberList.add(p4);
        
        for (PfMember pfMember : pfMemberList) {
            pfMemberService.savePfMember(pfMember);
        }
        
        List<ApplicationCategory> appCatList = new ArrayList<>();
        
        //Applications
        ApplicationCategory ac1 = new ApplicationCategory();
        ac1.setName("NGS");
        appCatList.add(ac1);
        ApplicationCategory ac2 = new ApplicationCategory();
        ac2.setName("Expression");
        appCatList.add(ac2);
        
        for (ApplicationCategory applicationCategory : appCatList) {
            applicationService.saveApplicationCategory(applicationCategory);
        }
        
        
        //Applications
        List<Application> appList = new ArrayList<>();
        
        Application ap = new Application();
        ap.setName("Whole genome");
        ap.setCategory(ac1);
        ap.setCode("WGE");
        Activity x = new Activity();
        x.setReferent(p3);
        x.setType(ActivityType.EXPERIMENTAL);
        x.setApplication(ap);
        x.setActivityParams(new ActivityParams());
        Activity y = new Activity();
        y.setReferent(p1);
        y.setType(ActivityType.ANALYSIS);
        y.setApplication(ap);
        y.setActivityParams(new ActivityParams());
        Activity z = new Activity();
        z.setReferent(p3);
        z.setType(ActivityType.BOTH);
        z.setApplication(ap);
        z.setActivityParams(new ActivityParams());
        Set<Activity> s = new HashSet<>();
        s.add(x);s.add(y);s.add(z);
        ap.setActivities(s);
        appList.add(ap);
        
        ap = new Application();
        ap.setName("Whole exome");
        ap.setCategory(ac1);
        ap.setCode("WEX");
        x = new Activity();
        x.setReferent(p3);
        x.setType(ActivityType.EXPERIMENTAL);
        x.setApplication(ap);
        x.setActivityParams(new ActivityParams());
        y = new Activity();
        y.setReferent(p1);
        y.setType(ActivityType.ANALYSIS);
        y.setApplication(ap);
        y.setActivityParams(new ActivityParams());
        s = new HashSet<>();
        s.add(x);s.add(y);
        ap.setActivities(s);
        appList.add(ap);
        
        ap = new Application();
        ap.setName("MiRNA");
        ap.setCategory(ac2);
        ap.setCode("MRN");
        x = new Activity();
        x.setReferent(p4);
        x.setType(ActivityType.EXPERIMENTAL);
        x.setApplication(ap);
        x.setActivityParams(new ActivityParams());
        y = new Activity();
        y.setReferent(p2);
        y.setType(ActivityType.ANALYSIS);
        y.setApplication(ap);
        y.setActivityParams(new ActivityParams());
        s = new HashSet<>();
        s.add(x);s.add(y);
        ap.setActivities(s);
        appList.add(ap);
        
        ap = new Application();
        ap.setName("Exon Chip");
        ap.setCategory(ac2);
        ap.setCode("ECH");
        x = new Activity();
        x.setReferent(p4);
        x.setType(ActivityType.EXPERIMENTAL);
        x.setApplication(ap);
        x.setActivityParams(new ActivityParams());
        y = new Activity();
        y.setReferent(p2);
        y.setType(ActivityType.ANALYSIS);
        y.setApplication(ap);
        y.setActivityParams(new ActivityParams());
        s = new HashSet<>();
        s.add(x);s.add(y);
        ap.setActivities(s);
        appList.add(ap);
        
        ap = new Application();
        ap.setName("qPCR");
        ap.setCode("QPC");
        appList.add(ap);
        
        for (Application app: appList) {
            applicationService.saveApplication(app);
        }
     
        
        
        /*
        List<Application> appList = new ArrayList<>();
        
        ApplicationParams ap1 = new ApplicationParams();
        ap1.setDaysOfBackupResults(TimeType.MONTH.toDays(6));
        ap1.setDaysToAccomplish(TimeType.MONTH.toDays(3));
        ap1.setDaysToDestructionSamples(TimeType.MONTH.toDays(6));
        ap1.setNbSamples(3);
        ap1.setResultsSentBy(ResultsSentByType.HARDDRIVE.name());
        ap1.setSampleConditioning(SampleConditioning.PLATE.name());
        ap1.setSampleTemperature(-20);
        ap1.setSampleType(SampleType.ADN.name());
                
        Application a1 = new Application();
        a1.setCategory(ac1);
        a1.setCode("WGE");
        a1.setName("whole genome");
        a1.setReferent(p3);
        a1.setApplicationParams(ap1);
        a1.setType(ApplicationType.EXPERIMENTAL);
        appList.add(a1);
        
        ApplicationParams ap2 = new ApplicationParams();
        ap2.setDaysOfBackupResults(TimeType.MONTH.toDays(6));
        ap2.setDaysToAccomplish(TimeType.MONTH.toDays(3));
        ap2.setDaysToDestructionSamples(TimeType.MONTH.toDays(6));
        ap2.setNbSamples(10);
        ap2.setResultsSentBy(ResultsSentByType.HARDDRIVE.name());
        ap2.setSampleConditioning(SampleConditioning.STRIPWELLS.name());
        ap2.setSampleTemperature(-20);
        ap2.setSampleType(SampleType.ADN.name());
        
        Application a2 = new Application();
        a2.setCategory(ac1);
        a2.setCode("WEX");
        a2.setName("whole exome");
        a2.setReferent(p3);
        a2.setApplicationParams(ap2);
        a2.setType(ApplicationType.EXPERIMENTAL);
        appList.add(a2);
        
        ApplicationParams ap3 = new ApplicationParams();
        ap3.setDaysOfBackupResults(TimeType.MONTH.toDays(6));
        ap3.setDaysToAccomplish(TimeType.MONTH.toDays(3));
        ap3.setDaysToDestructionSamples(TimeType.MONTH.toDays(6));
        ap3.setNbSamples(96);
        ap3.setResultsSentBy(ResultsSentByType.USBKEY.name());
        ap3.setSampleConditioning(SampleConditioning.PLATE.name());
        ap3.setSampleTemperature(-20);
        ap3.setSampleType(SampleType.ADN.name());
        
        Application a3 = new Application();
        a3.setCategory(ac1);
        a3.setCode("HPL");
        a3.setName("haloplex");
        a3.setReferent(p3);
        a3.setApplicationParams(ap3);
        a3.setType(ApplicationType.EXPERIMENTAL);
        appList.add(a3);
        
        ApplicationParams ap4 = new ApplicationParams();
        ap4.setDaysOfBackupResults(TimeType.MONTH.toDays(6));
        ap4.setDaysToAccomplish(TimeType.MONTH.toDays(2));
        ap4.setDaysToDestructionSamples(TimeType.MONTH.toDays(6));
        ap4.setNbSamples(96);
        ap4.setResultsSentBy(ResultsSentByType.HARDDRIVE.name());
        ap4.setSampleConditioning(SampleConditioning.PLATE.name());
        ap4.setSampleTemperature(-20);
        ap4.setSampleType(SampleType.ADN.name());
        
        Application a4 = new Application();
        a4.setCategory(ac2);
        a4.setCode("MIC");
        a4.setName("mi-RNA chip");
        a4.setReferent(p4);
        a4.setApplicationParams(ap4);
        a4.setType(ApplicationType.EXPERIMENTAL);
        appList.add(a4);
        
        ApplicationParams ap5 = new ApplicationParams();
        ap5.setDaysOfBackupResults(TimeType.MONTH.toDays(3));
        ap5.setDaysToAccomplish(TimeType.MONTH.toDays(2));
        ap5.setDaysToDestructionSamples(TimeType.MONTH.toDays(3));
        ap5.setNbSamples(24);
        ap5.setResultsSentBy(ResultsSentByType.USBKEY.name());
        ap5.setSampleConditioning(SampleConditioning.STRIPWELLS.name());
        ap5.setSampleTemperature(-20);
        ap5.setSampleType(SampleType.ADN.name());
        
        Application a5 = new Application();
        a5.setCategory(ac2);
        a5.setCode("ENC");
        a5.setName("exon chip");
        a5.setReferent(p2);
        a5.setApplicationParams(ap5);
        a5.setType(ApplicationType.EXPERIMENTAL);
        appList.add(a5);
        
        ApplicationParams ap6 = new ApplicationParams();
        ap6.setDaysOfBackupResults(TimeType.MONTH.toDays(3));
        ap6.setDaysToAccomplish(TimeType.WEEK.toDays(4));
        ap6.setNbSamples(0);
        ap6.setResultsSentBy(ResultsSentByType.USBKEY.name());
        ap6.setSampleConditioning(ResultsSentByType.USBKEY.name());
        ap6.setSampleType(SampleType.DATAFILE.name());
        
        Application a6 = new Application();
        a6.setCategory(ac3);
        a6.setCode("AWGE");
        a6.setName("analyse whole genome");
        a6.setReferent(p1);
        a6.setApplicationParams(ap6);
        a6.setType(ApplicationType.BIOINFORMATICS);
        appList.add(a6);
        
        ApplicationParams ap7 = new ApplicationParams();
        ap7.setDaysOfBackupResults(TimeType.MONTH.toDays(3));
        ap7.setDaysToAccomplish(TimeType.WEEK.toDays(4));
        ap7.setNbSamples(0);
        ap7.setResultsSentBy(ResultsSentByType.MAIL.name());
        ap7.setSampleConditioning(ResultsSentByType.MAIL.name());
        ap7.setSampleType(SampleType.DATAFILE.name());
        
        Application a7 = new Application();
        a7.setCategory(ac4);
        a7.setCode("AMIC");
        a7.setName("analyse mi-RNA");
        a7.setReferent(p2);
        a7.setApplicationParams(ap7);
        a7.setType(ApplicationType.BIOINFORMATICS);
        appList.add(a7);
        
        for (Application app : appList) {
            applicationService.saveApplication(app);
        }
        */ 
    }
}
