package com.Book.controller;

import com.Book.common.Result;
import com.Book.entity.*;
import com.Book.service.BorrowService;
import com.Book.service.StudentService;
import com.Book.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController

public class WebController {
    @Autowired
    private WebService webService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("show_login")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("show_main")
    public String showMain(HttpServletRequest request,
                           Model model){
        String stu_id = request.getParameter("stu_id");
        String stu_name = webService.getStuInfo(stu_id);
        System.out.println(stu_name);
        model.addAttribute("stu_id", stu_id);
        model.addAttribute("stu_name", stu_name);
        return "main";
    }

    @RequestMapping("show_register")
    public String showRegister(Model model){
        List<NationInfo> nationInfoList =
                webService.getNationList();
        List<XyInfo> xyInfoList =
                webService.getXyList();
        List<ProInfo> proInfoList =
                webService.getProList();

        model.addAttribute("nation_list", nationInfoList);
        model.addAttribute("xy_list", xyInfoList);
        model.addAttribute("pro_list", proInfoList);

        return "register";
    }

    @RequestMapping ("check_login")
    public Result checkLogin( HttpServletRequest request,
                             RedirectAttributes redirectAttributes){
        String userName = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String select = request.getParameter("select");
        System.out.println(userName+pwd+select);
        int flag = 0;
        if(select.equals("学生"))
            flag = webService.checkLoginStu(userName, pwd);
        else
            flag = webService.checkLoginAtt(userName, pwd);
        if(flag==0){
            return Result.error("-1", "用户名或密码错误");
        }else{
           redirectAttributes.addAttribute("stu_id", userName);
           int i = webService.updateCost();
            return Result.success(webService.getStuListone(userName));
        }
    }

    @RequestMapping("show_update")
    public String updateMyInfo(HttpServletRequest request,
                               Model model){
        String stu_id = request.getParameter("stu_id");
        List<StudentInfo> list =
                studentService.getMyInfo(stu_id);
        List<NationInfo> nationInfoList =
                webService.getNationList();
        List<XyInfo> xyInfoList =
                webService.getXyList();
        List<ProInfo> proInfoList =
                webService.getProList();


        model.addAttribute("my_info", list);

        model.addAttribute("nation_list", nationInfoList);
        model.addAttribute("xy_list", xyInfoList);
        model.addAttribute("pro_list", proInfoList);
        return "student_update";
    }

    @RequestMapping("show_pwd_update")
    public String showPwdUpdate(HttpServletRequest request,
                                Model model){
        String stu_id = request.getParameter("stu_id");

        model.addAttribute("stu_id", stu_id);
        return "stu_pwd_update";
    }

    @RequestMapping("show_book_insert_courseInfoList")
    public Result<?> showBookInsert1(HttpServletRequest request,
                                 Model model){
        List<CourseInfo> courseInfoList =
                webService.getCourseList();
        List<Kp1Info> kp1InfoList =
                webService.getKp1List();
        List<Kp2Info> kp2InfoList =
                webService.getKp2List();

        String stu_id = request.getParameter("stu_id");
        model.addAttribute("stu_id", stu_id);
        model.addAttribute("course_list", courseInfoList);
        model.addAttribute("kp1_list", kp1InfoList);
        model.addAttribute("kp2_list", kp2InfoList);
        return Result.success(courseInfoList);
    }
    @RequestMapping("show_book_insert_kp1InfoList")
    public Result<?> showBookInsert2(HttpServletRequest request,
                                     Model model){
        List<CourseInfo> courseInfoList =
                webService.getCourseList();
        List<Kp1Info> kp1InfoList =
                webService.getKp1List();
        List<Kp2Info> kp2InfoList =
                webService.getKp2List();


        return Result.success(kp1InfoList);
    }
    @RequestMapping("show_book_insert_kp2InfoList")
    public Result<?> showBookInsert3(HttpServletRequest request,
                                     Model model){
        List<CourseInfo> courseInfoList =
                webService.getCourseList();
        List<Kp1Info> kp1InfoList =
                webService.getKp1List();
        List<Kp2Info> kp2InfoList =
                webService.getKp2List();


        return Result.success(kp2InfoList);
    }

    @RequestMapping("show_book_delete")
    public String showBookDelete(HttpServletRequest request,
                                 Model model){
        String stu_id = request.getParameter("stu_id");
        model.addAttribute("stu_id", stu_id);
        return "book_delete";
    }

    @RequestMapping("show_book_borrow")
    public String showBookBorrow(HttpServletRequest request,
                                 Model model){
        String stu_id = request.getParameter("stu_id");
        model.addAttribute("stu_id", stu_id);
        return "book_borrow";
    }

    @RequestMapping("show_book_return")
    public String showBookReturn(HttpServletRequest request,
                                 Model model){
        String stu_id = request.getParameter("stu_id");
        model.addAttribute("stu_id", stu_id);
        return "book_return";
    }

    @RequestMapping("get_book_plan")
    public String getBookPlan(HttpServletRequest request,
                              RedirectAttributes redirectAttributes){

        String stu_id = request.getParameter("stu_id");
        redirectAttributes.addAttribute("stu_id", stu_id);
        redirectAttributes.addAttribute("level", 1);
        return "redirect:get_level";
    }
}
