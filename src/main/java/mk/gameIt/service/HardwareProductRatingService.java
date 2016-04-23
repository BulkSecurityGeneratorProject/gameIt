package mk.gameIt.service;

import mk.gameIt.domain.HardwareProductRating;
import mk.gameIt.web.dto.HardwareRatingObject;

/**
 * Created by Stefan on 23.04.2016.
 */
public interface HardwareProductRatingService {
    HardwareProductRating save(HardwareRatingObject hardwareRatingObject);

}
