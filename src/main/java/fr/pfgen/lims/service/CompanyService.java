/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Company;
import java.util.List;

/**
 *
 * @author eric
 */
public interface CompanyService {
    
    public abstract long countAllCompanies();

    public abstract void deleteCompany(Company company);

    public abstract Company findCompany(Long id);

    public abstract List<Company> findAllCompanies();

    public abstract List<Company> findCompanyEntries(int firstResult, int maxResults);

    public abstract void saveCompany(Company company);

    public abstract Company updateCompany(Company company);
    
    public abstract Company findCompanyByName(String name);
    
}
