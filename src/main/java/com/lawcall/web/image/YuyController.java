package com.lawcall.web.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.lawcall.web.image.mapper.YuyMapper;
import com.lawcall.web.image.services.YuyinService;

@Controller
public class YuyController {


	@Autowired
	private YuyMapper yuyMapper;

	@Autowired
	private MappingJacksonJsonView mappingJacksonJsonView;
	
	@Autowired
	private YuyinService yuyinService;
	
	/**
	 * 音频文件上传服务器并保存文件名至数据库
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
	@RequestMapping("yuy/shangc")
	public ModelAndView yuyinshangchuang(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws FileNotFoundException, IOException {// ModelMap对象主要用于传递控制方法处理数据到结果页面，(多余暂时已经移除)也就是说我们把结果页面上需要的数据放到ModelMap对象中即可，他的作用类似于request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据
		int random=(int) (Math.random()*90000+10000);
		System.out.println("开始");
		Map<String, Object> map = new HashMap<String, Object>();
		request.setCharacterEncoding("utf-8");
		// 得到上传文件名字
		String fileName = file.getOriginalFilename();
		if(fileName == null || fileName.length() == 0){
			map.put("error", "你选择的音频文件为空，请重新上传");
			return new ModelAndView(mappingJacksonJsonView,map);
		}
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		// 对扩展名进行小写转换
		ext = ext.toLowerCase();

		// 音频目标绝对路径
		String path_temp = request.getSession().getServletContext().getRealPath("/");
		String path = path_temp + "/../resources/yuyin";
		String wjm = System.nanoTime() +Integer.toString(random)+ "." + ext;
		String yy_wenjgs=ext;//语音文件格式
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
		/* 把服务器里语音索引存放在数据库里 */
		long yy_wenjdx= targetFile.length();//语音大小
		Timestamp yy_shangcsj= new Timestamp(System.currentTimeMillis());//发送时间
		try{

			int fanhzt = yuyMapper.yyShangChuang(wjm,
					yy_shangcsj,
					yy_wenjdx,
					yy_wenjgs);// 返回状态
			int yybh = yuyMapper.chaxunbh_yy(wjm);
			System.out.println("语音储存结果:success" + fanhzt);
			map.put("yybh", yybh);
		}catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView(mappingJacksonJsonView,map);
	}
	
	
	

	
	/**
	 * 根据语音编号，获取语音
	 * @param request
	 * @param response
	 * @return 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("yuyin")
	public void yuyin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String yybh = request.getParameter("yybh");
		if(yybh == null || yybh.length() == 0){
			return;
		}
		String yybhlj = yuyinService.chaxYuyin(yybh, request);
		String path = request.getSession().getServletContext().getRealPath("/")+yybhlj;
		File yy = new File(path);
		if(!yy.exists()){
			System.out.println("请求的资源不存在");
			return;
		}
		response.sendRedirect(yybhlj);
		/*return "redirect:"+yybhlj;*/
	}
}
