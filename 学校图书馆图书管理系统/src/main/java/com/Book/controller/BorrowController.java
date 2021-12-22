package com.Book.controller;


import com.Book.common.Result;
import com.Book.entity.BookInfo;
import com.Book.entity.BorrowInfo;
import com.Book.service.BookService;
import com.Book.service.BorrowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BookService bookService;
    private int maxCurrentPage=10;

    //借书
    @RequestMapping("borrow_book")
    public Result<?> borrowBook(BorrowInfo borrowInfo,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes){
        String stu_id = request.getParameter("stu_id");

        if(borrowService.isCost(stu_id)==0){
            int i = borrowService.insertBorrowBook(borrowInfo);
            int j = borrowService.updateBookInfo(borrowInfo.getCode());
        }
        redirectAttributes.addAttribute("stu_id", stu_id);
        System.out.println("借书");
        return Result.success();
    }
    //还书
    @RequestMapping("return_book")
    public Result<?> returnBook(BorrowInfo borrowInfo,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes){
        String stu_id = request.getParameter("stu_id");

        int i = borrowService.updateBorrowBook(borrowInfo);
        int j = borrowService.updateReturnBookInfo(borrowInfo.getCode());

        redirectAttributes.addAttribute("stu_id", stu_id);
        return Result.success();
    }
    //借书列表
    @RequestMapping("get_borrow_list")
    public Result<?> getBorrowList(HttpServletRequest request,
                                Model model){
        String stu_id = request.getParameter("stu_id");
        String fieldName = request.getParameter("field_name");
        String fieldValue = request.getParameter("field_value");
        String currentPage = request.getParameter("current_page");
        String pageSize = request.getParameter("page_size");
        if(fieldValue.equals(null))//无作用代码，防止null报错
            fieldValue = "";
        if(currentPage.equals("0"))//第一页时无法上一页
            currentPage = "1";
        if(currentPage.equals(String.valueOf(this.maxCurrentPage+1)))//最后一页时无法下一页
            currentPage = String.valueOf(this.maxCurrentPage);
        this.maxCurrentPage = bookService.getCurrentPageNumber();

        return Result.success( bookService.getBookList(fieldName, fieldValue, currentPage, pageSize, -1));
    }
}
