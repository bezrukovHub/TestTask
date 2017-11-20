package ua.com.domains.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.domains.entity.Domain;

public interface DomainDao extends JpaRepository<Domain, Integer> {
    Domain findOneByName(String name);
}
