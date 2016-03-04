package com.tongmeng.txyspring.ajaxmodel;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.tongmeng.txyspring.model.Slider;


public class AjaxSliders extends AjaxResponseBody {

	@JsonView(AjaxJsonViews.Public.class)
	ArrayList<Slider> result;
		
	public AjaxSliders(int status,ArrayList<Slider> result) {
		super(status);
		setResult(result);
	}

	public ArrayList<Slider> getResult() {
		return result;
	}

	public void setResult(ArrayList<Slider> result) {
		this.result = result;
	}
}
