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

import com.lawcall.web.image.mapper.TupianMapper;

/**
 * @author 胡正卫
 */
@Service
public class TupianService {

	private static String TUPIAN_PATH = "/../resources/tupian/";

	@Autowired
	private TupianMapper tupianMapper;

	/**
	 * 查询图片
	 * 根据图片编号查询头像文件
	 * @param criteria
	 * @return
	 */
	public String chaxTupian(String tpbh) {
		String wj = "no.png";
		if (tpbh != null && tpbh.length() > 0) {
			try {
				int _tpbh = Integer.parseInt(tpbh);
				if (_tpbh > 0) {
					String  _wj = tupianMapper.get(_tpbh);
					if (_wj != null && _wj.length() > 0) wj = _wj;
				}
			} catch (Exception e) {
				e.printStackTrace();
				wj = "borken.png";
			}
		}
		System.out.println(TUPIAN_PATH + wj);
		return TUPIAN_PATH + wj;
	}
	
}
