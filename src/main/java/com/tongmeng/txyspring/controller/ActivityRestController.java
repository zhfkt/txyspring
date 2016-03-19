package com.tongmeng.txyspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.ajaxmodel.ActInfoAjax;
import com.tongmeng.txyspring.ajaxmodel.AjaxJsonViews;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody;
import com.tongmeng.txyspring.ajaxmodel.AjaxResponseBody.RESPONSE_STATUS;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.service.ActivityService;
import com.tongmeng.txyspring.service.UserService;

@RestController
@RequestMapping(value = "/api/activity")
public class ActivityRestController {

	@Autowired
	private ActivityService as;
	
	@Autowired
	private UserService us;	

	@JsonView(AjaxJsonViews.ActInfo.class)
	@RequestMapping(value = "/GetActivities", method = RequestMethod.GET)
	public AjaxResponseBody<List<ActInfoAjax>> GetActivities(@RequestParam(value = "type", required = true) int type,
			@RequestParam(value = "campus", required = false, defaultValue = "0") int campus,
			@RequestParam(value = "subtype", required = false, defaultValue = "0") int subtype,
			@RequestParam(value = "sort", required = false, defaultValue = "0") int sort,
			@RequestParam(value = "p", required = false, defaultValue = "0") int p) {

		if (subtype == 0) {
			subtype = type * 10000;
		}

		List<CommonActInfo> lstAct = as.listActivitiesByActCodeAndSchCode(campus, subtype, sort, p);
		List<ActInfoAjax> ajaxLstAct = new ArrayList<ActInfoAjax>();

		for (CommonActInfo commonActInfo : lstAct) {
			ajaxLstAct.add(new ActInfoAjax(commonActInfo));
		}

		return new AjaxResponseBody<List<ActInfoAjax>>(RESPONSE_STATUS.SUCCESS, ajaxLstAct);

	}

	@JsonView(AjaxJsonViews.ActDetail.class)
	@RequestMapping(value = "/GetDetail", method = RequestMethod.GET)
	public AjaxResponseBody<ActInfoAjax> GetDetail(@RequestParam(value = "id", required = true) int id) {

		ActInfoAjax actInfoAjax = null;

		CommonActInfo commonActInfo = as.getActivitiyDetail(id);
		if (commonActInfo != null) {
			boolean isFavoured = us.isFavoured(id);
			actInfoAjax = new ActInfoAjax(commonActInfo, isFavoured);
		}
		/*else
		{
			
		}*/

		return new AjaxResponseBody<ActInfoAjax>(RESPONSE_STATUS.SUCCESS, actInfoAjax);
	}

}
