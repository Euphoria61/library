package com.Book.mapper;


import com.Book.entity.BookInfo;
import com.Book.entity.ShelfInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    @Select("SELECT COUNT(*) FROM book_info " +
            "WHERE is_exist = 1")
    int getCurrentPageNumber();

    List<BookInfo> getBookList1(String fieldName, String fieldValue, int pos, String pageSize);
    List<BookInfo> getBookList2(String fieldName, String fieldValue, int pos, String pageSize);
    List<BookInfo> getBookList3(String fieldName, String fieldValue, int pos, String pageSize);

    int getBookNumber(String code);

    int insertABook(BookInfo bookInfo);

    @Select("SELECT * FROM shelf_info WHERE shelf_id > 0")
    List<ShelfInfo> getShelfList();

    int putBookIntoShelf(String code, String shelf_id);

    int putBookIntoInventory(String code);

    int deleteABook(String code);

    int undoDelete(String code);

    BookInfo getBookInfo(String code);

    List<BookInfo> getBookList(String shelf_code);
}
