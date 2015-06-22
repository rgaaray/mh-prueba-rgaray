package com.mh.prueba.rgaray.model.service.sec;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mh.prueba.rgaray.model.entity.sec.SecRole;
import com.mh.prueba.rgaray.model.repository.sec.SecRoleRepository;
import com.mh.prueba.rgaray.model.service.WebAppServiceException;

@Service
@Transactional
public class SecRoleService {
	
	@Resource
	SecRoleRepository secRoleRepository;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecRole findOne(Long secRoleId) throws WebAppServiceException{
		SecRole secRole = secRoleRepository.findOne(secRoleId);
		return secRole;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecRole> findAll() throws WebAppServiceException{
		List<SecRole> list = secRoleRepository.findAll();
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecRole> findAll(Pageable pageable) throws WebAppServiceException{
		Page<SecRole> page = secRoleRepository.findAll(pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecRole> findBySecRoleNameContainingIgnoreCase(String secRoleName, Pageable pageable) throws WebAppServiceException{
		Page<SecRole> page = secRoleRepository.findBySecRoleNameContainingIgnoreCase(secRoleName, pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Long countAll() throws WebAppServiceException{
		Long count = secRoleRepository.count();
		return count;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void save(SecRole secRole) throws WebAppServiceException{
		secRoleRepository.save(secRole);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void update(SecRole secRole) throws WebAppServiceException{
		secRoleRepository.save(secRole);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void delete(Long secRoleId) throws WebAppServiceException{
		secRoleRepository.delete(secRoleId);
	}

}
