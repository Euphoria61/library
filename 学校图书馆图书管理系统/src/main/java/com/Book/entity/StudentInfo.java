package com.Book.entity;

import java.sql.Date;

public class StudentInfo {
    private String stu_id;
    private String pwd;
    private String stu_name;
    private String sex;//性别
    private int nation_id;
    private String nation_name;//名族
    private Date birth;//出生日期
    private String home;//籍贯
    private Date rx_date;//入学日期
    private int xy_id;
    private String xy_name;//学院
    private String post;//职务
    private int pro_id;
    private String pro_name;//专业
    private float cost;//费用

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getNation_id() {
        return nation_id;
    }

    public void setNation_id(int nation_id) {
        this.nation_id = nation_id;
    }

    public String getNation_name() {
        return nation_name;
    }

    public void setNation_name(String nation_name) {
        this.nation_name = nation_name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Date getRx_date() {
        return rx_date;
    }

    public void setRx_date(Date rx_date) {
        this.rx_date = rx_date;
    }

    public int getXy_id() {
        return xy_id;
    }

    public void setXy_id(int xy_id) {
        this.xy_id = xy_id;
    }

    public String getXy_name() {
        return xy_name;
    }

    public void setXy_name(String xy_name) {
        this.xy_name = xy_name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }
}
