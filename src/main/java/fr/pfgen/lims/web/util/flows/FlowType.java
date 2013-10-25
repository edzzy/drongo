/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util.flows;

/**
 *
 * @author eric
 */
public enum FlowType {
    PROJECT ("/pages/projects/project"),
    CLIENT ("/pages/people/client"),
    RESEARCHUNIT ("/pages/people/researchUnitCreate"),
    RESEARCHTEAM ("/pages/people/researchTeamCreate"),
    COMPANY ("/pages/people/companyCreate"),
    PFMEMBER ("/pages/people/pfMember"),
    CONTRACT ("/pages/projects/contract");
    
    private final String path;

    private FlowType(String path) {
        this.path = path;
    }
    
    public String getPath(){
        return path;
    }
}
