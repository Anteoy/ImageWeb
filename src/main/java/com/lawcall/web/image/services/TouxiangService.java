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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawcall.web.image.mapper.TouxiangMapper;

/**
 * @author 胡正卫
 */
@Service
public class TouxiangService {

	private static String IMAGE_PATH = "/../resources/touxiang/";

	@Autowired
	private TouxiangMapper touxiangMapper;

	/**
	 * 查询头像
	 * 根据头像编号查询头像文件
	 * @param criteria
	 * @return
	 */
	public String chaxTouxiang(String touxbh) {
		String wj = "no.png";
		if (touxbh != null && touxbh.length() > 0) {
			try {
				int _txbh = Integer.parseInt(touxbh);
				if (_txbh > 0) {
					String  _wj = touxiangMapper.get(_txbh);
					if (_wj != null && _wj.length() > 0) wj = _wj;//wj文件
				}
			} catch (Exception e) {
				e.printStackTrace();
				wj = "broken.png";
			}
		}
		return IMAGE_PATH + wj;
	}
	
	

}
