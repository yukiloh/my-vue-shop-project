package com.myvueshopproject.login.result;

import java.io.Serializable;
import java.util.ArrayList;

public class RolesResult implements Serializable {

    private ArrayList<FirstPerm> data;
    private Meta meta;

    public RolesResult(ArrayList<FirstPerm> data) {

        this.data = data;

        String msg = "获取角色列表成功";
        int status = 200;

        this.meta = new Meta(msg,status);
    }

    public ArrayList<FirstPerm> getData() {
        return data;
    }

    public void setData(ArrayList<FirstPerm> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
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




    public static class FirstPerm {
        private Integer id;
        private String authName;
        private String path;
        private String desc;
        private ArrayList<SecondPerm> children;

        public FirstPerm(Integer id, String authName, String path, String desc,ArrayList<SecondPerm> children) {
            this.id = id;
            this.authName = authName;
            this.path = path;
            this.desc = desc;
            this.children = children;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public ArrayList<SecondPerm> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<SecondPerm> children) {
            this.children = children;
        }
    }




    public static class SecondPerm {
        private Integer id;
        private String authName;
        private String path;
        private ArrayList<ThirdPerm> children;

        public SecondPerm(Integer id, String authName, String path, ArrayList<ThirdPerm> children) {
            this.id = id;
            this.authName = authName;
            this.path = path;
            this.children = children;
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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public ArrayList<ThirdPerm> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ThirdPerm> children) {
            this.children = children;
        }
    }

    public static class ThirdPerm {
        private Integer id;
        private String authName;
        private String path;

        public ThirdPerm(Integer id, String authName, String path) {
            this.id = id;
            this.authName = authName;
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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
