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
public abstract class CompanyFlow extends GenericFlow implements FlowMethods{

    @Override
    public final String endFlowAndRedirect() {
    FacesUtils.removeObjectFromSessionMap("company");
        if (flowBean.getFlowAbove(FlowType.COMPANY) != null) {
            switch (flowBean.getFlowAbove(FlowType.COMPANY)) {
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
        flowBean.endFlow(FlowType.COMPANY);
        FacesUtils.removeObjectFromSessionMap("company");
    }
}
