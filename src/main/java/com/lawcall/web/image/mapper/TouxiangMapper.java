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

import com.lawcall.web.image.entity.TouXiang;

/**
 * @author 周严
 */
public interface TouxiangMapper {

	/**
	 * 根据编号获取头像
	 * @param criteria
	 * @return
	 */
	public String get(@Param("txbh") int txbh);
	
	/**
	 * 插入头像
	 * @param tx_touxkd 
	 * @param touxianglujing
	 * @return
	 */
	public int touXiangShangChuang(@Param("wjm") String wjm,
								   @Param("tx_shangcsj") Timestamp tx_shangcsj,
								   @Param("tx_wenjdx") int tx_wenjdx,
								   @Param("tx_kuand") int tx_kuand,
								   @Param("tx_changd") int tx_changd,
								   @Param("tx_wenjgs") String tx_wenjgs);
	
	/**
	 * 依据文件名查询头像编号
	 * @param touxiangkey
	 * @return
	 */
	public int chaxunbh(@Param("wjm")String wjm);
	
	
	/**
	 * 删除头像
	 * @param touxiangbh
	 * @return
	 */
	public void shanChuTouXiang(@Param("touxbh") int touxbh);
	
	/**
	 *以下对应新增managermapper
	 */

	/**
	 * 查询所有头像图片信息
	 */
	public List<TouXiang> chaxsy();

	/*
	 * 头像总数
	 */
	public int count();

	public List<TouXiang> getTxjt(@Param("touxbh") int touxbh);

	/**
	 * 临时头像数据插入
	 * @param flag
	 * @param xm
	 * @return
	 */
	public int update(@Param("flag") int flag, @Param("xm") String xm);


	/**
	 * 更新用户头像信息
	 * @param touxbh
	 * @param customerid
     */
 	public void updateUserInfo(@Param("touxbh") int touxbh, @Param("customerid") String customerid);
}
