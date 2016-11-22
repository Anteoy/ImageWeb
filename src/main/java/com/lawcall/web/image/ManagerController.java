package com.lawcall.web.image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.lawcall.web.image.entity.TouXiang;
import com.lawcall.web.image.entity.TuPian;
import com.lawcall.web.image.mapper.TouxiangMapper;
import com.lawcall.web.image.mapper.TupianMapper;

@Controller
public class ManagerController {
	
	@Autowired
	private TouxiangMapper touxiangMapper;
	
	@Autowired
	private TupianMapper tupianMapper;
	
	@Autowired
	private MappingJacksonJsonView mappingJacksonJsonView;

	/**
	 * 服务器全部头像信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("touxiang/getAll")
	public ModelAndView getTx(HttpServletRequest request, HttpServletResponse response){
		
		List<TouXiang> touxiangs = touxiangMapper.chaxsy();//头像数组
		Map<String,Object> map = new HashMap<String, Object>();
		if (touxiangs != null) {
			map.put("total", touxiangs.size());
			map.put("rows", touxiangs);
		} else {
			map.put("total", 0);
			map.put("rows", null);
		}
		return new ModelAndView(mappingJacksonJsonView, map);
	}
	
	/**
	 * 通过ID查询头像具体信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("touxiang/getTxjt")
	public ModelAndView getTxjt(HttpServletRequest request, HttpServletResponse response){
		
		Map<String,Object> map = new HashMap<String, Object>();
		String bh = request.getParameter("bh");
		if(bh == null || bh == ""){
			map.put("error", "您输入的编号为空，请重新输入");
			map.put("rows", null);
			return new ModelAndView(mappingJacksonJsonView, map);
		}
		int touxbh = Integer.parseInt(bh);
		List<TouXiang> touxiangs = touxiangMapper.getTxjt(touxbh);//头像数组
		
		if (touxiangs != null) {
			map.put("total", touxiangs.size());
			map.put("rows", touxiangs);
		} else {
			map.put("total", 0);
			map.put("rows", null);
		}
		return new ModelAndView(mappingJacksonJsonView, map);
	}
	
	/**
	 * 服务器全部图片信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tupian/getAll")
	public ModelAndView getTp(HttpServletRequest request, HttpServletResponse response){
		
		List<TuPian> tupians = tupianMapper.chaxsy();//图片数组
		Map<String,Object> map = new HashMap<String, Object>();
		if (tupians != null) {
			map.put("total", tupians.size());
			map.put("rows", tupians);
		} else {
			map.put("total", 0);
			map.put("rows", null);
		}
		return new ModelAndView(mappingJacksonJsonView, map);
	}
	
	/**
	 * 通过ID查询图片具体信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("touxiang/getTpjt")
	public ModelAndView getTpjt(HttpServletRequest request, HttpServletResponse response){
		String bh = request.getParameter("bh");
		Map<String,Object> map = new HashMap<String, Object>();
		if(bh == null || bh == ""){
			map.put("error", "您输入的编号为空，请重新输入");
			map.put("rows", null);
			return new ModelAndView(mappingJacksonJsonView, map);
		}
			
		int tupbh = Integer.parseInt(bh);
		List<TuPian> touxiangs = tupianMapper.getTpjt(tupbh);//头像数组
		
		if (touxiangs != null) {
			map.put("total", touxiangs.size());
			map.put("rows", touxiangs);
		} else {
			map.put("total", 0);
			map.put("rows", null);
		}
		return new ModelAndView(mappingJacksonJsonView, map);
	}
	
}
