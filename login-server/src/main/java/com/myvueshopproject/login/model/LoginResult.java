package com.myvueshopproject.login.model;

import java.io.Serializable;

public class LoginResult implements Serializable {

    private Data data;
    private Meta meta;

    public LoginResult(Integer id, Integer rid, String username, String mobile, String email, String token, Boolean isOk) {
        this.data = new Data(id,rid,username,mobile,email,token);

        String msg;
        int status;

        if (isOk) {
            msg = "登陆成功";
            status = 200;
        } else {
            msg = "登陆失败";
            status = 401;
        }
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

    private class Data {
        private Integer id;
        private Integer rid;
        private String username;
        private String mobile;
        private String email;
        private String token;

        public Data(Integer id, Integer rid, String username, String mobile, String email, String token) {
            this.id = id;
            this.rid = rid;
            this.username = username;
            this.mobile = mobile;
            this.email = email;
            this.token = token;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRid() {
            return rid;
        }

        public void setRid(Integer rid) {
            this.rid = rid;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    private class Meta {
        private String msg;
        private Integer status;

        public Meta(String msg, Integer status) {
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
