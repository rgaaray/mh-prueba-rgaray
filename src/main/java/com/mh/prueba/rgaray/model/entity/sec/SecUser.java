package com.mh.prueba.rgaray.model.entity.sec;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mh.prueba.rgaray.web.util.AppUtil;

@Entity
@Table(name = "SEC_USER")
public class SecUser {
	
	@Id
	@GeneratedValue
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecUserIdGenerator")
//	@SequenceGenerator(name = "SecUserIdGenerator", sequenceName = "sec_use_id_seq", allocationSize = 1)
	@Column(name = "SEC_USE_ID", nullable = false)
	private Long secUserId;
	
	@Column(name = "SEC_USE_USERNAME", nullable = true, length = 24)
	private String secUserUsername;
	
	@Column(name = "SEC_USE_PASSWORD", length = 104, nullable = false)
	private String secUserPassword;
	
	@Column(name = "SEC_USE_NAME", nullable = true, length = 104)
	private String secUserName;
	
	@Column(name = "SEC_USE_EMAIL", nullable = true, length = 104)
	private String secUserEmail;
	
	@Column(name = "SEC_USE_BIRTHDATE", nullable = true)
	private Date secUserBirthDate;
	
	@Column(name = "SEC_USE_ACTIVE", length = 1, nullable = false)
	private Integer secUserActive;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEC_ROL_ID", nullable = false)
	private SecRole secRole;
	
	@Transient
	private String secUserBirthDateAsDD_MM_YYYY;
	

	public Long getSecUserId() {
		return secUserId;
	}

	public void setSecUserId(Long secUserId) {
		this.secUserId = secUserId;
	}

	public String getSecUserUsername() {
		return secUserUsername;
	}

	public void setSecUserUsername(String secUserUsername) {
		this.secUserUsername = secUserUsername;
	}

	public String getSecUserPassword() {
		return secUserPassword;
	}

	public void setSecUserPassword(String secUserPassword) {
		this.secUserPassword = secUserPassword;
	}

	public String getSecUserName() {
		return secUserName;
	}

	public void setSecUserName(String secUserName) {
		this.secUserName = secUserName;
	}

	public String getSecUserEmail() {
		return secUserEmail;
	}

	public void setSecUserEmail(String secUserEmail) {
		this.secUserEmail = secUserEmail;
	}

	public Integer getSecUserActive() {
		return secUserActive;
	}

	public void setSecUserActive(Integer secUserActive) {
		this.secUserActive = secUserActive;
	}

	public SecRole getSecRole() {
		return secRole;
	}

	public void setSecRole(SecRole secRole) {
		this.secRole = secRole;
	}

	public Date getSecUserBirthDate() {
		return secUserBirthDate;
	}

	public void setSecUserBirthDate(Date secUserBirthDate) {
		this.secUserBirthDate = secUserBirthDate;
	}

	public String getSecUserBirthDateAsDD_MM_YYYY() {
		if (secUserBirthDate != null)
			return AppUtil.getDateAsDD_MM_YYYY(secUserBirthDate);
		else
			return secUserBirthDateAsDD_MM_YYYY;
	}

	public void setSecUserBirthDateAsDD_MM_YYYY(String secUserBirthDateAsDD_MM_YYYY) {
		this.secUserBirthDateAsDD_MM_YYYY = secUserBirthDateAsDD_MM_YYYY;
	}
	

}
