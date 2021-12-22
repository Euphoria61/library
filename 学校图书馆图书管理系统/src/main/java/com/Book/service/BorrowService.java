package com.Book.service;

import com.Book.entity.BookInfo;
import com.Book.entity.BorrowInfo;
import com.Book.mapper.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Service
public class BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    public String getBorrowTime(){//得到当天日期
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = tempDate.format(new java.util.Date());
        return datetime;
    }

    public String getNeedReturnTime(){//得到需要归还的日期
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = tempDate.parse(getBorrowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, 90);

        SimpleDateFormat returnDate = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = returnDate.format(cal.getTime());
        return datetime;
    }

    public long getDays(String date){//计算天数，输入日期减去当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long date1 = 0;
        long date2 = 0;
        try{
            cal.setTime(sdf.parse(getBorrowTime()));
            date1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date));
            date2 = cal.getTimeInMillis();
        }catch (Exception e){
            e.printStackTrace();
        }

        return (date1-date2)/(1000*3600*24);
    }

    public String getBorrowTime(BorrowInfo borrowInfo){//得到借阅日期
        return borrowMapper.getBorrowTime(borrowInfo);
    }

    public float getCost(long days){//输入相差天数，输出费用
        float daysCost = (float) 0.6;
        long day = days-90;
        if(day > 0){
            return day*daysCost;
        }else{
            return 0;
        }
    }

    public int insertBorrowBook(BorrowInfo borrowInfo){//借书
        borrowInfo.setBorrow_time(Date.valueOf(getBorrowTime()));
        borrowInfo.setNeed_return_time(Date.valueOf(getNeedReturnTime()));
        borrowInfo.setId(Integer.valueOf(borrowInfo.getStu_id()));
        return borrowMapper.insertBorrowBook(borrowInfo);
    }

    public int updateBookInfo(String code){
        return borrowMapper.updateBookInfo(code);
    }//借书

    public float isCost(String stu_id){
        return borrowMapper.isCost(stu_id);
    }

    public int updateBorrowBook(BorrowInfo borrowInfo){//还书
        borrowInfo.setReal_return_time(Date.valueOf(getBorrowTime()));
        borrowInfo.setCost(getCost(getDays(getBorrowTime(borrowInfo))));
        return borrowMapper.updateBorrowBook(borrowInfo);
    }

    public List<BorrowInfo> getBorrowList(String stu_id){
        List<BorrowInfo> borrowInfo = borrowMapper.getBorrowList(stu_id);
        for(BorrowInfo borrowInfo1 : borrowInfo){
            long n = 90-getDays(String.valueOf(borrowInfo1.getBorrow_time()));
            System.out.println("borrow_time = "+borrowInfo1.getBorrow_time());
            if(n>=0){
                borrowInfo1.setTime(String.valueOf(n));
                System.out.println("n = "+n);
            }
            else
                borrowInfo1.setTime("已超时！");
        }
        return borrowInfo;
    }

    public List<BorrowInfo> getBorrowRecodeList(String stu_id){
        List<BorrowInfo> borrowInfo = borrowMapper.getBorrowRecodeList(stu_id);
        return borrowInfo;
    }

    public int updateReturnBookInfo(String code){
        return borrowMapper.updateReturnBookInfo(code);
    }//还书
}
