package com.myvueshopproject.login.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PermResult implements Serializable {

    private Data data;
    private Meta meta;

    public PermResult(Data data) {
        this.data = data;

        String msg = "获取权限列表成功";
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
        private ArrayList<Perm> perms;

        public Data(ArrayList<Perm> perms) {
            this.perms = perms;
        }

        public ArrayList<Perm> getPerms() {
            return perms;
        }

        public void setPerms(ArrayList<Perm> perms) {
            this.perms = perms;
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

    public static class Perm {
        private Integer id;
        private String authName;
        private String level;
        private Integer pid;
        private String path;

        public Perm(Integer id, String authName, String level, Integer pid, String path) {
            this.id = id;
            this.authName = authName;
            this.level = level;
            this.pid = pid;
            this.path = path;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }


}
