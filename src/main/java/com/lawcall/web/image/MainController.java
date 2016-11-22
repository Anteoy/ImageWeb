package com.lawcall.web.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lawcall.web.image.services.TouxiangService;
import com.lawcall.web.image.services.TupianService;
import com.lawcall.web.image.services.YuyinService;

@Controller
public class MainController {

	@Autowired
	private TouxiangService touxiangService;

	@Autowired
	private TupianService tupianService;
	
	@Autowired
	private YuyinService yuyinService;

	

	

	

}