package com.Book.service;

import com.Book.entity.*;
import com.Book.mapper.WebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebService {
    @Autowired
    private WebMapper webMapper;
    @Autowired
    private BorrowService borrowService;

    public int checkLoginStu(String userName, String pwd){ return webMapper.checkLoginStu(userName, pwd); }

    public int checkLoginAtt(String userName, String pwd){
        return webMapper.checkLoginAtt(userName, pwd);
    }

    public List<NationInfo> getNationList(){
        return webMapper.getNationList();
    }

    public List<XyInfo> getXyList(){
        return webMapper.getXyList();
    }

    public List<ProInfo> getProList(){
        return webMapper.getProList();
    }

    public String getStuInfo(String stu_id){ return webMapper.getStuInfo(stu_id); }

    public List<CourseInfo> getCourseList(){
        return webMapper.getCourseList();
    }

    public List<Kp1Info> getKp1List(){
        return webMapper.getKp1List();
    }

    public List<Kp2Info> getKp2List(){
        return webMapper.getKp2List();
    }

    public List<BorrowInfo> getBorrowList(){
        return webMapper.getBorrowList();
    }

    public List<StudentInfo> getStuList(){
        return webMapper.getStuList();
    }
    public StudentInfo getStuListone(String stu_id){
        return (StudentInfo) webMapper.getStuListone(stu_id);
    }


    public int updateCost(){
        List<BorrowInfo> list = getBorrowList();
        for(BorrowInfo borrowInfo : list){
            borrowInfo.setCost(borrowService.getCost(borrowService.getDays(borrowService.getBorrowTime(borrowInfo))));
            int i = webMapper.setBorrowCost(borrowInfo);
        }
        List<StudentInfo> studentInfoList = getStuList();
        for(StudentInfo studentInfo : studentInfoList){
            String stu_id = studentInfo.getStu_id();
            String cost = webMapper.getAllCost(stu_id);
            float stuCost;
            if(cost!=null)
                stuCost = Float.valueOf(cost);
            else
                stuCost = 0;
            int i = webMapper.setStuCost(stu_id, stuCost);
        }
        return 1;
    }
}