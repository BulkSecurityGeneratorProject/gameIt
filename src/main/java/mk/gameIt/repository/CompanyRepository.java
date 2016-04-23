package mk.gameIt.repository;

import mk.gameIt.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
