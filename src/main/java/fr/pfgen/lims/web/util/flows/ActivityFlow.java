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
public class ActivityFlow extends GenericFlow implements FlowMethods{
    
    @Override
    public final String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.ACTIVITY) != null) {
            switch (flowBean.getFlowAbove(FlowType.ACTIVITY)) {
                case APPLICATION:
                    return redirectToApplication();
                default:
                    return redirectToDefault();
            }
        } else {
            return redirectToDefault();
        }
    }
    
    public String redirectToApplication(){
        cleanFlow();
        return "/pages/projects/application" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "/pages/projects/activities" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.ACTIVITY);
        FacesUtils.removeObjectFromSessionMap("activity");
    }
}
