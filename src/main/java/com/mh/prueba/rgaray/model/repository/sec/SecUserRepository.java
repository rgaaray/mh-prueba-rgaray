package com.mh.prueba.rgaray.model.repository.sec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mh.prueba.rgaray.model.entity.sec.SecUser;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser, Long> {
	
	public Page<SecUser> findBySecUserUsernameContainingIgnoreCase(String secUserUsername, Pageable pageable);
	
	@Query(value = "SELECT existuser(?1)", nativeQuery = true)
	public Integer existUser(String secUserUsername);
}
