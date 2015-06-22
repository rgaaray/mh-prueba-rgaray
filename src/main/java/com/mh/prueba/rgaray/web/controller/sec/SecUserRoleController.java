package com.mh.prueba.rgaray.web.controller.sec;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mh.prueba.rgaray.model.entity.sec.SecRole;
import com.mh.prueba.rgaray.model.entity.sec.SecUser;
import com.mh.prueba.rgaray.model.entity.sec.SecUserRole;
import com.mh.prueba.rgaray.model.service.WebAppServiceException;
import com.mh.prueba.rgaray.model.service.sec.SecRoleService;
import com.mh.prueba.rgaray.model.service.sec.SecUserRoleService;
import com.mh.prueba.rgaray.model.service.sec.SecUserService;
import com.mh.prueba.rgaray.web.controller.AbstractWebAppController;

@Controller
@RequestMapping(value = "/user/role")
public class SecUserRoleController extends AbstractWebAppController {
	
	static String VIEW_SEC_USER_ROLE = "sec/secUserRoleView";
	
	static final Logger logger = LoggerFactory.getLogger(SecUserRoleController.class);
	
	@Autowired
	SecUserRoleService secUserRoleService;
	
	@Autowired
	SecUserService secUserService;
	
	@Autowired
	SecRoleService secRoleService;
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value = "secRoleName", required = false) String secRoleName,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "secUserId", required = false) Long secUserId,
			ModelMap model) {
		
		List<String> errors = new ArrayList<String>();
		view(pageNumber, model, errors, secRoleName, secUserId);
		
		return VIEW_SEC_USER_ROLE;
		
	}
	
	@RequestMapping(value="/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@RequestParam(value = "secUserId", required = false) Long secUserId,
			@RequestParam(value = "secRoleId", required = false) Long secRoleId,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
			ModelMap model) {
		
		List<String> errors = new ArrayList<String>();
		SecUser secUser = null;
		SecRole secRole = null;
		SecUserRole secUserRole = new SecUserRole();
		
		try {
			secUser = secUserService.findOne(secUserId);
			secRole = secRoleService.findOne(secRoleId);
			secUserRole.setSecUser(secUser);
			secUserRole.setSecRole(secRole);
			secUserRoleService.save(secUserRole);
		} catch (WebAppServiceException e) {
			logger.error("No se pudo agregar el registro: " + e.getMessage(), e);
			errors.add("No se pudo agregar el registro.");
		}
		
		view(pageNumber, model, errors, "", secUserId);
		
		return VIEW_SEC_USER_ROLE;
		
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value = "secRoleName", required = false) String secRoleName,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "secUserId", required = false) Long secUserId,
			@RequestParam(value = "secUserRoleId", required = false) Long secUserRoleId,
			ModelMap model) {
		
		List<String> errors = new ArrayList<String>();
		
		try {
			secUserRoleService.delete(secUserRoleId);
		} catch (WebAppServiceException e) {
			logger.error("Error eliminando registros: " + e.getMessage(), e);
			errors.add("Error eliminando registro.");
		}
		
		view(pageNumber, model, errors, "", secUserId);
		
		return VIEW_SEC_USER_ROLE;
		
	}
	
	private void view(Integer pageNumber, ModelMap model, List<String> errors, String secRoleName, Long secUserId){
		int PAGE_SIZE = 10;
		
		PageRequest request = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "secRole.secRoleName");
		Page<SecUserRole> result = null;
		SecUser secUser = null;
		List<SecRole> secRole = null;
		
		try {
			secUser = secUserService.findOne(secUserId);
			secRole = secRoleService.findAll();
			
			if (StringUtils.isEmpty(secRoleName)) {
				result = secUserRoleService.findBySecUser(secUserId, request);
			} else{
				result = secUserRoleService.findBySecRoleSecRoleNameContainingIgnoreCase(secRoleName, request);
			}
		} catch (WebAppServiceException e) {
			logger.error("No se pudo consultar los registros: " + e.getMessage(), e);
			errors.add("No se pudo consultar los registros.");
		}
		
		if (errors.isEmpty()){
			model.put("currentPage", result.getNumber());
			model.put("totalPages", result.getTotalPages());
			model.put("totalElements", result.getTotalElements());
			model.put("result", result.getContent());
			model.put("secRole", secRole);
		} else {
			model.addAttribute("errors", errors);
		}
		
		model.put("secUser", secUser);
		
	}
	
}
