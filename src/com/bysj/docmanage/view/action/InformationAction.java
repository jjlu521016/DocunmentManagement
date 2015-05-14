package com.bysj.docmanage.view.action;

import com.opensymphony.xwork2.ActionSupport;

public class InformationAction extends ActionSupport {

    private String[] uploadFileName;

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("�����Ǳ��淢�����ݺ͸������ӵĹ�̣�");
        System.out.println(uploadFileName[0] + "||" + uploadFileName[1]);
        return "success";
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

}