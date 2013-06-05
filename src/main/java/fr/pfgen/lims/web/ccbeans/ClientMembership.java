/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.ccbeans;

import fr.pfgen.lims.domain.Client;
import fr.pfgen.lims.domain.ClientType;
import fr.pfgen.lims.domain.ResearchTeam;
import fr.pfgen.lims.domain.ResearchUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@FacesComponent("clientMembership")
public class ClientMembership extends UINamingContainer {
    
    private static final String internUnitName = "UMR 1087";

    /**
     * Returns the component family of {@link UINamingContainer}. (that's just
     * required by composite component)
     */
    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
    
    private Client client;
    private List<ClientType> clientTypeList;
    private Map<ResearchUnit, List<ResearchTeam>> unit2teams;
   

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        client = (Client) getAttributes().get("selClient");
        clientTypeList = (List<ClientType>) getAttributes().get("clientTypeList");
        unit2teams = (Map<ResearchUnit, List<ResearchTeam>>) getAttributes().get("unit2teams");
        
        //UISelectOne type = (UISelectOne) findComponent("clientTypeSelect");
        
        List<ResearchUnit> units = new ArrayList<>();
        for (ResearchUnit researchUnit : unit2teams.keySet()) {
            if (!researchUnit.toString().equals(internUnitName)){
                units.add(researchUnit);
                System.out.println(researchUnit.getName());
            }
        }
        
        if (client!=null){
            if (client.getType().toString().equals("interne")){
                UISelectOne unitSelect = (UISelectOne) findComponent("unitSelect");
                unitSelect.setRendered(false);
            }
        }
        
        
        
        setUnitList(units);
        
        
        
       
        
        
        
        super.encodeBegin(context);
    }
    
    public void onTypeChanged(AjaxBehaviorEvent event) {
        ClientType type = (ClientType) ((UISelectOne) event.getSource()).getValue();
        UISelectOne unitSelect = (UISelectOne) findComponent("unitSelect");
        switch (type.toString()) {
            case "interne":
                unitSelect.setRendered(false);
                break;
            case "académique":
                unitSelect.setRendered(true);
                break;
            case "privé":
                unitSelect.setRendered(true);
                break;
            default:
                
                break;
        }
    }
    
    public void onUnitChanged(AjaxBehaviorEvent event) {
        
    }

    public List<ResearchUnit> getUnitList() {
        return ((List<ResearchUnit>) getStateHelper().get("unitList"));
    }

    public void setUnitList(List<ResearchUnit> unitList) {
        getStateHelper().put("unitList", unitList);
    }

    public List<ResearchTeam> getTeamList() {
        return ((List<ResearchTeam>) getStateHelper().get("teamList"));
    }

    public void setTeamList(List<ResearchTeam> teamList) {
       getStateHelper().put("teamList", teamList);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<ResearchUnit, List<ResearchTeam>> getUnit2teams() {
        return unit2teams;
    }

    public void setUnit2teams(Map<ResearchUnit, List<ResearchTeam>> unit2teams) {
        this.unit2teams = unit2teams;
    }

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
    }
}
