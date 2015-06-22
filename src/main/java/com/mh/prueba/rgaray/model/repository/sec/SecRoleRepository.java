package com.mh.prueba.rgaray.model.repository.sec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mh.prueba.rgaray.model.entity.sec.SecRole;

@Repository
public interface SecRoleRepository extends JpaRepository<SecRole, Long> {
	
	public Page<SecRole> findBySecRoleNameContainingIgnoreCase(String secRoleName, Pageable pageable);

}
