package mk.gameIt.service;

import mk.gameIt.domain.User;
import mk.gameIt.web.dto.UserObject;

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

    void deleteOne(Long id);

    User updateUser(Long id, UserObject userObject);

    User createNewUser(UserObject userObject) throws IOException, SQLException;

    void deleteAll();

    void changeLangKey(String lang, String username);
}
