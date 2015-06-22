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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mh.prueba.rgaray.model.entity.sec.SecRole;
import com.mh.prueba.rgaray.model.entity.sec.SecUser;
import com.mh.prueba.rgaray.model.service.WebAppServiceException;
import com.mh.prueba.rgaray.model.service.sec.SecRoleService;
import com.mh.prueba.rgaray.model.service.sec.SecUserService;
import com.mh.prueba.rgaray.web.controller.AbstractWebAppController;
import com.mh.prueba.rgaray.web.util.AppUtil;

@Controller
@RequestMapping(value = "/user")
public class SecUserController extends AbstractWebAppController {
	
	static String VIEW_SEC_USER = "sec/secUserView";
	static String VIEW_SEC_USER_ADD = "sec/secUserViewForm";
	static String VIEW_SEC_USER_EDIT = "sec/secUserViewForm";
	
	static final Logger logger = LoggerFactory.getLogger(SecUserController.class);
	
	@Autowired
	SecUserService secUserService;
	
	@Autowired
	SecRoleService secRoleService;
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value = "secUserUsername", required = false) String secUserUsername,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
			ModelMap model) {
		
		List<String> errors = new ArrayList<String>();
		view(pageNumber, model, errors, secUserUsername);
		
		return VIEW_SEC_USER;
		
	}
	
	@RequestMapping(value="/addView", method = {RequestMethod.GET, RequestMethod.POST})
	public String addView(ModelMap model) {
		List<String> errors = new ArrayList<String>();
		List<SecRole> secRole = null;
		
		try {
			secRole = secRoleService.findAll();
		} catch (WebAppServiceException e) {
			logger.error("No se pudo consultar los registros: " + e.getMessage(), e);
			errors.add("No se pudo consultar los registros.");
		}
		
		if (errors.isEmpty()){
			model.put("secRole", secRole);
		} else {
			model.addAttribute("errors", errors);
		}
		
		return VIEW_SEC_USER_ADD;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody String add(@ModelAttribute(value="secUser") SecUser secUser, BindingResult result ){
		String returnJson = "";
		try {
	        if(!result.hasErrors()){
				secUserService.save(secUser);
	        	Long count = secUserService.countAll();
	        	returnJson = "{\"action\":\"add\", " +
	        					"\"text\": \"Usuario agregado a la lista. Total de usuarios: " + count + "\"}";
	        }else{
	        	returnJson = "{\"action\":\"add\", "
	        					+ "\"text\":\"Error al agregar registro.\"}";
	        }
		} catch (WebAppServiceException e) {
			logger.error("Error al agregar registro: " + e.getMessage(), e);
			returnJson = "{\"action\":\"add\", "
					+ "\"text\":\"Error al agregar registro.\"}";
		}
		
        return returnJson;
    }
	
	@RequestMapping(value="/editView", method = {RequestMethod.GET, RequestMethod.POST})
	public String editView(ModelMap model,
			@RequestParam(value = "secUserId", required = true) Long secUserId) {
		List<String> errors = new ArrayList<String>();
		List<SecRole> secRole = null;
		SecUser secUser = null;
		
		try {
			secRole = secRoleService.findAll();
			secUser = secUserService.findOne(secUserId);
		} catch (WebAppServiceException e) {
			logger.error("No se pudo consultar los registros: " + e.getMessage(), e);
			errors.add("No se pudo consultar los registros.");
		}
		
		if (errors.isEmpty()){
			model.put("secRole", secRole);
			model.put("secUser", secUser);
		} else {
			model.addAttribute("errors", errors);
		}
		
		return VIEW_SEC_USER_EDIT;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody String edit(@ModelAttribute(value="secUser") SecUser secUser, BindingResult result ){
		String returnJson = "";
		try {
	        if(!result.hasErrors()){
	        	secUser.setSecUserBirthDate(AppUtil.getDateFromDD_MM_YYYY(secUser.getSecUserBirthDateAsDD_MM_YYYY()));
				secUserService.update(secUser);
	        	returnJson = "{\"action\":\"edit\", " +
	        					"\"text\": \"Usuario actualizado.\"}";
	        }else{
	        	returnJson = "{\"action\":\"edit\", "
	        					+ "\"text\":\"Error al actualizar registro.\"}";
	        }
		} catch (WebAppServiceException e) {
			logger.error("Error al agregar registro: " + e.getMessage(), e);
			returnJson = "{\"action\":\"edit\", "
					+ "\"text\":\"Error al actualizar registro.\"}";
		}
		
        return returnJson;
    }
	
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(ModelMap model,
			@RequestParam(value = "secUserId", required = true) Long secUserId,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
		List<String> errors = new ArrayList<String>();
		
		try {
			secUserService.delete(secUserId);
		} catch (WebAppServiceException e) {
			logger.error("Error eliminando registro: " + e.getMessage(), e);
			errors.add("Error eliminando registro.");
		}
		
		view(pageNumber, model, errors, "");
		
		return VIEW_SEC_USER;
	}
	
	private void view(Integer pageNumber, ModelMap model, List<String> errors, String secUserUsername){
		int PAGE_SIZE = 10;
		
		PageRequest request = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "secUserUsername");
		Page<SecUser> result = null;
		
		try {
			if (StringUtils.isEmpty(secUserUsername)) {
				result = secUserService.findAll(request);
			} else{
				result = secUserService.findBySecUserUsernameContainingIgnoreCase(secUserUsername, request);
			}
		} catch (WebAppServiceException e) {
			logger.error("Error eliminando registro: " + e.getMessage(), e);
			errors.add("No se pudo consultar los registros.");
		}
		
		if (errors.isEmpty()){
			model.put("currentPage", result.getNumber());
			model.put("totalPages", result.getTotalPages());
			model.put("totalElements", result.getTotalElements());
			model.put("result", result.getContent());
		} else {
			model.addAttribute("errors", errors);
		}
	}


}
