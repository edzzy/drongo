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
public class StepFlow extends GenericFlow implements FlowMethods{

    @Override
    public String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.STEP) != null) {
            switch (flowBean.getFlowAbove(FlowType.STEP)) {
                case ACTIVITY:
                    return redirectToActivity();
                default:
                    return redirectToDefault();
            }
        } else {
            return redirectToDefault();
        }
    }
    
    public String redirectToActivity(){
        cleanFlow();
        return "/pages/projects/activity" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "";
    }

    @Override
    public void cleanFlow() {
        flowBean.endFlow(FlowType.STEP);
        FacesUtils.removeObjectFromSessionMap("step");
    }
}
