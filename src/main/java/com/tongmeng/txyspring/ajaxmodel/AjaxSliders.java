package com.tongmeng.txyspring.ajaxmodel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.Sliders;


public class AjaxSliders extends AjaxResponseBody {

	@JsonView(AjaxJsonViews.Public.class)
	List<Sliders> result;
		
	public AjaxSliders(int status,List<Sliders> result) {
		super(status);
		setResult(result);
	}

	public List<Sliders> getResult() {
		return result;
	}

	public void setResult(List<Sliders> result) {
		this.result = result;
	}
}
