package ua.com.domains.dto;

import org.springframework.stereotype.Component;
import ua.com.domains.entity.Domain;
import ua.com.domains.entity.User;

@Component
public class DtoUtilMapper {

    public User userDTOToUser(UserDTO userDTO){
        return new User(userDTO.getName(),userDTO.getEmail());
    }
    public  UserDTO userToUserDTO(User user){
        return new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getPassword());
    }
    public Domain domainDTOToDomain(DomainDTO domainDTO){
        return new Domain(domainDTO.getName());
    }
    public  DomainDTO domainToDomainDTO(Domain category){
        return new DomainDTO(category.getId(),category.getName());
    }

}
