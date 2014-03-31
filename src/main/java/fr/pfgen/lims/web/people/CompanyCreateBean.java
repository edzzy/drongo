/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.people;

import fr.pfgen.lims.domain.people.Client;
import fr.pfgen.lims.domain.people.Company;
import fr.pfgen.lims.service.CompanyService;
import fr.pfgen.lims.web.util.FacesUtils;
import fr.pfgen.lims.web.util.flows.CompanyFlow;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eric
 */
@Component
@Scope("request")
public class CompanyCreateBean extends CompanyFlow{

    private Company company = new Company();
    @Autowired
    private CompanyService companyService;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String name = ((String) value).toLowerCase();

        Company existingCompany = companyService.findCompanyByName(name);

        if (existingCompany != null) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), null, "\"" + name + "\" " + FacesUtils.getI18nValueInMessages("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public String saveNewCompany() {
        try {
            companyService.saveCompany(company);
            Client client = ((Client) FacesUtils.getObjectInSessionMap("client"));

            if (client != null) {
                client.setCompany(company);
                FacesUtils.putObjectInSessionMap("wizStep", "typeTab");
                return endFlowAndRedirect();
            } else {
                //TODO redirect to company list when created !!!!
                return null;
            }
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValueInMessages("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelCompanyCreation() {
        Client client = ((Client) FacesUtils.getObjectInSessionMap("client"));

        if (client != null) {
            FacesUtils.putObjectInSessionMap("wizStep", "typeTab");
            return endFlowAndRedirect();
        }else{
            //TODO redirect to company list when created !!!!
            return null;
        }
    }
}
