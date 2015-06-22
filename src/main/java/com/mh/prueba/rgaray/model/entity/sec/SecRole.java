package com.mh.prueba.rgaray.model.entity.sec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEC_ROLE")
public class SecRole {
	
	@Id
	@GeneratedValue
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecRoleIdGenerator")
//	@SequenceGenerator(name = "SecRoleIdGenerator", sequenceName = "sec_rol_id_seq", allocationSize = 1)
	@Column(name = "SEC_ROL_ID", nullable = false)
	private Long secRoleId;
	
	@Column(name = "SEC_ROL_NAME", nullable = true, length = 24)
	private String secRoleName;
	
	@Column(name = "SEC_ROL_ACTIVE", length = 1, nullable = false)
	private Integer secRoleActive;

	
	public Long getSecRoleId() {
		return secRoleId;
	}

	public void setSecRoleId(Long secRoleId) {
		this.secRoleId = secRoleId;
	}

	public String getSecRoleName() {
		return secRoleName;
	}

	public void setSecRoleName(String secRoleName) {
		this.secRoleName = secRoleName;
	}

	public Integer getSecRoleActive() {
		return secRoleActive;
	}

	public void setSecRoleActive(Integer secRoleActive) {
		this.secRoleActive = secRoleActive;
	}
	
}
