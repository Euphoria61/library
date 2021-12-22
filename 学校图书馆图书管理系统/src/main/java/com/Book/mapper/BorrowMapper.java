package com.Book.mapper;

import com.Book.entity.BookInfo;
import com.Book.entity.BorrowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BorrowMapper {
    int insertBorrowBook(BorrowInfo borrowInfo);

    int updateBookInfo(String code);

    int updateBorrowBook(BorrowInfo borrowInfo);

    int updateReturnBookInfo(String code);

    String getBorrowTime(BorrowInfo borrowInfo);

    List<BorrowInfo> getBorrowList(String stu_id);
    List<BorrowInfo> getBorrowRecodeList(String stu_id);

    float isCost(String stu_id);
}
