package ua.com.domains.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.domains.dao.DomainDao;
import ua.com.domains.dto.DomainDTO;
import ua.com.domains.dto.DtoUtilMapper;
import ua.com.domains.service.DomainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainServiceImpl implements DomainService
{

    @Autowired
    private DomainDao domainDao;

    @Autowired
    private DtoUtilMapper dtoMapper;

    @Override
    public void save(DomainDTO domainDTO) {
        domainDao.save(dtoMapper.domainDTOToDomain(domainDTO));
    }

    @Override
    public void delete(int id) {
        domainDao.delete(id);
    }

    @Override
    public DomainDTO findOne(int id) {
        return dtoMapper.domainToDomainDTO(domainDao.findOne(id));
    }

    @Override
    public void update(DomainDTO domainDTO){
        domainDao.save(dtoMapper.domainDTOToDomain(domainDTO));
    }

    @Override
    public List<DomainDTO> findAll() {
        return domainDao.findAll().stream().map(dtoMapper::domainToDomainDTO).collect(Collectors.toList());
    }

    @Override
    public DomainDTO findOneByName(String name) {
        return dtoMapper.domainToDomainDTO((domainDao.findOneByName(name)));
    }
}
