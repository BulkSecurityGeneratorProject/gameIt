package mk.gameIt.config;

import mk.gameIt.domain.Role;
import mk.gameIt.domain.User;
import mk.gameIt.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

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
  public void init() throws IOException, SQLException {
    User admin = userRepository.findOneByUsername("admin");
    if (admin == null) {
      admin = new User();
      admin.setUsername("admin");
      admin.setPasswordHash(encoder.encode("admin123"));
      admin.role = Role.ROLE_ADMIN;
      File img = ResourceUtils.getFile("classpath:static/images/defaultProfileImage.png");
      byte[] imag = IOUtils.toByteArray(new FileInputStream(img));
      Blob imageBlob = new SerialBlob(imag);
      admin.setProfileImage(imageBlob);
      userRepository.save(admin);
    }
  }
}
