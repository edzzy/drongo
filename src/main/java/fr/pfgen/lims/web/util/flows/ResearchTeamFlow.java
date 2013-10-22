/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util.flows;

import fr.pfgen.lims.web.util.FacesUtils;

/**
 *
 * @author eric
 */
public abstract class ResearchTeamFlow extends GenericFlow implements FlowMethods{

    @Override
    public final String endFlowAndRedirect() {
    FacesUtils.removeObjectFromSessionMap("researchTeam");
        if (flowBean.getFlowAbove(FlowType.RESEARCHTEAM) != null) {
            switch (flowBean.getFlowAbove(FlowType.RESEARCHTEAM)) {
                case CLIENT:
                    return redirectToClient();
                default:
                    return redirectToDefault();
            }
        } else {
            return redirectToDefault();
        }
    }
    
    public String redirectToClient(){
        cleanFlow();
        return "/pages/people/client" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.RESEARCHTEAM);
        FacesUtils.removeObjectFromSessionMap("researchTeam");
    }
}
