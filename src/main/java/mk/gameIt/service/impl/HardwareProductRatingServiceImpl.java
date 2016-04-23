package mk.gameIt.service.impl;

import mk.gameIt.domain.HardwareProductRating;
import mk.gameIt.repository.HardwareProductRatingRepository;
import mk.gameIt.repository.HardwareProductRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.HardwareProductRatingService;
import mk.gameIt.web.dto.HardwareRatingObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Stefan on 23.04.2016.
 */
@Service
public class HardwareProductRatingServiceImpl implements HardwareProductRatingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    HardwareProductRepository hardwareProductRepository;
    @Autowired
    HardwareProductRatingRepository hardwareProductRatingRepository;

    @Transactional
    @Override
    public HardwareProductRating save(HardwareRatingObject hardwareRatingObject) {
        HardwareProductRating hardwareProductRating = new HardwareProductRating();
        hardwareProductRating.setUserId(userRepository.findOne(hardwareRatingObject.getUserId()));
        hardwareProductRating.setHardwareId(hardwareProductRepository.findOne(hardwareRatingObject.getHardwareId()));
        hardwareProductRating.setRating(hardwareRatingObject.getRating());
        return hardwareProductRatingRepository.save(hardwareProductRating);
    }
}
