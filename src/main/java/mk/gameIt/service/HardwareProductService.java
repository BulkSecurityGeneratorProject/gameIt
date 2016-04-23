package mk.gameIt.service;

import mk.gameIt.domain.HardwareProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface HardwareProductService {
    HardwareProduct findOne(Long id);
    List<HardwareProduct> findAll();
    Page<HardwareProduct> findAll(Pageable pageable);
    void delete(Long id);
    HardwareProduct save(HardwareProduct hardwareProduct);
}
