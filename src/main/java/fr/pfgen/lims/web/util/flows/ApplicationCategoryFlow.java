/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util.flows;

import fr.pfgen.lims.domain.projects.Application;
import fr.pfgen.lims.domain.projects.ApplicationCategory;
import fr.pfgen.lims.web.util.FacesUtils;

/**
 *
 * @author eric
 */
public class ApplicationCategoryFlow extends GenericFlow implements FlowMethods{

    @Override
    public final String endFlowAndRedirect() {
        if (flowBean.getFlowAbove(FlowType.APPLICATIONCATEGORY) != null) {
            switch (flowBean.getFlowAbove(FlowType.APPLICATIONCATEGORY)) {
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
        if (FacesUtils.getObjectInSessionMap("application")!=null){
            Application app = (Application) FacesUtils.getObjectInSessionMap("application");
            app.setCategory((ApplicationCategory) FacesUtils.getObjectInSessionMap("applicationCategory"));
        }
        cleanFlow();
        return "/pages/projects/application" + REDIRECT;
    }
    
    public String redirectToDefault(){
        cleanFlow();
        return "/pages/projects/applications" + REDIRECT;
    }

    @Override
    public void cleanFlow() {
        flowBean.endFlow(FlowType.APPLICATIONCATEGORY);
        FacesUtils.removeObjectFromSessionMap("applicationCategory");
    }
    
}
