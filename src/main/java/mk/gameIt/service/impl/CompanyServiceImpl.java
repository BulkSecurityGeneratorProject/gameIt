package mk.gameIt.service.impl;

import mk.gameIt.domain.Company;
import mk.gameIt.repository.CompanyRepository;
import mk.gameIt.service.CompanyService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(CompanyServiceImpl.class);

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
     //   Company company = companyRepository.findOne(id);
     //   log.debug("Deleted Company: {}", company);
        companyRepository.delete(id);
    }

    @Transactional
    @Override
    public Company save(Company company) {
        company = companyRepository.save(company);
        log.debug("Created Company: {}", company);
        return company;
    }
}
