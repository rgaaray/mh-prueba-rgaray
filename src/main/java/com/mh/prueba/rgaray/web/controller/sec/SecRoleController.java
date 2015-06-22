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
import com.mh.prueba.rgaray.model.service.WebAppServiceException;
import com.mh.prueba.rgaray.model.service.sec.SecRoleService;
import com.mh.prueba.rgaray.web.controller.AbstractWebAppController;

@Controller
@RequestMapping(value = "/role")
public class SecRoleController extends AbstractWebAppController {
	
	static String VIEW_SEC_ROLE = "sec/secRoleView";
	static String VIEW_SEC_ROLE_ADD = "sec/secRoleViewForm";
	static String VIEW_SEC_ROLE_EDIT = "sec/secRoleViewForm";
	
	static final Logger logger = LoggerFactory.getLogger(SecRoleController.class);
	
	@Autowired
	SecRoleService secRoleService;
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam(value = "secRoleName", required = false) String secRoleName,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
			ModelMap model) {
		
		List<String> errors = new ArrayList<String>();
		view(pageNumber, model, errors, secRoleName);
		
		return VIEW_SEC_ROLE;
		
	}
	
	@RequestMapping(value="/addView", method = {RequestMethod.GET, RequestMethod.POST})
	public String addView(ModelMap model) {
		
		return VIEW_SEC_ROLE_ADD;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody String add(@ModelAttribute(value="secRole") SecRole secRole, BindingResult result ){
		String returnJson = "";
		try {
	        if(!result.hasErrors()){
				secRoleService.save(secRole);
	        	Long count = secRoleService.countAll();
	        	returnJson = "{\"action\":\"add\", " +
	        					"\"text\": \"Rol agregado a la lista. Total de roles: " + count + "\"}";
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
			@RequestParam(value = "secRoleId", required = true) Long secRoleId) {
		List<String> errors = new ArrayList<String>();
		SecRole secRole = null;
		
		try {
			secRole = secRoleService.findOne(secRoleId);
		} catch (WebAppServiceException e) {
			logger.error("No se pudo consultar los registros: " + e.getMessage(), e);
			errors.add("No se pudo consultar los registros.");
		}
		
		if (errors.isEmpty()){
			model.put("secRole", secRole);
		} else {
			model.addAttribute("errors", errors);
		}
		
		return VIEW_SEC_ROLE_EDIT;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, produces="application/json")
    public @ResponseBody String edit(@ModelAttribute(value="secRole") SecRole secRole, BindingResult result ){
		String returnJson = "";
		try {
	        if(!result.hasErrors()){
				secRoleService.update(secRole);
	        	returnJson = "{\"action\":\"edit\", " +
	        					"\"text\": \"Rol actualizado.\"}";
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
			@RequestParam(value = "secRoleId", required = true) Long secRoleId,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
		
		List<String> errors = new ArrayList<String>();
		
		try {
			secRoleService.delete(secRoleId);
		} catch (WebAppServiceException e) {
			logger.error("Error eliminando registro: " + e.getMessage(), e);
			errors.add("Error eliminando registro.");
		}
		
		view(pageNumber, model, errors, "");
		
		return VIEW_SEC_ROLE;
	}
	
	private void view(Integer pageNumber, ModelMap model, List<String> errors, String secRoleName){
		int PAGE_SIZE = 10;
		
		PageRequest request = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "secRoleName");
		Page<SecRole> result = null;
		
		try {
			if (StringUtils.isEmpty(secRoleName)) {
				result = secRoleService.findAll(request);
			} else{
				result = secRoleService.findBySecRoleNameContainingIgnoreCase(secRoleName, request);
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
