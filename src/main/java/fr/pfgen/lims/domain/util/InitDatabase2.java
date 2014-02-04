/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.util;

import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import fr.pfgen.lims.domain.equipments.Intervention;
import fr.pfgen.lims.domain.equipments.PlateformType;
import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.ClientType;
import fr.pfgen.lims.domain.people.Company;
import fr.pfgen.lims.domain.people.PfMember;
import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import fr.pfgen.lims.service.ClientService;
import fr.pfgen.lims.service.ClientTypeService;
import fr.pfgen.lims.service.CompanyService;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.service.PfMemberService;
import fr.pfgen.lims.service.ResearchTeamService;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author edouard
 */
public class InitDatabase2 implements ServletContextListener {
    
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
    EquipmentService equipmentService;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            WebApplicationContextUtils
                    .getRequiredWebApplicationContext(sce.getServletContext())
                    .getAutowireCapableBeanFactory()
                    .autowireBean(this);
            
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
            
            List<Company> companyList = new ArrayList<>();
            
            Company co1 = new Company();
            co1.setName("Clean Cells");
            companyList.add(co1);
            Company co2 = new Company();
            co2.setName("Affymetrix");
            companyList.add(co2);
            
            Company co3 = new Company();
            co3.setName("Empire");
            companyList.add(co3);
            
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
            
            Client c4 = new Client();
            c4.setFirstname("Darth");
            c4.setLastname("Vador");
            c4.setEmail("darthvador@empire.com");
            c4.setType(ct3);
            c4.setCompany(co3);
            clientList.add(c4);
            
            for (Client client : clientList) {
                clientService.saveClient(client);
            }
            
            List<PfMember> pfMemberList = new ArrayList<>();
            
            PfMember p1 = new PfMember();
            p1.setFirstname("eric");
            p1.setLastname("charpentier");
            p1.setEmail("eric.charpentier@univ-nantes.fr");
            
            p1.setOffice("218");
            pfMemberList.add(p1);
            
            PfMember p2 = new PfMember();
            p2.setFirstname("edouard");
            p2.setLastname("hirchaud");
            p2.setEmail("edouard.hirchaud@univ-nantes.fr");
            
            p2.setOffice("218");
            pfMemberList.add(p2);
            
            PfMember p3 = new PfMember();
            p3.setFirstname("Laetitia");
            p3.setLastname("Duboscq-Bidot");
            p3.setEmail("Laetitia.Duboscq@univ-nantes.fr");
            p3.setOffice("222");
            pfMemberList.add(p3);
            
            PfMember p4 = new PfMember();
            p4.setFirstname("Marine");
            p4.setLastname("Cornec");
            p4.setEmail("Marine.Cornec@univ-nantes.fr");
            p4.setOffice("221");
            pfMemberList.add(p4);
            
            PfMember p5 = new PfMember();
            p5.setFirstname("Raluca");
            p5.setLastname("Teusan");
            p5.setEmail("raluca.teusan@inserm.fr");
            p5.setOffice("218");
            pfMemberList.add(p5);
            
            PfMember p6 = new PfMember();
            p6.setFirstname("Audrey");
            p6.setLastname("Bihouée");
            p6.setEmail("audrey.bihouee@nantes.fr");
            p6.setOffice("218");
            pfMemberList.add(p6);
            
            PfMember p7 = new PfMember();
            p7.setFirstname("Richard");
            p7.setLastname("Redon");
            p7.setEmail("richard.redon@inserm.fr");
            p7.setOffice("217");
            pfMemberList.add(p7);
            
            PfMember p8 = new PfMember();
            p8.setFirstname("Catherine");
            p8.setLastname("Chevalier");
            p8.setEmail("catherine.chevalier@inserm.fr");
            p8.setOffice("212");
            pfMemberList.add(p8);
            
            PfMember p9 = new PfMember();
            p9.setFirstname("Audrey");
            p9.setLastname("Donnart");
            p9.setEmail("audrey.donnart@univ-nantes.fr");
            p9.setOffice("211");
            pfMemberList.add(p9);
            
            PfMember p10 = new PfMember();
            p10.setFirstname("Françoise");
            p10.setLastname("Gros");
            p10.setEmail("francoise.gros@inserm.fr");
            p10.setOffice("212");
            pfMemberList.add(p10);
            
            PfMember p11 = new PfMember();
            p11.setFirstname("Jade");
            p11.setLastname("Violleau");
            p11.setEmail("jade.violleau@univ-nantes.fr");
            p11.setOffice("212");
            pfMemberList.add(p11);
            
            for (PfMember pfMember : pfMemberList) {
                pfMemberService.savePfMember(pfMember);
            }
            
            List<RunDevice> runDeviceList = new ArrayList<>();
            RunDevice rd1 = new RunDevice();
            DateTime dt = new DateTime(2007, 1, 1, 0, 0, 0);
            rd1.setAcquisitionDate(dt.toDate());
            rd1.setName("Baie de sauvegarde");
            rd1.setManufacturer("DELL");
            rd1.setRoom("IRS : 117");
            rd1.setItx("ITX0000");
            rd1.setType("Power Vault MD1000");
            rd1.setSerialNumber("98LK93J");
            rd1.setStatus(EquipmentStatus.ACTIVE);
            rd1.setPlateform(PlateformType.BIOINFO);
            byte[] imgByte1 = excractBytes("/home/edouard/Images/pvmd1000.jpg");
            rd1.setImage(imgByte1);
            
            runDeviceList.add(rd1);
            
            RunDevice rd2 = new RunDevice();
            DateTime dt2 = new DateTime(2009, 1, 1, 0, 0, 0);
            rd2.setAcquisitionDate(dt2.toDate());
            rd2.setName("Guigamor");
            rd2.setManufacturer("DELL");
            rd2.setRoom("IRS : 117");
            rd2.setItx("ITX0001");
            rd2.setType("PowerEdge 2970");
            rd2.setSerialNumber("BPJN64J");
            rd2.setStatus(EquipmentStatus.ACTIVE);
            rd2.setPlateform(PlateformType.BIOINFO);
            byte[] imgByte2 = excractBytes("/home/edouard/Images/PowerEdge2970.jpg");
            rd2.setImage(imgByte2);
            
            runDeviceList.add(rd2);
            
            
            RunDevice rd3 = new RunDevice();
            DateTime dt3 = new DateTime(2011, 1, 1, 0, 0, 0);
            rd3.setAcquisitionDate(dt3.toDate());
            rd3.setName("GeneTitan");
            rd3.setManufacturer("Affymetrix");
            rd3.setRoom("IRS : 217");
            rd3.setItx("ITX0005");
            rd3.setType("GeneTitan");
            rd3.setSerialNumber("AffBPJN64J");
            rd3.setStatus(EquipmentStatus.ACTIVE);
            rd3.setPlateform(PlateformType.GENETIQUE);
            byte[] imgByte3 = excractBytes("/home/edouard/Images/AffymetrixGeneTitan.jpg");
            rd3.setImage(imgByte3);
            
            runDeviceList.add(rd3);
            
            
            for (RunDevice rundevice : runDeviceList) {
                equipmentService.saveRunDevice(rundevice);
            }
            
            Intervention i1 = new Intervention();
            i1.setEquipment(rd2);
            i1.setSubject("Changement du disque 0 ");
            i1.setComment("<strong>RAID 5</strong>");
            i1.setInterventionDate(new DateTime(2013, 2, 22, 0, 0, 0).toDate());
            
            equipmentService.saveIntervention(i1);
            
        } catch (IOException ex) {
            Logger.getLogger(InitDatabase2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
    public byte[] excractBytes(String ImageName) throws IOException {
        File imPath = new File(ImageName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(imPath);
        byte array[] = new byte[2048];
        int nRead = 0;
        while ((nRead = fis.read(array)) != -1) {
            baos.write(array, 0, nRead);
        }
        baos.flush();
        baos.close();
        fis.close();
        
        return baos.toByteArray();
    }
    
}
