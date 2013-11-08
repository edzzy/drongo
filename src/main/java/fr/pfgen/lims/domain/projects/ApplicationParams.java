/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.domain.projects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author eric
 */
@Entity
@Table(name = "application_params")
public class ApplicationParams extends AbstractActivityParams{
    
    @OneToOne(optional = false, mappedBy = "applicationParams", cascade = CascadeType.ALL)
    private Application application;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
