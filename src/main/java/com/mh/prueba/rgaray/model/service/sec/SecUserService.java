package com.mh.prueba.rgaray.model.service.sec;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mh.prueba.rgaray.model.entity.sec.SecUser;
import com.mh.prueba.rgaray.model.repository.sec.SecUserRepository;
import com.mh.prueba.rgaray.model.service.WebAppServiceException;

@Service
@Transactional
public class SecUserService {
	
	@Resource
	SecUserRepository secUserRepository;
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecUser findOne(Long secUserId) throws WebAppServiceException{
		SecUser secUser = secUserRepository.findOne(secUserId);
		return secUser;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecUser> findAll() throws WebAppServiceException{
		List<SecUser> list = secUserRepository.findAll();
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecUser> findAll(Pageable pageable) throws WebAppServiceException{
		Page<SecUser> page = secUserRepository.findAll(pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SecUser> findBySecUserUsernameContainingIgnoreCase(String secUserUsername, Pageable pageable) throws WebAppServiceException{
		Page<SecUser> page = secUserRepository.findBySecUserUsernameContainingIgnoreCase(secUserUsername, pageable);
		return page;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Long countAll() throws WebAppServiceException{
		Long count = secUserRepository.count();
		return count;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void save(SecUser secUser) throws WebAppServiceException{
		secUserRepository.save(secUser);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void update(SecUser secUser) throws WebAppServiceException{
		secUserRepository.save(secUser);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class, WebAppServiceException.class})
	public void delete(Long secUserId) throws WebAppServiceException{
		secUserRepository.delete(secUserId);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Integer existUser(String secUserUsername) throws WebAppServiceException{
		Integer exist = secUserRepository.existUser(secUserUsername);
		return exist;
	}

}
