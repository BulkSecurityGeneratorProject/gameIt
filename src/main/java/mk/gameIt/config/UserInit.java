package mk.gameIt.config;

import mk.gameIt.domain.Role;
import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by ristes on 3/9/16.
 */
@Component
public class UserInit {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @PostConstruct
  public void init() {
    User admin = userRepository.findOneByUsername("admin");
    if (admin == null) {
      admin = new User();
      admin.setUsername("admin");
      admin.setPasswordHash(encoder.encode("admin123"));
      admin.role = Role.ROLE_ADMIN;
      userRepository.save(admin);
    }
  }
}
