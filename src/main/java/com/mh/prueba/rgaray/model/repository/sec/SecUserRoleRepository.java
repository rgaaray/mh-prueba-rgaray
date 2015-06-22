package com.mh.prueba.rgaray.model.repository.sec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.prueba.rgaray.model.entity.sec.SecUser;
import com.mh.prueba.rgaray.model.entity.sec.SecUserRole;

public interface SecUserRoleRepository extends JpaRepository<SecUserRole, Long> {
	
	public Page<SecUserRole> findBySecRoleSecRoleNameContainingIgnoreCase(String secRoleName, Pageable pageable);
	public Page<SecUserRole> findBySecUser(SecUser secUser, Pageable pageable);
	
}
