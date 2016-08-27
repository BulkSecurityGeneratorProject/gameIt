package mk.gameIt.service;

import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;
import mk.gameIt.web.exceptions.EmailExistsException;
import mk.gameIt.web.exceptions.UsernameExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stefan on 24.03.2016.
 */
public interface UserService {
    List<User> findAll();

    User findOne(Long id);

    User findOneByUsername(String username);

    void deleteOne(Long id);

    User updateUser(String username, UserObject userObject);

    User createNewUser(UserObject userObject) throws IOException, SQLException, UsernameExistsException, EmailExistsException;

    User currentLoggedInUser();

    void updateUserImage(MultipartFile image) throws IOException, SQLException;

    void changeLangKey(String lang, String username);
}
