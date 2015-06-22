package com.mh.prueba.rgaray.model.service.sec;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mh.prueba.rgaray.model.entity.sec.SecUser;
import com.mh.prueba.rgaray.model.entity.sec.SecUserRole;
import com.mh.prueba.rgaray.model.repository.sec.SecUserRepository;
import com.mh.prueba.rgaray.model.repository.sec.SecUserRoleRepository;
import com.mh.prueba.rgaray.model.service.WebAppServiceException;

@Service
@Transactional
public class SecUserRoleService {
	
	@Resource
	SecUserRoleRepository secUserRoleRepository;
	
	@Resource
	SecUserRepository secUserRepository;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecUserRole findOne(Long secUserRoleId) throws WebAppServiceException{
		SecUserRole secUserRole = secUserRoleRepository.findOne(secUserRoleId);
		return secUserRole;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecUserRole> findAll() throws WebAppServiceException{
		List<SecUserRole> list = secUserRoleRepository.findAll();
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecUserRole> findAll(Pageable pageable) throws WebAppServiceException{
		Page<SecUserRole> page = secUserRoleRepository.findAll(pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecUserRole> findBySecUser(Long secUserId, Pageable pageable) throws WebAppServiceException{
		SecUser secUser = secUserRepository.findOne(secUserId);
		Page<SecUserRole> page = secUserRoleRepository.findBySecUser(secUser, pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecUserRole> findBySecRoleSecRoleNameContainingIgnoreCase(String secRoleName, Pageable pageable) throws WebAppServiceException{
		Page<SecUserRole> page = secUserRoleRepository.findBySecRoleSecRoleNameContainingIgnoreCase(secRoleName, pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Long countAll() throws WebAppServiceException{
		Long count = secUserRoleRepository.count();
		return count;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void save(SecUserRole secUserRole) throws WebAppServiceException{
		secUserRoleRepository.save(secUserRole);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void update(SecUserRole secUserRole) throws WebAppServiceException{
		secUserRoleRepository.save(secUserRole);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void delete(Long secUserRoleId) throws WebAppServiceException{
		secUserRoleRepository.delete(secUserRoleId);
	}

}
