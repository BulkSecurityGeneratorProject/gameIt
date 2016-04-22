package mk.gameIt.service.impl;

import mk.gameIt.domain.Provider;
import mk.gameIt.domain.Role;
import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void deleteOne(Long id) {

        userRepository.delete(id);
    }

    @Override
    public User updateUser(Long id, UserObject userObject) {
        User user = userRepository.findOne(id);
        user.setFirstName(userObject.getFirstName());
        user.setLastName(userObject.getLastName());
        //userObject.get
        //user.setAdmin(userObject.getAdmin());
       // user.setActivated(userObject.getActivated());
        userRepository.save(user);
        log.debug("Changed Information for User: {}", user);
        return user;
    }

    @Override
    public User createNewUser(UserObject userObject) {
        User user = new User();
        user.setUsername(userObject.getUsername());
        user.setFirstName(userObject.getFirstName());
        user.setLastName(userObject.getLastName());
        user.setEmail(userObject.getEmail());
        String passwordHash = passwordEncoder.encode(userObject.getPassword());
        user.setPasswordHash(passwordHash);
        user.setLangKey("en");
        user.setProvider(Provider.LOCAL);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void changeLangKey(String lang, String username) {
        User user = userRepository.findOneByUsername(username);
        user.setLangKey(lang);
        userRepository.save(user);
        log.debug("Changed Language Key for User: {}", user);
    }

    public User requestPasswordReset(String email) {
        User user = userRepository.findOneByEmail(email);
        if (user.isActivated()) {
            user.setResetKey(RandomUtil.generateResetKey());
            user.setResetDate(ZonedDateTime.now());
            userRepository.save(user);
            return user;
        }
        return null;
    }
    public User finishPasswordReset(String newPassword, String resetKey){
        log.debug("Reset user password for reset key {}", resetKey);

        User user = userRepository.findOneByResetKey(resetKey);
        if (!user.getResetDate().isAfter(ZonedDateTime.now().minusHours(24))) {
            user.setPasswordHash(passwordEncoder.encode(newPassword));
            user.setResetKey(null);
            user.setResetDate(null);
            userRepository.save(user);
            return user;
        }
        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);

        if (user != null) {
            SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole().toString());
            List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
            roles.add(role);

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(), roles);
        } else {
            return null;
        }
    }
}
