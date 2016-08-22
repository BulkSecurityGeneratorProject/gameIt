package mk.gameIt.service.impl;

import mk.gameIt.domain.Provider;
import mk.gameIt.domain.Role;
import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;
import org.apache.commons.io.IOUtils;
import org.hibernate.type.BlobType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate3.support.BlobByteArrayType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mk.gameIt.repository.UserRepository;
import mk.gameIt.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    @Transactional
    public void deleteOne(Long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public User updateUser(String username, UserObject userObject) {
        User user = userRepository.findOneByUsername(username);
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
    @Transactional
    public User createNewUser(UserObject userObject) throws IOException, SQLException {
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
        if (userObject.getProfileImage() == null) {
            File img = ResourceUtils.getFile("classpath:static/images/defaultProfileImage.png");
            byte[] imag = IOUtils.toByteArray(new FileInputStream(img));
            Blob imageBlob = new SerialBlob(imag);
            user.setProfileImage(imageBlob);
        }
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    @Transactional
    public void updateUserImage(MultipartFile image) throws IOException, SQLException {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            User user = findOneByUsername(username);
            if (user != null) {
                Blob pictureBlob = null;
                if (image != null && "image/png".equals(image.getContentType())) {
                    pictureBlob = new SerialBlob(image.getBytes());
                    user.setProfileImage(pictureBlob);
                    userRepository.save(user);
                }
            }
        }
    }

    @Override
    @Transactional
    public void changeLangKey(String lang, String username) {
        User user = userRepository.findOneByUsername(username);
        user.setLangKey(lang);
        userRepository.save(user);
        log.debug("Changed Language Key for User: {}", user);
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
