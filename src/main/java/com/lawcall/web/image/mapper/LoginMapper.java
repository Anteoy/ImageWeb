package com.lawcall.web.image.mapper;

import org.apache.ibatis.annotations.Param;

import com.lawcall.web.image.entity.UserInfo;

public interface LoginMapper {

	public UserInfo hqyh(@Param("yonghm") String yonghm, @Param("mim") String mim);

	public int xgmm(@Param("bh") int bh, @Param("mim") String mim);

	public int xzyh(UserInfo userInfo);

}

