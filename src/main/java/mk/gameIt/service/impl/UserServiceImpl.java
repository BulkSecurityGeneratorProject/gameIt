package mk.gameIt.service.impl;

import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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
        //user.setAdmin(userObject.getAdmin());
        //user.setActive(userObject.getActive());
        userRepository.save(user);
        return user;
    }

    @Override
    public User createNewUser(UserObject userObject) {
        User user = new User();
        user.setUsername(userObject.getUsername());
        user.setFirstName(userObject.getFirstName());
        user.setLastName(userObject.getLastName());
        user.setEmail(userObject.getLastName());
        String passwordHash = passwordEncoder.encode(userObject.getPassword());
        user.setPasswordHash(passwordHash);
        return userRepository.save(user);
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
