package com.djorquab.relational.relational.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.bo.User;
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
	
	private static final Map<String, Object> initialAuthParams(Object ... params) {
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("Current params {}, size {}", params, params.length);
		Map<String, Object> mapParams = Utils.createParams(params);
		if (mapParams == null) {
			mapParams = new HashMap<>();
		}
		mapParams.put("user", UserDTO.builder().username(authUser.getUsername()).build());
		log.info("initialAuthParams - Auth map with params {}", mapParams);
		return mapParams;
	}
	
	public static final ModelAndView createModelAndViewWithTableDefinition(String template, Class<?> tableClass, Object ... params) {
		Map<String, Object> mapParams = initialAuthParams(params);
		mapParams.put("tableDefinition", Processors.tableDefinitionInstance().process(tableClass));
		log.info("Map params {}", mapParams);
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
		log.info("Map params {}", mapParams);
		return new ModelAndView(template,
				mapParams);
	}
}
