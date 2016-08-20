package mk.gameIt.service;

import mk.gameIt.domain.Location;
import mk.gameIt.web.dto.LocationObject;

import java.util.List;

/**
 * Created by Stefan on 20.8.2016.
 */
public interface LocationService {
     List<Location> findAll();
     Location findOne(Long id);
     Location save(LocationObject object);

}
