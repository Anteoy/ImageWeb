/**
 * @filename YishengMapper.java
 * @directory /src/com/lawcall/web/info/mapper
 * @package com.lawcall.web.info.mapper
 * @description 医生表实体
 * @author 胡世君
 * @date 2015-06-23 15:21:37
 * @version v0.1
 */
package com.lawcall.web.image.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawcall.web.image.entity.TuPian;

/**
 * @author 胡正卫
 */
public interface TupianMapper {

	/**
	 * 根据编号获取头像路径
	 * @param criteria
	 * @return
	 */
	public String get(int txbh);
	

	/**
	 * 插入图片
	 * @param touxianglujing
	 * @return
	 */
	public int tuPianShangChuang(@Param("wjm") String wjm,
								 @Param("tp_shangcsj") Timestamp px_shangcsj,
								 @Param("tp_wenjdx") int tp_wenjdx,
								 @Param("tp_kuand") int tp_kuand,
								 @Param("tp_changd") int tp_changd,
								 @Param("tp_wenjgs") String tp_wenjgs,
								 @Param("flag") String flag);
	
	/**
	 * 删除图片
	 * @param touxiangkey
	 * @return
	 */
	public void shanChuTuPian(@Param("tupbh") int tupbh);
	
	/**
	 * 查询图片编号
	 * @param tupiankey
	 * @return
	 */
	public int chaxunbh(@Param("wjm")String wjm);


	public List<TuPian> chaxsy();


	public List<TuPian> getTpjt(@Param("tupbh") int tupbh);


	public void updatelawyerInfo(@Param("tupbh")int tupbh, @Param("lawyerid")String lawyerid);

	public void updatelawyerInfoBm(@Param("tupbh")int tupbh, @Param("lawyerid")String lawyerid);
}
