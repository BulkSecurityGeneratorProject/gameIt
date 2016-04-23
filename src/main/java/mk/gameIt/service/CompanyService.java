package mk.gameIt.service;

import mk.gameIt.domain.Company;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface CompanyService {
    Company findOne(Long id);
    List<Company> findAll();
    void delete(Long id);
    Company save(Company company);
}
