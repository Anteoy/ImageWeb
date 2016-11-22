package com.lawcall.web.image.entity;

import java.sql.Timestamp;

public class TouXiang {
	//头像编号
	private int bh;
	
	//头像文件名
	private String wjm;
	
	//头像上传时间
	private Timestamp tx_shangcsj;
	
	//头像文件大小
	private int tx_wenjdx; 
	
	public int getBh() {
		return bh;
	}

	public void setBh(int bh) {
		this.bh = bh;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public Timestamp getTx_shangcsj() {
		return tx_shangcsj;
	}

	public void setTx_shangcsj(Timestamp tx_shangcsj) {
		this.tx_shangcsj = tx_shangcsj;
	}

	public int getTx_wenjdx() {
		return tx_wenjdx;
	}

	public void setTx_wenjdx(int tx_wenjdx) {
		this.tx_wenjdx = tx_wenjdx;
	}

	public int getTx_changd() {
		return tx_changd;
	}

	public void setTx_changd(int tx_changd) {
		this.tx_changd = tx_changd;
	}

	public int getTx_kuand() {
		return tx_kuand;
	}

	public void setTx_kuand(int tx_kuand) {
		this.tx_kuand = tx_kuand;
	}

	public String getTx_wenjgs() {
		return tx_wenjgs;
	}

	public void setTx_wenjgs(String tx_wenjgs) {
		this.tx_wenjgs = tx_wenjgs;
	}

	//头像尺寸
	private int tx_changd;
	
	private int tx_kuand;
	
	private String tx_wenjgs;
	
	
}
