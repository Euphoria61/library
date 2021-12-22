package com.Book.entity;

import java.sql.Date;

public class BookInfo {
    private String code;
    private String book_name;
    private int course_id;
    private String course_name;
    private int kp1_id;
    private String kp1_name;
    private int kp2_id;
    private String kp2_name;
    private int shelf_id;
    private String shelf_code;
    private int level_id;
    private String level_name;
    private String stu_name;
    private Date borrow_time;
    private float cost;
    private String pos;
    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public Date getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(Date borrow_time) {
        this.borrow_time = borrow_time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getKp1_id() {
        return kp1_id;
    }

    public void setKp1_id(int kp1_id) {
        this.kp1_id = kp1_id;
    }

    public String getKp1_name() {
        return kp1_name;
    }

    public void setKp1_name(String kp1_name) {
        this.kp1_name = kp1_name;
    }

    public int getKp2_id() {
        return kp2_id;
    }

    public void setKp2_id(int kp2_id) {
        this.kp2_id = kp2_id;
    }

    public String getKp2_name() {
        return kp2_name;
    }

    public void setKp2_name(String kp2_name) {
        this.kp2_name = kp2_name;
    }

    public int getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(int shelf_id) {
        this.shelf_id = shelf_id;
    }

    public String getShelf_code() {
        return shelf_code;
    }

    public void setShelf_code(String shelf_code) {
        this.shelf_code = shelf_code;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }
}
