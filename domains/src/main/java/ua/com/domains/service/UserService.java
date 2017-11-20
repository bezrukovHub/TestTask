package ua.com.domains.service;


import ua.com.domains.dto.UserDTO;
import ua.com.domains.entity.User;

import java.util.List;

public interface UserService {

    void save(UserDTO userDTO) throws Exception;
    void save(User user) throws Exception;
    void delete(int id);
    UserDTO findOne(int id);
    List<UserDTO> findAll();
    void update (User user);

    User findByUUID(String uuid);

}
