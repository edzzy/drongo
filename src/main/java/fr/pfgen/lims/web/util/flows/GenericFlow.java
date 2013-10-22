/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util.flows;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eric
 */
public abstract class GenericFlow {

    final String REDIRECT = "?faces-redirect=true";
    @Autowired
    FlowBean flowBean;

    /*
    public String endFlowAndRedirect(FlowType flowType) {
        String redirectString;
        switch (flowType) {
            case PROJECT:
                redirectString = "/pages/projects/projects" + REDIRECT;
                break;
            case CLIENT:
                FacesUtils.removeObjectFromSessionMap("client");
                if (flowBean.getFlowAbove(flowType) != null) {
                    switch (flowBean.getFlowAbove(flowType)) {
                        case PROJECT:
                            redirectString = "/pages/projects/project" + REDIRECT;
                            break;
                        default:
                            redirectString = "/pages/people/clients" + REDIRECT;
                            break;
                    }
                } else {
                    redirectString = "/pages/people/clients" + REDIRECT;
                }
                break;
            case RESEARCHTEAM:
                FacesUtils.removeObjectFromSessionMap("researchTeam");
                if (flowBean.getFlowAbove(flowType) != null) {
                    switch (flowBean.getFlowAbove(flowType)) {
                        case CLIENT:
                            redirectString = "/pages/people/client" + REDIRECT;
                            break;
                        default:
                            redirectString = "";
                            break;
                    }
                } else {
                    redirectString = "";
                }
                break;
            case RESEARCHUNIT:
                FacesUtils.removeObjectFromSessionMap("researchUnit");
                if (flowBean.getFlowAbove(flowType) != null) {
                    switch (flowBean.getFlowAbove(flowType)) {
                        case RESEARCHTEAM:
                            redirectString = "/pages/people/researchTeamCreate" + REDIRECT;
                            break;
                        default:
                            redirectString = "";
                            break;
                    }
                } else {
                    redirectString = "";
                }
                break;
            case COMPANY:
                FacesUtils.removeObjectFromSessionMap("company");
                if (flowBean.getFlowAbove(flowType) != null) {
                    switch (flowBean.getFlowAbove(flowType)) {
                        case CLIENT:
                            redirectString = "/pages/people/client" + REDIRECT;
                            break;
                        default:
                            redirectString = "";
                            break;
                    }
                } else {
                    redirectString = "";
                }
                break;
            default:
                redirectString = "";
                break;
        }
        flowBean.endFlow(flowType);
        return redirectString;

    }
    */

    public final void enterFlow(FlowType f) {
        flowBean.addFlowToStack(f);
    }
}
