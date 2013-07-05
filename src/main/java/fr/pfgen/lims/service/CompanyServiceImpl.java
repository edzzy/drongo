/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.Company;
import fr.pfgen.lims.repository.CompanyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eric
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRespository;

    @Override
    public long countAllCompanies() {
        return companyRespository.count();
    }

    @Override
    public void deleteCompany(Company company) {
        companyRespository.delete(company);
    }

    @Override
    public Company findCompany(Long id) {
        return companyRespository.findOne(id);
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRespository.findAll();
    }

    @Override
    public List<Company> findCompanyEntries(int firstResult, int maxResults) {
        return companyRespository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveCompany(Company company) {
        companyRespository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRespository.save(company);
    }

    @Override
    public Company findCompanyByName(String name) {
        return companyRespository.findByName(name);
    }
}
