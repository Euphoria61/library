package com.Book.service;

import com.Book.entity.StudentInfo;
import com.Book.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;


    public int insert(StudentInfo studentInfo){//注册，插入学生信息
        int i = 0;
        i = isStudentExist(studentInfo.getStu_id());
        if(i==0)
            return studentMapper.insert(studentInfo);
        else
            return -1;//学生id已存在
    }

    public int isStudentExist(String stu_id){
        return studentMapper.isStudentExist(stu_id);
    }//判断学生是否已存在，存在返回1，不存在返回0

    public List<StudentInfo> getMyInfo(String stu_id){
        return studentMapper.getMyInfo(stu_id);
    }//得到当前登录用户名

    public int updateStuInfo(StudentInfo studentInfo){ return studentMapper.updateStuInfo(studentInfo); }//学生修改信息

    public int updateStuPwd(String stu_id, String oldPwd, String newPwd1, String newPwd2){//修改密码
        int i = 0;
        System.out.println("isOldPwd = "+studentMapper.isOldPwd(stu_id, oldPwd));
        System.out.println("newPwd1 = "+newPwd1);
        System.out.println("newPwd2 = "+newPwd2);
        if(studentMapper.isOldPwd(stu_id, oldPwd)!=0){
            if(newPwd1.equals(newPwd2)){
                i = studentMapper.updateStuPwd(stu_id, newPwd1);
            }else {
                i = -1;//两次输入密码不一致，返回i = -1
            }
        }else{
            i = -2;//输入旧密码错误，返回i = -2；
        }
        System.out.println("i = "+i);
        return i;
    }
}
