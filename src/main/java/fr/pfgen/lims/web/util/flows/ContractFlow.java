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
public class ContractFlow extends GenericFlow implements FlowMethods{

    @Override
    public final String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.CONTRACT) != null) {
            switch (flowBean.getFlowAbove(FlowType.CONTRACT)) {
                case PROJECT:
                    return redirectToProject();
                default:
                    return redirectToDefault();
            }
        } else {
            return redirectToDefault();
        }
    }
    
    public String redirectToProject(){
        cleanFlow();
        return "/pages/projects/project" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "/pages/projects/contracts" + REDIRECT;
    }

    @Override
    public final void cleanFlow() {
        flowBean.endFlow(FlowType.CONTRACT);
        FacesUtils.removeObjectFromSessionMap("contract");
    }
}
