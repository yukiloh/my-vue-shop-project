package com.myvueshopproject.login.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class CategoriesResult implements Serializable {

    private Data data;
    private Meta meta;

    public CategoriesResult(Data data) {
        this.data = data;

        String msg = "获取商品分类列表成功";
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

        private ArrayList<Categories0> children;

        public Data(ArrayList<Categories0> children) {
            this.children = children;
        }

        public ArrayList<Categories0> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Categories0> children) {
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

    public static class Categories0 {

        private Integer cate_id;
        private String cate_name;
        private Integer cate_pid;   //分类的父ID
        private Integer cate_level;
        private Boolean cate_deleted;
        private ArrayList<Categories1> children;

        public Categories0(Integer cate_id, String cate_name, Integer cate_pid, Integer cate_level, Boolean cate_deleted, ArrayList<Categories1> children) {
            this.cate_id = cate_id;
            this.cate_name = cate_name;
            this.cate_pid = cate_pid;
            this.cate_level = cate_level;
            this.cate_deleted = cate_deleted;
            this.children = children;
        }

        public Integer getCate_id() {
            return cate_id;
        }

        public void setCate_id(Integer cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public Integer getCate_pid() {
            return cate_pid;
        }

        public void setCate_pid(Integer cate_pid) {
            this.cate_pid = cate_pid;
        }

        public Integer getCate_level() {
            return cate_level;
        }

        public void setCate_level(Integer cate_level) {
            this.cate_level = cate_level;
        }

        public Boolean getCate_deleted() {
            return cate_deleted;
        }

        public void setCate_deleted(Boolean cate_deleted) {
            this.cate_deleted = cate_deleted;
        }

        public ArrayList<Categories1> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Categories1> children) {
            this.children = children;
        }
    }

    public static class Categories1 {

        private Integer cate_id;
        private String cate_name;
        private Integer cate_pid;   //分类的父ID
        private Integer cate_level;
        private Boolean cate_deleted;
        private ArrayList<Categories2> children;

        public Categories1(Integer cate_id, String cate_name, Integer cate_pid, Integer cate_level, Boolean cate_deleted, ArrayList<Categories2> children) {
            this.cate_id = cate_id;
            this.cate_name = cate_name;
            this.cate_pid = cate_pid;
            this.cate_level = cate_level;
            this.cate_deleted = cate_deleted;
            this.children = children;
        }

        public Integer getCate_id() {
            return cate_id;
        }

        public void setCate_id(Integer cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public Integer getCate_pid() {
            return cate_pid;
        }

        public void setCate_pid(Integer cate_pid) {
            this.cate_pid = cate_pid;
        }

        public Integer getCate_level() {
            return cate_level;
        }

        public void setCate_level(Integer cate_level) {
            this.cate_level = cate_level;
        }

        public Boolean getCate_deleted() {
            return cate_deleted;
        }

        public void setCate_deleted(Boolean cate_deleted) {
            this.cate_deleted = cate_deleted;
        }

        public ArrayList<Categories2> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Categories2> children) {
            this.children = children;
        }
    }

    public static class Categories2 {

        private Integer cate_id;
        private String cate_name;
        private Integer cate_pid;   //分类的父ID
        private Integer cate_level;
        private Boolean cate_deleted;

        public Categories2(Integer cate_id, String cate_name, Integer cate_pid, Integer cate_level, Boolean cate_deleted) {
            this.cate_id = cate_id;
            this.cate_name = cate_name;
            this.cate_pid = cate_pid;
            this.cate_level = cate_level;
            this.cate_deleted = cate_deleted;
        }

        public Integer getCate_id() {
            return cate_id;
        }

        public void setCate_id(Integer cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public Integer getCate_pid() {
            return cate_pid;
        }

        public void setCate_pid(Integer cate_pid) {
            this.cate_pid = cate_pid;
        }

        public Integer getCate_level() {
            return cate_level;
        }

        public void setCate_level(Integer cate_level) {
            this.cate_level = cate_level;
        }

        public Boolean getCate_deleted() {
            return cate_deleted;
        }

        public void setCate_deleted(Boolean cate_deleted) {
            this.cate_deleted = cate_deleted;
        }
    }
}
