package com.Book.controller;

import com.Book.common.Result;
import com.Book.entity.BookInfo;
import com.Book.entity.ShelfInfo;
import com.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    private int maxCurrentPage=10;

    @RequestMapping("get_book_list")//图书列表
    public Result<?> getBookList(HttpServletRequest request){

        String fieldName = request.getParameter("field_name");
        String fieldValue = request.getParameter("field_value");
        String currentPage = request.getParameter("current_page");
        String pageSize = request.getParameter("page_size");
        System.out.println(fieldName+fieldValue+currentPage+pageSize);
        if(fieldValue.equals(null))//无作用代码，防止null报错
            fieldValue = "";
        if(currentPage.equals("0"))//第一页时无法上一页
            currentPage = "1";
        if(currentPage.equals(String.valueOf(this.maxCurrentPage+1)))//最后一页时无法下一页
            currentPage = String.valueOf(this.maxCurrentPage);
        this.maxCurrentPage = bookService.getCurrentPageNumber();



        return Result.success(bookService.getBookList(fieldName, fieldValue, currentPage, pageSize, 1));
    }

    @RequestMapping("insert_a_book")//新建图书
    public Result<?> insertABook(BookInfo bookInfo){
        int i = bookService.insertABook(bookInfo);
        System.out.println(bookInfo);
        return Result.success();
    }

    @RequestMapping("show_book_inventory")//查看库存
    public Result<List<BookInfo>> showBookInventory(HttpServletRequest request){
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
        return Result.success(bookService.getBookList(fieldName, fieldValue, currentPage, pageSize, 0));
    }

    @RequestMapping("show_book_put")//图书上架
    public Result<?> putBookIntoShelf(HttpServletRequest request,
                                   Model model){
        String code = request.getParameter("code");
        String fieldName = request.getParameter("field_name");
        String fieldValue = request.getParameter("field_value");
        String currentPage = request.getParameter("current_page");
        String pageSize = request.getParameter("page_size");
        if(fieldValue.equals(null))//无作用代码，防止null报错
            fieldValue = "";




        return Result.success(bookService.getShelfList());
    }

    @RequestMapping("put_book_into_shelf")//
    public Result<?> putBookIntoShelf(HttpServletRequest request){
        String code = request.getParameter("code");
        String shelf_id = request.getParameter("shelf_id");
        String fieldName = request.getParameter("field_name");
        String fieldValue = request.getParameter("field_value");
        String currentPage = request.getParameter("current_page");
        String pageSize = request.getParameter("page_size");
        if(fieldValue.equals(null))//无作用代码，防止null报错
            fieldValue = "";
        int i = bookService.putBookIntoShelf(code, shelf_id);

        return Result.success();
    }



    @RequestMapping("put_book_into_inventory")
    public Result<?> putBookIntoInventory(HttpServletRequest request){
        String code = request.getParameter("code");

        int i = bookService.putBookIntoInventory(code);
        return Result.success();
    }
//图书报废处理
    @RequestMapping("delete_a_book")
    public Result<?> deleteABook(HttpServletRequest request){
        String code = request.getParameter("code");
        int i = bookService.deleteABook(code);
        return Result.success();
    }
//报废历史
    @RequestMapping("show_delete_recode")
    public Result<?> showDeleteRecode(HttpServletRequest request){
        String fieldName = request.getParameter("field_name");
        String fieldValue = request.getParameter("field_value");
        String currentPage = request.getParameter("current_page");
        String pageSize = request.getParameter("page_size");


       return Result.success(bookService.getBookList(fieldName, fieldValue, currentPage, pageSize, 2));
    }
//撤销报废
    @RequestMapping("undo_delete")
    public Result undoDelete(HttpServletRequest request){
        String code = request.getParameter("code");
        String fieldName = request.getParameter("field_name");
        String fieldValue = request.getParameter("field_value");
        String currentPage = request.getParameter("current_page");
        String pageSize = request.getParameter("page_size");
        if(fieldValue.equals(null))//无作用代码，防止null报错
            fieldValue = "";
        int i = bookService.undoDelete(code);

        return Result.success();
    }
//楼层
    @RequestMapping("get_level")
    public Result<?> getLevel(HttpServletRequest request,
                           Model model){
        String level = request.getParameter("level");
        System.out.println(level);
        String[] shelf = bookService.getShelf(Integer.valueOf(level));
        System.out.println(shelf);


        return Result.success(shelf);
    }
//书架号
    @RequestMapping("get_shelf_1")
    public Result<?> getShelf1(HttpServletRequest request,
                           Model model){
        String shelf = request.getParameter("shelf");
        String shelf1 = shelf + "-1";
        String shelf2 = shelf + "-2";
        String shelf3 = shelf + "-3";
        List<BookInfo> list1 = bookService.getBookList(shelf1);
        List<BookInfo> list2 = bookService.getBookList(shelf2);
        List<BookInfo> list3 = bookService.getBookList(shelf3);

        return Result.success(list1);
    }
    @RequestMapping("get_shelf_2")
    public Result<?> getShelf2(HttpServletRequest request,
                           Model model){
        String shelf = request.getParameter("shelf");
        String shelf1 = shelf + "-1";
        String shelf2 = shelf + "-2";
        String shelf3 = shelf + "-3";
        List<BookInfo> list1 = bookService.getBookList(shelf1);
        List<BookInfo> list2 = bookService.getBookList(shelf2);
        List<BookInfo> list3 = bookService.getBookList(shelf3);


        return Result.success(list2);
    }
    @RequestMapping("get_shelf_3")
    public Result<?> getShelf3(HttpServletRequest request,
                           Model model){
        String shelf = request.getParameter("shelf");
        String shelf1 = shelf + "-1";
        String shelf2 = shelf + "-2";
        String shelf3 = shelf + "-3";
        List<BookInfo> list1 = bookService.getBookList(shelf1);
        List<BookInfo> list2 = bookService.getBookList(shelf2);
        List<BookInfo> list3 = bookService.getBookList(shelf3);

        return Result.success(list3);
    }
//查看图书信息
    @RequestMapping("show_book_info")
    public Result<?> showBookInfo(HttpServletRequest request,
                               Model model){

        String code = request.getParameter("code");

        BookInfo bookInfoList =
                bookService.getBookInfo(code);


        return Result.success(bookService.getBookInfo(code));
    }
}
