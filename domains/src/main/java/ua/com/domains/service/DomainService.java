package ua.com.domains.service;

import ua.com.domains.dto.DomainDTO;

import java.util.List;


public interface DomainService{

    void save(DomainDTO domainDTO);
    void delete(int id);
    DomainDTO findOne(int id);
    List<DomainDTO> findAll();

    void update(DomainDTO domainDTO);

    DomainDTO findOneByName(String name);
}
