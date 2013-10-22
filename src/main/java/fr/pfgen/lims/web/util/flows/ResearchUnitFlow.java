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
public abstract class ResearchUnitFlow extends GenericFlow implements FlowMethods{

    @Override
    public final String endFlowAndRedirect() {
    FacesUtils.removeObjectFromSessionMap("researchUnit");
        if (flowBean.getFlowAbove(FlowType.RESEARCHUNIT) != null) {
            switch (flowBean.getFlowAbove(FlowType.RESEARCHUNIT)) {
                case RESEARCHTEAM:
                    return redirectToResearchTeam();
                default:
                    return redirectToDefault();
            }
        } else {
            return redirectToDefault();
        }
    }
    
    public String redirectToResearchTeam(){
        cleanFlow();
        return "/pages/people/researchTeamCreate" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.RESEARCHUNIT);
        FacesUtils.removeObjectFromSessionMap("researchUnit");
    }
}
