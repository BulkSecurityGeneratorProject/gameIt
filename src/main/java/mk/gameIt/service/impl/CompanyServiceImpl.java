package mk.gameIt.service.impl;

import mk.gameIt.domain.Company;
import mk.gameIt.repository.CompanyRepository;
import mk.gameIt.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findOne(Long id) {
        return companyRepository.findOne(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Transactional
    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
