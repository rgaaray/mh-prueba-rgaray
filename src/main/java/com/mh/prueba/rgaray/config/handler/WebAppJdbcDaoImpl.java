package com.mh.prueba.rgaray.config.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Component;

/**
 * Servicio para personalizar UserDetails.
 *
 * @author rene.garay
 * @version 13/01/2015
 *
 */
@Component("userDetailsService")
public class WebAppJdbcDaoImpl extends JdbcDaoImpl {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	@Value(
			"SELECT " +
				"SEC_USE_USERNAME" +
				", SEC_USE_PASSWORD" +
				", SEC_USE_ACTIVE " +
			"FROM " +
				"SEC_USER " +
			"WHERE " +
				"SEC_USE_USERNAME = ?"
			)
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		super.setUsersByUsernameQuery(usersByUsernameQueryString);
	}

	@Override
	@Value(
			"SELECT " + 
				"USE.SEC_USE_USERNAME, " +
				"ROL.SEC_ROL_NAME " +
			"FROM SEC_USER_ROLE USEROL " +
			    "INNER JOIN SEC_USER USE ON " +
				"USE.SEC_USE_ID = USEROL.SEC_USE_ID " +
			    "INNER JOIN SEC_ROLE ROL ON " +
				"ROL.SEC_ROL_ID = USEROL.SEC_ROL_ID " +
			"WHERE " +
				"USE.SEC_USE_USERNAME = ?" 
			)
	public void setAuthoritiesByUsernameQuery(String queryString) {
		super.setAuthoritiesByUsernameQuery(queryString);
	}

	@Override
	public List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new String[] { username },
				new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						String username = rs.getString("SEC_USE_USERNAME");
						String password = rs.getString("SEC_USE_PASSWORD");
						boolean enabled = rs.getBoolean("SEC_USE_ACTIVE");
						boolean accountNonExpired = true;
						boolean credentialsNonExpired = true;
						boolean accountNonLocked = true;

						return new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
								accountNonLocked, AuthorityUtils.NO_AUTHORITIES);
					}

				});
	}

	@Override
	public UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!super.isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}

		return new User(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
				userFromUserQuery.isAccountNonExpired(), userFromUserQuery.isCredentialsNonExpired(),
				userFromUserQuery.isAccountNonLocked(), combinedAuthorities);
	}
	
}