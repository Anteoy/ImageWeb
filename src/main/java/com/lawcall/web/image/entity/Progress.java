package com.lawcall.web.image.entity;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by YangHuanglin on 2016/6/15.
 */
public class Progress {
    private long fileReaded;//单位为字节
    private long fileLength;//单位为字节
    private int fileIndex;
    private String percent;

    public long getFileReaded() {
        return fileReaded;
    }

    public void setFileReaded(long fileReaded) {
        this.fileReaded = fileReaded;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public String getPercent() {
        BigDecimal a = new BigDecimal(fileReaded);
        BigDecimal b = new BigDecimal(fileLength);
        if (b.equals(BigDecimal.ZERO))
            return "0%";
        BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_DOWN);
        percent = NumberFormat.getPercentInstance().format(c);
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
