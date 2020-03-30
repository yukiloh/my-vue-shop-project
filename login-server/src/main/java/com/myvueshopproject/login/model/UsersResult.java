package com.myvueshopproject.login.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UsersResult implements Serializable {

    private Data data;
    private Meta meta;

    public UsersResult(Data data) {
        this.data = data;

        String msg = "获取用户列表成功";
        int status = 200;

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

        private Integer total;
        private Integer pagenum;
        private ArrayList<Users> users;

        public Data(Integer total, Integer pagenum, ArrayList<Users> users) {
            this.total = total;
            this.pagenum = pagenum;
            this.users = users;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getPagenum() {
            return pagenum;
        }

        public void setPagenum(Integer pagenum) {
            this.pagenum = pagenum;
        }

        public ArrayList<Users> getUsers() {
            return users;
        }

        public void setUsers(ArrayList<Users> users) {
            this.users = users;
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

    public static class Users {
        private Integer id;
        private String userName;
        private String mobile;
        private Integer type;
        private String email;
        private Date createTime;
        private Boolean mgState; //用户当前 状态
        private String roleName;

        public Users(Integer id, String userName, String mobile, Integer type, String email, Date createTime, Boolean mgState, String roleName) {
            this.id = id;
            this.userName = userName;
            this.mobile = mobile;
            this.type = type;
            this.email = email;
            this.createTime = createTime;
            this.mgState = mgState;
            this.roleName = roleName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public Boolean getMgState() {
            return mgState;
        }

        public void setMgState(Boolean mgState) {
            this.mgState = mgState;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }
}
