package com.lawcall.web.image;

import com.lawcall.util.WriteFileUtil;
import com.lawcall.web.image.entity.Progress;
import com.lawcall.web.image.mapper.TouxiangMapper;
import com.lawcall.web.image.services.TouxiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TouxController {

	@Autowired
	private TouxiangMapper touxiangMapper;


	@Autowired
	private MappingJacksonJsonView mappingJacksonJsonView;
	
	@Autowired
	private TouxiangService touxiangService;


	/**
	 * 获取上传进度
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "progress")
	public ModelAndView progress(HttpServletRequest request,HttpServletResponse response) {
	//	response.addHeader("Access-Control-Allow-Origin", "*");
		HashMap map = new HashMap();
		HttpSession session = request.getSession();
		System.out.println(session);
		Progress progress = (Progress) request.getSession().getAttribute("upload_status");
		if (progress == null) {
			map.put("result", "error");
		} else {
			map.put("result", progress);
		}
		return new ModelAndView(new MappingJacksonJsonView(),map);
	}


	/**
	 * 头像上传并保存至服务器
	 * 
	 * @param file
	 *            传入图片文件
	 * @param request
	 * @param model
	 *            模型
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("toux/shangc")
	public ModelAndView touxiangshangchuan(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response
			) throws IOException {// ModelMap对象主要用于传递控制方法处理数据到结果页面，也就是说我们把结果页面上需要的数据放到ModelMap对象中即可，他的作用类似于request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据

	//	response.addHeader("Access-Control-Allow-Origin", "*");
		System.out.println("开始");
		// 图片目标绝对路径
		String customerid = request.getParameter("customerid");
		System.out.println(customerid);
		String path = request.getSession().getServletContext().getRealPath("");
		String fileName = null;
		try{
			path = path + "/../resources/touxiang";
			 fileName = file.getOriginalFilename();
		}catch (Exception e){
			e.printStackTrace();
		}
		// 得到上传文件名字

		String xm_M = fileName.substring(0,fileName.lastIndexOf("."));
		String xm=new String(xm_M.getBytes("ISO8859-1"),"UTF-8");
		System.out.println("姓名为： "+ xm);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(fileName != null&&fileName.length()>0){
			int random=(int) (Math.random()*90000+10000);
			// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			// 对扩展名进行小写转换
			ext = ext.toLowerCase();
			String wjm = new Date().getTime()+Integer.toString(random)+ "." + ext;
			String tx_wenjgs=ext;
			System.out.println(fileName);
			System.out.println(path);
			// 利用上传文件名在目标路径新建一个相同文件名文件
			File targetFile = new File(path, wjm);
			if (!targetFile.exists()) {
				// 如果目标文件不存在，则新建一个
				targetFile.mkdirs();
			}
			// 保存
			try {
				// 使用transferTo方法进行文件的复制，传入需要放入的路径下文件
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//上传文件信息
			if (targetFile.exists() && targetFile.isFile()){ 
				int tx_wenjdx=(int) targetFile.length();//头像大小
				BufferedImage sourceImg =ImageIO.read(new FileInputStream(targetFile)); //图片缓冲流
				int tx_changd=sourceImg.getHeight();//长度
				int tx_kuand=sourceImg.getWidth();//宽度
				Timestamp tx_shangcsj= new Timestamp(System.currentTimeMillis());//发送时间
				int fanhzt = touxiangMapper.touXiangShangChuang(wjm,
						tx_shangcsj,
						tx_wenjdx,
						tx_kuand,
						tx_changd,
						tx_wenjgs);
				System.out.println("头像储存结果" + fanhzt);
			}else{
				System.out.println("文件不存在，或目标不是一个文件类型");
			}
			int touxbh=0;
			try{
				touxbh = touxiangMapper.chaxunbh(wjm);
				touxiangMapper.updateUserInfo(touxbh,customerid);
			}catch (Exception e){

				e.printStackTrace();
			}
			map.put("touxbh", touxbh);
			map.put("result", "OK");
			map.put("message", "上传完成");
//			if(xm != null && touxbh != 0){
//				map.put("xm,touxbh", xm+":"+touxbh);
//				System.out.println("存储的头像编号为："+ touxbh);
//				URL url;
//				try {
//					url = new URL("http://127.0.0.1:8082/YoujiayiWeb/toux/shangc.do?xm="+xm+"&flag="+touxbh);
//					URLConnection URLconnection = url.openConnection();
//					HttpURLConnection httpConnection = (HttpURLConnection)URLconnection;
//					int responseCode = httpConnection.getResponseCode();
//					System.out.println("http responseCode:"+responseCode);
//					map.put("responseCode", responseCode);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					map.put("error", "url本地请求异常");
//					return new ModelAndView(mappingJacksonJsonView,map);
//				}
//			}else{
//				map.put("error", "姓名或头像编号为空，请重新检查上传");
//				return new ModelAndView(mappingJacksonJsonView,map);
//			}
		}else{
			map.put("error", "你选择的头像为空，请选择头像");
		}
		return new ModelAndView(mappingJacksonJsonView,map);
		

	}

	
	/**
	 * 根据头像编号，获取头像
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("touxiang")
	public void touxiang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String touxbh = request.getParameter("touxbh");
		if(touxbh == null || touxbh.length() == 0){
			return;
		}
		String touxianglujing = touxiangService.chaxTouxiang(touxbh);
		String path = request.getSession().getServletContext().getRealPath("/")+touxianglujing;
		File wj = new File(path);
		if(!wj.exists()){
			System.out.println("请求的资源不存在");
			return;
		}
		WriteFileUtil.writeFile(touxianglujing, request, response);
	}
	
	/**
	 * 头像删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("toux/shanc")
	public  ModelAndView	touxiangshanchu(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String touxbhStr = request.getParameter("touxbh");
		Map<String, Object> map = new HashMap<String, Object>();
		if(touxbhStr != null && touxbhStr.length()>0){
		int touxiangbh = Integer.parseInt(touxbhStr);
		String wjm = touxiangMapper.get(touxiangbh);
		if (wjm != null ) {
			touxiangMapper.shanChuTouXiang(touxiangbh);//
			// 头像目标绝对路径
			String path = request.getSession().getServletContext().getRealPath("")+"/../resources/touxiang/" +wjm;
			File file = new File(path);
			if (file.exists()) {
			    file.delete();
			}
			System.out.println("头像删除成功");

			map.put("success", 1);
		} else {
			System.out.println("图片不存在，输入不能为空");
			map.put("success", 0);
			
		}
		
		}
		else{
			map.put("success", 0);
		}
		return new ModelAndView(mappingJacksonJsonView, map);
	}

}
