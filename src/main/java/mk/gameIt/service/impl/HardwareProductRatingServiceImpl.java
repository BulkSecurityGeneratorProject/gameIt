package mk.gameIt.service.impl;

import mk.gameIt.domain.HardwareProductRating;
import mk.gameIt.repository.HardwareProductRatingRepository;
import mk.gameIt.repository.HardwareProductRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.HardwareProductRatingService;
import mk.gameIt.service.HardwareProductService;
import mk.gameIt.web.dto.HardwareRatingObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class HardwareProductRatingServiceImpl implements HardwareProductRatingService {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(HardwareProductRatingServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    HardwareProductRepository hardwareProductRepository;
    @Autowired
    HardwareProductRatingRepository hardwareProductRatingRepository;
    @Autowired
    HardwareProductService hardwareProductService;

    @Transactional
    @Override
    public HardwareProductRating save(HardwareRatingObject hardwareRatingObject) {
        //Todo: potential problem when updating rating;
        HardwareProductRating hardwareProductRating = new HardwareProductRating();
        hardwareProductRating.setUserId(userRepository.findOne(hardwareRatingObject.getUserId()));
        hardwareProductRating.setHardwareId(hardwareProductRepository.findOne(hardwareRatingObject.getHardwareId()));
        hardwareProductRating.setRating(hardwareRatingObject.getRating());
        hardwareProductRating = hardwareProductRatingRepository.save(hardwareProductRating);
        hardwareProductService.calculateRating(hardwareProductRating.getHardwareId(),hardwareProductRating.getRating());
        log.debug("Created HardwareProduct Rating: {}", hardwareProductRating);
        return hardwareProductRating;
    }
}
