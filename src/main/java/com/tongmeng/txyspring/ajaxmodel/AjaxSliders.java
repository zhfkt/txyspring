package com.tongmeng.txyspring.ajaxmodel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.Sliders;


public class AjaxSliders extends AjaxResponseBody {

	@JsonView(AjaxJsonViews.Public.class)
	ArrayList<Sliders> result;
		
	public AjaxSliders(int status,ArrayList<Sliders> result) {
		super(status);
		setResult(result);
	}

	public ArrayList<Sliders> getResult() {
		return result;
	}

	public void setResult(ArrayList<Sliders> result) {
		this.result = result;
	}
}
