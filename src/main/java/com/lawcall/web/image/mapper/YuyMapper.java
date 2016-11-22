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

import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * @author 周严
 */
public interface YuyMapper {


	/**
	 * 语音上传
	 * @param wjm
	 * @param yy_shangcsj
	 * @param yy_wenjdx
	 * @param yy_wenjgs
	 * @return
	 */
	public int yyShangChuang(@Param("wjm") String wjm, @Param("shangcsj") Timestamp yy_shangcsj, @Param("wenjdx") long yy_wenjdx, @Param("wenjgs") String yy_wenjgs);


	public int chaxunbh_yy(@Param("wjm") String wjm);


	public String get_yy(@Param("bh") Integer _yybh);
}
