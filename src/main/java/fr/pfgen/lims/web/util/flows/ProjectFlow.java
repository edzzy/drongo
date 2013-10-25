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
public abstract class ProjectFlow extends GenericFlow implements FlowMethods {
    
    @Override
    public final String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.PROJECT) != null) {
            switch (flowBean.getFlowAbove(FlowType.PROJECT)) {
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
        return "/pages/projects/projects" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.PROJECT);
        FacesUtils.removeObjectFromSessionMap("project");
    }
}
