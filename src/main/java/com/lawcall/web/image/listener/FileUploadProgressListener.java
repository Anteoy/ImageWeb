package com.lawcall.web.image.listener;

import com.lawcall.web.image.entity.Progress;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;


/**
 * Created by YangHuanglin on 2016/6/15.
 */
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;

    public FileUploadProgressListener(HttpSession session) {
        this.session = session;
        Progress status = new Progress();
        session.setAttribute("upload_status", status);
    }

    /**
     * readedLength 到目前为止读取文件的比特数 fileLength 文件总大小 index 目前正在读取第几个文件
     */
    public void update(long readedLength, long fileLength, int index) {
        Progress status = (Progress) session.getAttribute("upload_status");
        status.setFileReaded(readedLength);
        status.setFileLength(fileLength);
        status.setFileIndex(index);
        session.setAttribute("upload_status", status);
    }
}
