package com.lawcall.web.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.lawcall.web.image.mapper.TouxiangMapper;
import com.lawcall.web.image.mapper.TupianMapper;

import sun.misc.BASE64Decoder;

@Controller
public class JoinController {

	@Autowired
	private TouxiangMapper touxiangMapper;

	@Autowired
	private TupianMapper tupianMapper;

	@Autowired
	private MappingJacksonJsonView mappingJacksonJsonView;
	
	
	/**
	 * base64 文件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("tupsc")
	public ModelAndView tupsc(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		String imgStr = request.getParameter("base64");
		imgStr = imgStr.replaceAll(" ", "+");
//		FileUtils.writeStringToFile(new File("e:\\put2.txt"), imgStr);
		String path = request.getParameter("path");
		int random=(int) (Math.random()*90000+10000);
		System.out.println("开始");
		Map<String, Object> map = new HashMap<String, Object>();
		// 图片目标绝对路径
		System.out.println("获得上传过来的完全全路径为 ："+ path);
		String savepath = request.getSession().getServletContext().getRealPath("/tupian/base64Img");
		System.out.println("储存目录为 ："+ savepath);
		if(path == null || path.length() == 0){
			map.put("error", "你选择的图片为空，请选择图片");
			return new ModelAndView(mappingJacksonJsonView,map);
		}
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = path.substring(path.lastIndexOf(".") + 1, path.length());
		//解析文件后缀
		// 对扩展名进行小写转换
		ext = ext.toLowerCase();
		// 得到上传文件名字
		String tx_wenjgs=ext;
		//得到储存文件名
		String wjm = System.nanoTime() +Integer.toString(random)+ "." + ext;
		System.out.println(path);
		// 利用上传文件名在目标路径新建一个相同文件名文件
		File targetFile = new File(savepath, wjm);
		if (!targetFile.exists()) {
			// 如果目标文件不存在，则新建一个
			targetFile.createNewFile();
		}
		// 保存
		try {
			//图像数据为空
			if (imgStr == null){
				map.put("success", 0);
				return new ModelAndView(mappingJacksonJsonView,map);
			} 
	            
	        BASE64Decoder decoder = new BASE64Decoder();
	      //  byte[] imgStrbyte = imgStr.getBytes();
			//Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
//            for(int i=0;i<b.length;++i)
//            {
//                if(b[i]<0)
//                {//调整异常数据
//                    b[i]+=256;
//                }
//            }
			// 使用base64解码进行写入
			OutputStream os = new FileOutputStream(targetFile);
			os.write(b);
			//关闭流
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* 把服务器里图片索引存放在数据库里 */
		int tp_wenjdx=(int) targetFile.length();//头像大小
		BufferedImage sourceImg =ImageIO.read(new FileInputStream(targetFile)); //图片缓冲流
		int tp_changd=sourceImg.getHeight();//长度
		int tp_kuand=sourceImg.getWidth();//宽度
		Timestamp tp_shangcsj= new Timestamp(System.currentTimeMillis());//发送时间
		int fanhzt = tupianMapper.tuPianShangChuang(wjm,
				tp_shangcsj,
				tp_wenjdx,
				tp_kuand,
				tp_changd,
				tx_wenjgs,"");// 返回状态
		int tupbh = tupianMapper.chaxunbh(wjm);
		System.out.println("图片储存结果:success" + fanhzt);
		map.put("tupbh:", tupbh);
		return new ModelAndView(mappingJacksonJsonView,map);
	}

}
