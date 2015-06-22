package com.mh.prueba.rgaray.model.entity.sec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SEC_USER_ROLE")
public class SecUserRole {
	
	@Id
	@GeneratedValue
	@Column(name = "SEC_USE_ROL_ID", nullable = false)
	private Long secUserRoleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEC_USE_ID", nullable = false)
	private SecUser secUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEC_ROL_ID", nullable = false)
	private SecRole secRole;

	public Long getSecUserRoleId() {
		return secUserRoleId;
	}

	public void setSecUserRoleId(Long secUserRoleId) {
		this.secUserRoleId = secUserRoleId;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}

	public SecRole getSecRole() {
		return secRole;
	}

	public void setSecRole(SecRole secRole) {
		this.secRole = secRole;
	}

}
