package com.myvueshopproject.login.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class AddUsersResult implements Serializable {

    private Data data;
    private Meta meta;

    public AddUsersResult(Data data) {
        this.data = data;

        String msg = "添加用户成功";
        int status = 201;

        this.meta = new Meta(msg,status);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Data {

        private Integer id;
        private String username;
        private String mobile;
        private Integer type;
        private String openid;
        private String email;
        private Date createTime;
        private Date modifyTime;
        private Boolean isDelete;
        private Boolean isActive;

        public Data(Integer id, String username, String mobile, Integer type, String openid, String email, Date createTime, Date modifyTime, Boolean isDelete, Boolean isActive) {
            this.id = id;
            this.username = username;
            this.mobile = mobile;
            this.type = type;
            this.openid = openid;
            this.email = email;
            this.createTime = createTime;
            this.modifyTime = modifyTime;
            this.isDelete = isDelete;
            this.isActive = isActive;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Date modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Boolean getDelete() {
            return isDelete;
        }

        public void setDelete(Boolean delete) {
            isDelete = delete;
        }

        public Boolean getActive() {
            return isActive;
        }

        public void setActive(Boolean active) {
            isActive = active;
        }
    }

    private class Meta {
        private String msg;
        private Integer status;

        Meta(String msg, Integer status) {
            this.msg = msg;
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
