package ua.com.domains.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.domains.dao.UserDao;
import ua.com.domains.dto.DtoUtilMapper;
import ua.com.domains.dto.UserDTO;
import ua.com.domains.entity.User;
import ua.com.domains.service.UserService;
import ua.com.domains.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserServiceImpl implements UserService ,UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByName(username);

    }


    private BCryptPasswordEncoder encoder;

    public void save(User user) throws Exception {

        validator.validate(user);

       user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);

    }
    @Autowired
    private DtoUtilMapper dtoMapper;

    @Override
    public void save(UserDTO userDTO) throws Exception {

        validator.validate(userDTO);

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDao.save(dtoMapper.userDTOToUser(userDTO));
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public UserDTO findOne(int id) {
        return dtoMapper.userToUserDTO(userDao.findOne(id));
    }

    @Override
    public List<UserDTO> findAll() {
        return userDao.findAll().stream().map(u -> dtoMapper.userToUserDTO(u)).collect(Collectors.toList());
    }

    public User findByUUID(String uuid) {
        return userDao.findByUUID(uuid);
    }

    public void update(User user) {
        userDao.save(user);
    }



}