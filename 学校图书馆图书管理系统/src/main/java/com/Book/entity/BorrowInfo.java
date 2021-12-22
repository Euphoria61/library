package com.Book.entity;

import java.sql.Date;

public class BorrowInfo {
    private int borrow_id;
    private String code;
    private String stu_id;
    private int id;
    private Date borrow_time;
    private Date need_return_time;
    private Date real_return_time;
    private int is_return;
    private float cost;
    private String book_name;
    private String time;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public Date getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(Date borrow_time) {
        this.borrow_time = borrow_time;
    }

    public Date getNeed_return_time() {
        return need_return_time;
    }

    public void setNeed_return_time(Date need_return_time) {
        this.need_return_time = need_return_time;
    }

    public Date getReal_return_time() {
        return real_return_time;
    }

    public void setReal_return_time(Date real_return_time) {
        this.real_return_time = real_return_time;
    }

    public int getIs_return() {
        return is_return;
    }

    public void setIs_return(int is_return) {
        this.is_return = is_return;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
