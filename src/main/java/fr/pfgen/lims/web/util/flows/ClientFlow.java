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
public abstract class ClientFlow extends GenericFlow implements FlowMethods {

    @Override
    public final String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.CLIENT) != null) {
            switch (flowBean.getFlowAbove(FlowType.CLIENT)) {
                case PROJECT:
                    return redirectToProject();
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
    
    public String redirectToProject(){
        cleanFlow();
        return "/pages/projects/project" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "/pages/people/clients" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.CLIENT);
        FacesUtils.removeObjectFromSessionMap("client");
    }
}
