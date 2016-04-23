package mk.gameIt.service.impl;

import mk.gameIt.domain.HardwareProduct;
import mk.gameIt.repository.HardwareProductRepository;
import mk.gameIt.service.HardwareProductService;
import org.slf4j.Logger;
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
    private final Logger log = org.slf4j.LoggerFactory.getLogger(HardwareProductServiceImpl.class);

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
    public synchronized HardwareProduct incrementNumberOfViews(HardwareProduct hardwareProduct) {
        hardwareProduct.setHardwareNumberOfViews(hardwareProduct.getHardwareNumberOfViews() + 1);
        return hardwareProductRepository.save(hardwareProduct);
    }

    @Override
    public void delete(Long id) {
        //HardwareProduct hardwareProduct = hardwareProductRepository.findOne(id);
        //log.debug("Created HardwareProduct: {}", hardwareProduct);
        hardwareProductRepository.delete(id);
    }

    @Override
    public HardwareProduct save(HardwareProduct hardwareProduct) {
        hardwareProduct = hardwareProductRepository.save(hardwareProduct);
        log.debug("Created HardwareProduct: {}", hardwareProduct);
        return hardwareProduct;
    }

    @Override
    public void calculateRating(HardwareProduct hardwareId, int rating) {

    }
}
