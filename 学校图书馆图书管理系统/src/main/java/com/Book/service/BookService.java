package com.Book.service;

import com.Book.entity.BookInfo;
import com.Book.entity.ShelfInfo;
import com.Book.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public int getCurrentPageNumber(){//得到需要的页面数量
        int n = bookMapper.getCurrentPageNumber();
        return (n-1)/10 + 1;
    }

    public List<BookInfo> getBookList(String fieldName, String fieldValue, String currentPage, String pageSize, int i){
        int pos = 0;
        pos = (Integer.parseInt(currentPage)-1) * (Integer.parseInt(pageSize));//pos就是当前页面的最小值
        if(i==0)
            return bookMapper.getBookList2(fieldName, fieldValue, pos, pageSize);
        if(i==1)
            return bookMapper.getBookList1(fieldName, fieldValue, pos, pageSize);
        return bookMapper.getBookList3(fieldName, fieldValue, pos, pageSize);
    }

    public int getBookNumber(String code){
        return bookMapper.getBookNumber(code);
    }//得到图书流水号

    public void code(BookInfo bookInfo){//产生图书编号
        String code = "1";
        if(bookInfo.getKp1_id() >= 10)
            code += bookInfo.getKp1_id();
        else
            code += "0" + bookInfo.getKp1_id();
        if(bookInfo.getKp2_id() >= 10)
            code += bookInfo.getKp2_id();
        else
            code += "0" + bookInfo.getKp2_id();
        int n = getBookNumber(code)+1;
        if(n >= 10)
            code += n;
        else
            code += "0" + n;
        bookInfo.setCode(code);
    }

    public int insertABook(BookInfo bookInfo){//插入图书
        code(bookInfo);
        return bookMapper.insertABook(bookInfo);
    }

    public List<ShelfInfo> getShelfList(){
        return bookMapper.getShelfList();
    }

    public int putBookIntoShelf(String code, String shelf_id){
        return bookMapper.putBookIntoShelf(code, shelf_id);
    }

    public int putBookIntoInventory(String code){
        return bookMapper.putBookIntoInventory(code);
    }

    public int deleteABook(String code){
        return bookMapper.deleteABook(code);
    }

    public int undoDelete(String code){
        return bookMapper.undoDelete(code);
    }

    public String[] getShelf(int level){
        String[] shelf1 = {"A", "B", "C", "D", "E", "F"};
        String[] shelf2 = {"G", "H", "I", "J", "K", "L"};
        String[] shelf3 = {"M", "N", "O", "P", "Q", "R"};
        switch (level){
            case 1:return shelf1;
            case 2:return shelf2;
            case 3:return shelf3;
        }
        return null;
    }

    public BookInfo getBookInfo(String code){
        BookInfo bookInfo = bookMapper.getBookInfo(code);
        return bookInfo;
    }

    public List<BookInfo> getBookList(String shelf_code){
        return bookMapper.getBookList(shelf_code);
    }
}
