package com.myvueshopproject.login.result;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuResult implements Serializable {

    private ArrayList<Data> data;
    private Meta meta;

    public MenuResult(ArrayList<Data> children) {
        this.data = children;

        String msg = "获取菜单列表成功";
        int status = 200;

        this.meta = new Meta(msg,status);
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
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
        private String authName;
        private String path;
        private ArrayList<Data> children;

        public Data(Integer id, String authName, String path, ArrayList<Data> children) {
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

        public ArrayList<Data> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Data> children) {
            this.children = children;
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
