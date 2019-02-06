package com.djorquab.relational.relational.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.commons.UserDTO;
import com.djorquab.relational.relational.processor.Processors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BackofficeUtils {
	
	public static final ModelAndView createModelAndView(String template, Object ... params) {
		Map<String, Object> mapParams = initialAuthParams(params);
		return new ModelAndView(template,
				mapParams);
	}
	
	public static final Map<String, Object> initialAuthParams(Object ... params) {
		Map<String, Object> mapParams = Utils.createParams(params);
		if (mapParams == null) {
			mapParams = new HashMap<>();
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails authUser = null;
		if (principal instanceof UserDetails) {
			authUser = (UserDetails) principal;
		}
		mapParams.put("user", UserDTO.builder().username(authUser != null ? authUser.getUsername() : "Guest").build());
		return mapParams;
	}
	
	public static final ModelAndView createModelAndViewWithTableDefinition(String template, Class<?> tableClass, Object ... params) {
		Map<String, Object> mapParams = initialAuthParams(params);
		mapParams.put("tableDefinition", Processors.tableDefinitionInstance().process(tableClass));
		log.debug("Map params {}", mapParams);
		return new ModelAndView(template,
				mapParams);
	}
	
	public static final ModelAndView createModelAndViewWithFormDefinition(String template, Class<?> tableClass, Object ... params) {
		Map<String, Object> mapParams = initialAuthParams(params);
		mapParams.put("formDefinition", Processors.formProcessorInstance().process(tableClass));
		log.debug("Map params {}", mapParams);
		return new ModelAndView(template,
				mapParams);
	}
	
	public static final ModelAndView createModelAndViewWithTableDefinitionAndForm(String template, Class<?> tableClass, Class<?> formClass, Object ... params) {
		Map<String, Object> mapParams = initialAuthParams(params);
		mapParams.put("tableDefinition", Processors.tableDefinitionInstance().process(tableClass));
		mapParams.put("formDefinition", Processors.formProcessorInstance().process(formClass));
		log.debug("Map params {}", mapParams);
		log.debug("Form entity {} - {}", mapParams.containsKey(BackofficeConstants.FORM_OBJECT), mapParams.get(BackofficeConstants.FORM_OBJECT));
		return new ModelAndView(template,
				mapParams);
	}
	
	public static final ModelAndView fragmentWithTableDefinition(String template, Class<?> tableClass, Object ... params) {
		Map<String, Object> mapParams = initialAuthParams(params);
		mapParams.put("definition", Processors.tableDefinitionInstance().process(tableClass));
		PagedResult<?> pagedResult = (PagedResult<?>)mapParams.get(BackofficeConstants.TABLE_RESULT);
		if (pagedResult != null) {
			mapParams.put("elements", pagedResult.getElements());
			mapParams.put("page", pagedResult.getPage());
			mapParams.put("size", pagedResult.getPageSize());
			mapParams.put("numberOfPages", pagedResult.getNumberOfPages());
		}
		log.debug("Map params {}", mapParams);
		return new ModelAndView(template,
				mapParams);
	}
}
