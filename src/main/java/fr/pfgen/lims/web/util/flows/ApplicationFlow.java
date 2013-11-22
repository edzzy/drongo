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
public class ApplicationFlow extends GenericFlow implements FlowMethods{

    @Override
    public final String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.APPLICATION) != null) {
            switch (flowBean.getFlowAbove(FlowType.APPLICATION)) {
                case CONTRACT:
                    return redirectToContract();
                default:
                    return redirectToDefault();
            }
        } else {
            return redirectToDefault();
        }
    }
    
    public String redirectToContract(){
        cleanFlow();
        return "/pages/projects/contract" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "/pages/projects/applications" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.APPLICATION);
        FacesUtils.removeObjectFromSessionMap("application");
    }
    
}
