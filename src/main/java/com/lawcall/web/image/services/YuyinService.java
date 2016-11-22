/**
 * @filename YishengService.java
 * @directory /src/com/lawcall/web/info/services
 * @package com.lawcall.web.info.service
 * @description 医生实体及业务
 * @author 胡正卫
 * @date 2015-06-23 15:46:05
 * @version v0.1
 */
package com.lawcall.web.image.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawcall.web.image.mapper.YuyMapper;

/**
 * 
 * @author 周嚴
 *
 */
@Service
public class YuyinService {

	private static String YUYIN_PATH = "/../resources/yuyin/";

	@Autowired
	private YuyMapper yuyMapper;

	/**
	 * 查询语音
	 * 根据语音编号查询头像文件
	 * @param criteria
	 * @return
	 */
	public String chaxYuyin(String yybh,HttpServletRequest request) {
		String wj = "no.png";
		if (yybh != null && yybh.length() > 0) {
			try {
				int _yybh = Integer.parseInt(yybh);
				if (_yybh > 0) {
					String  _wj = yuyMapper.get_yy(_yybh);
					if (_wj != null && _wj.length() > 0) wj = _wj;
				}
			} catch (Exception e) {
				e.printStackTrace();
				wj = "borken.png";
			}
		}
		System.out.println(YUYIN_PATH + wj);
		String url = YUYIN_PATH + wj;
		return url;
	}
	
}
