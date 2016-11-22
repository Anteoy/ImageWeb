package com.lawcall.web.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.lawcall.util.WriteFileUtil;
import com.lawcall.web.image.mapper.TupianMapper;
import com.lawcall.web.image.services.TupianService;

@Controller
public class TupController {


	@Autowired
	private TupianMapper tupianMapper;
	
	@Autowired
	private TupianService tupianService;

	@Autowired
	private MappingJacksonJsonView mappingJacksonJsonView;
	

	
	/**
	 * 图片上传服务器并保存文件名至数据库
	 * 
	 * @param file
	 *            上传文件
	 * @param request
	 * @param model
	 *            模型
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("tup/shangc")
	public ModelAndView tupianshangchuan(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws FileNotFoundException, IOException {// ModelMap对象主要用于传递控制方法处理数据到结果页面，(多余暂时已经移除)也就是说我们把结果页面上需要的数据放到ModelMap对象中即可，他的作用类似于request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据
		int random=(int) (Math.random()*90000+10000);
		String flag = request.getParameter("flag");//0身份证正面，１身份证背面
		String lawyerid = request.getParameter("lawyerid");
		System.out.println(lawyerid);
		System.out.println("开始");
		Map<String, Object> map = new HashMap<String, Object>();
		request.setCharacterEncoding("utf-8");
		// 得到上传文件名字
		String fileName = file.getOriginalFilename();
		if(fileName == null || fileName.length() == 0){
			map.put("error", "你选择的图片为空，请选择图片");
			return new ModelAndView(mappingJacksonJsonView,map);
		}
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 对扩展名进行小写转换
		ext = ext.toLowerCase();
		// 图片目标绝对路径
		String path = request.getSession().getServletContext().getRealPath("");
		path = path + "/../resources/tupian";
		String wjm = System.nanoTime() +Integer.toString(random)+ "." + ext;
		String tx_wenjgs=ext;
		System.out.println(path);
		// 利用上传文件名在目标路径新建一个相同文件名文件
		File targetFile = new File(path, wjm);
		if (!targetFile.exists()) {
			// 如果目标文件夹不存在，则新建一个
			targetFile.mkdirs();
		}
		// 保存
		try {
			// 使用transferTo方法进行文件的复制，传入需要放入的路径下文件
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* 把服务器里图片索引存放在数据库里 */
		int tp_wenjdx=(int) targetFile.length();//头像大小
		BufferedImage sourceImg =ImageIO.read(new FileInputStream(targetFile)); //图片缓冲流
		int tp_changd=sourceImg.getHeight();//长度
		int tp_kuand=sourceImg.getWidth();//宽度
		Timestamp tp_shangcsj= new Timestamp(System.currentTimeMillis());//发送时间
		int fanhzt =0;
		int tupbh = 0;
		try{

			 fanhzt = tupianMapper.tuPianShangChuang(wjm,
					tp_shangcsj,
					tp_wenjdx,
					tp_kuand,
					tp_changd,
					tx_wenjgs,
					 flag);// 返回状态
			 tupbh = tupianMapper.chaxunbh(wjm);
			if (flag.equals("0")) {
				tupianMapper.updatelawyerInfo(tupbh, lawyerid);
			}
			if(flag.equals("1")){
				tupianMapper.updatelawyerInfoBm(tupbh, lawyerid);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("图片储存结果:success" + fanhzt);
		map.put("tupbh", tupbh);
		map.put("result", "OK");
		map.put("message", "上传完成");
		
		return new ModelAndView(mappingJacksonJsonView,map);
	}
	
	
	/**
	 * 根据图片编号，获取图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tupian")
	public void tupian(HttpServletRequest request, HttpServletResponse response) {
		String tupbh = request.getParameter("tupbh");
		if(tupbh == null || tupbh.length() == 0){
			return;
		}
		String tupianlujing = tupianService.chaxTupian(tupbh);
		String path = request.getSession().getServletContext().getRealPath("/")+tupianlujing;
		File tupian = new File(path);
		if(!tupian.exists()){
			System.out.println("请求的资源不存在");
			return;
		}
		WriteFileUtil.writeFile(tupianlujing, request, response);
	}
	
	/**
	 * 图片删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("tup/shanc")
	public ModelAndView tupianshanchu(HttpServletRequest request, HttpServletResponse response) {
		String tupbh = request.getParameter("tupbh");
		Map<String, Object> map = new HashMap<String, Object>();
		if(tupbh != null && tupbh.length()>0){
			int tupianbh = Integer.parseInt(tupbh);
			String wjm = tupianMapper.get(tupianbh);
			if (wjm != null && wjm.length()>0) {
				tupianMapper.shanChuTuPian(tupianbh);//
				// 图片目标绝对路径
				String path = request.getSession().getServletContext().getRealPath("")+"/../recourses/tupian/"+wjm;
				File file = new File(path);
				// 删除源图片文件 
				if (file.exists()) {
				    file.delete();
				}
				System.out.println("头像删除成功");
				map.put("success", 1);
			} else {
				map.put("success", 0);
			}
		}
		else{
			map.put("success", 0);
		}
		return new ModelAndView(mappingJacksonJsonView,map);
	}

}
