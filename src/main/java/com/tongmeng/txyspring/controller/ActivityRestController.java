package com.tongmeng.txyspring.controller;

import java.util.List;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.ActDetailAjax;
import com.tongmeng.txyspring.ajaxmodel.ActInfoAjax;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.service.ActivityService;

@RestController
@RequestMapping(value = "/api/activity")
public class ActivityRestController {

	@Autowired
	private ActivityService activityService;

	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/GetActivities", method = RequestMethod.GET)
	public AjaxResponseBody<List<ActInfoAjax>> GetActivities(@RequestParam(value = "type", required = true) int type,
			@RequestParam(value = "campus", required = false, defaultValue = "0") int campus,
			@RequestParam(value = "subtype", required = false, defaultValue = "0") int subtype,
			@RequestParam(value = "sort", required = false, defaultValue = "1") int sort,
			@RequestParam(value = "p", required = false, defaultValue = "0") int p) throws CredentialException {


		List<ActInfoAjax> ajaxLstAct = activityService.listActivitiesByActCodeAndSchCode(campus, subtype, sort, p,
				type);
		return new AjaxResponseBody<List<ActInfoAjax>>(RESPONSE_STATUS.SUCCESS, ajaxLstAct);

	}

	@JsonView(AjaxJsonViews.Public.class)
	@RequestMapping(value = "/GetDetail", method = RequestMethod.GET)
	public AjaxResponseBody<ActDetailAjax> GetDetail(@RequestParam(value = "id", required = true) int id) {

		ActDetailAjax actInfoAjax = activityService.getActivitiyDetail(id);
		return new AjaxResponseBody<ActDetailAjax>(RESPONSE_STATUS.SUCCESS, actInfoAjax);
	}

}
