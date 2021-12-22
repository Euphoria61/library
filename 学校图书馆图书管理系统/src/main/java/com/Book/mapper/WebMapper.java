package com.Book.mapper;


import com.Book.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WebMapper {
    @Select("SELECT COUNT(*) FROM stu_info WHERE stu_id = #{stu_id} AND pwd = #{pwd}")
    int checkLoginStu(@Param("stu_id") String userName, @Param("pwd") String pwd);

    @Select("SELECT COUNT(*) FROM att_info WHERE att_id = #{att_id} AND pwd = #{pwd}")
    int checkLoginAtt(@Param("att_id") String userName, @Param("pwd") String pwd);

    @Select("SELECT * FROM nation_info")
    List<NationInfo> getNationList();

    @Select("SELECT * FROM xy_info")
    List<XyInfo> getXyList();

    @Select("SELECT * FROM pro_info")
    List<ProInfo> getProList();

    @Select("SELECT stu_name FROM stu_info WHERE stu_id = #{stu_id}")
    String getStuInfo(@Param("stu_id") String stu_id);

    @Select("SELECT * FROM course_info")
    List<CourseInfo> getCourseList();

    @Select("SELECT * FROM kp1_info")
    List<Kp1Info> getKp1List();

    @Select("SELECT * FROM kp2_info")
    List<Kp2Info> getKp2List();

    @Select("SELECT * FROM borrow_info WHERE is_return = 0")
    List<BorrowInfo> getBorrowList();

    @Select("SELECT * FROM stu_info")
    List<StudentInfo> getStuList();
    @Select("SELECT * FROM stu_info WHERE stu_id = #{stu_id}")
    StudentInfo getStuListone(String stu_id);

    @Select("SELECT SUM(cost) FROM borrow_info WHERE stu_id = #{stu_id} AND is_pay = 0")
    String getAllCost(String stu_id);

    int setBorrowCost(BorrowInfo borrowInfo);
    int setStuCost(String stu_id, float cost);
}
