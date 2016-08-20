package mk.gameIt.service.impl;

import mk.gameIt.domain.Location;
import mk.gameIt.domain.User;
import mk.gameIt.repository.LocationRepository;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.LocationService;
import mk.gameIt.web.dto.LocationObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stefan on 20.8.2016.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findOne(Long id) {
        return locationRepository.findOne(id);
    }

    @Override
    public Location save(LocationObject object) {
        Location location = new Location();
        location.setLatitude(object.getLatitude());
        location.setLongitude(object.getLongitude());
        User user = userRepository.findOneByUsername(object.getUsername());
        location.setUserId(user);
        location = locationRepository.save(location);
        user.getLocations().add(location);
        userRepository.save(user);
        return location;
    }
}
