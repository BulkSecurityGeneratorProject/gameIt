package mk.gameIt.service.impl;

import mk.gameIt.domain.HardwareProduct;
import mk.gameIt.repository.HardwareProductRepository;
import mk.gameIt.service.HardwareProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
@Transactional
public class HardwareProductServiceImpl implements HardwareProductService {
    @Autowired
    HardwareProductRepository hardwareProductRepository;

    @Override
    public HardwareProduct findOne(Long id) {
        return hardwareProductRepository.findOne(id);
    }

    @Override
    public List<HardwareProduct> findAll() {
        return hardwareProductRepository.findAll();
    }

    @Override
    public Page<HardwareProduct> findAll(Pageable pageable) {
        return hardwareProductRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        hardwareProductRepository.delete(id);
    }

    @Override
    public HardwareProduct save(HardwareProduct hardwareProduct) {
        return hardwareProductRepository.save(hardwareProduct);
    }
}
