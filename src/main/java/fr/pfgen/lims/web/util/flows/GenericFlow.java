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

    public final String enterFlow(FlowType f) {
        flowBean.addFlowToStack(f);
        return f.getPath()+REDIRECT;
    }
}
