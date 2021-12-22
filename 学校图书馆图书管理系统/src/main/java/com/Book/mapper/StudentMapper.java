package com.Book.mapper;

import com.Book.entity.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    int isStudentExist(String stu_id);

    int insert(StudentInfo studentInfo);

    List<StudentInfo> getMyInfo(String stu_id);

    int updateStuInfo(StudentInfo studentInfo);

    @Select("SELECT COUNT(*) FROM stu_info " +
            "WHERE stu_id = #{stu_id} AND pwd = #{old_pwd}")
    int isOldPwd(@Param("stu_id") String stu_id, @Param("old_pwd") String oldPwd);

    int updateStuPwd(String stu_id, String pwd);
}
