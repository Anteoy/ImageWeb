package com.lawcall.web.image.entity;

import java.sql.Timestamp;

public class TuPian {

	//头像编号
		private int bh;
		
		//头像文件名
		private String wjm;
		
		//头像上传时间
		private Timestamp tp_shangcsj;
		
		//头像文件大小
		private int tp_wenjdx; 
		
		//头像尺寸
		private int tp_changd;
		
		private int tp_kuand;
		
		private String tp_wenjgs;

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

		public Timestamp getTp_shangcsj() {
			return tp_shangcsj;
		}

		public void setTp_shangcsj(Timestamp tp_shangcsj) {
			this.tp_shangcsj = tp_shangcsj;
		}

		public int getTp_wenjdx() {
			return tp_wenjdx;
		}

		public void setTp_wenjdx(int tp_wenjdx) {
			this.tp_wenjdx = tp_wenjdx;
		}

		public int getTp_changd() {
			return tp_changd;
		}

		public void setTp_changd(int tp_changd) {
			this.tp_changd = tp_changd;
		}

		public int getTp_kuand() {
			return tp_kuand;
		}

		public void setTp_kuand(int tp_kuand) {
			this.tp_kuand = tp_kuand;
		}

		public String getTp_wenjgs() {
			return tp_wenjgs;
		}

		public void setTp_wenjgs(String tp_wenjgs) {
			this.tp_wenjgs = tp_wenjgs;
		}
		
}
