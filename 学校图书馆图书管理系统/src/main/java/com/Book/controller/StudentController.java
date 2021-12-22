package com.Book.controller;

import com.Book.common.Result;
import com.Book.entity.BorrowInfo;
import com.Book.entity.StudentInfo;
import com.Book.service.BorrowService;
import com.Book.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BorrowService borrowService;
//注册
    @RequestMapping("register")
    public Result<?> register(StudentInfo studentInfo){
        int i = 0;
        i = studentService.insert(studentInfo);
        return Result.success();
    }
//个人信息
    @RequestMapping("get_my_info")
    public Result<?> getMyInfo(HttpServletRequest request,
                            Model model){
        String stu_id = request.getParameter("stu_id");
        List<StudentInfo> studentInfoList =
                studentService.getMyInfo(stu_id);
        List<BorrowInfo> borrowInfoList =
                borrowService.getBorrowList(stu_id);
        List<BorrowInfo> borrowRecodeInfoList =
                borrowService.getBorrowRecodeList(stu_id);

        return Result.success(borrowService.getBorrowList(stu_id));
    }
    @RequestMapping("get_my_return")
    public Result<?> getMyInfo2(HttpServletRequest request,
                               Model model){
        String stu_id = request.getParameter("stu_id");

        return Result.success( borrowService.getBorrowRecodeList(stu_id));
    }


    @RequestMapping("update_stu_info")
    public Result<?> updateStuInfo(StudentInfo studentInfo,
                                RedirectAttributes redirectAttributes){
        int i = studentService.updateStuInfo(studentInfo);

        return Result.success();
    }

    @RequestMapping("update_stu_pwd")
    public String updateStuPwd(HttpServletRequest request,
                               RedirectAttributes redirectAttributes){
        String stu_id = request.getParameter("stu_id");
        String oldPwd = request.getParameter("old_pwd");
        String newPwd1 = request.getParameter("new_pwd1");
        String newPwd2 = request.getParameter("new_pwd2");

        int i = studentService.updateStuPwd(stu_id, oldPwd, newPwd1, newPwd2);

        redirectAttributes.addAttribute("stu_id", stu_id);
        return "redirect:show_main";
    }
}
