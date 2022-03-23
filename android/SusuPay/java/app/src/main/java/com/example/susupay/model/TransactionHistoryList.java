package model;

import java.sql.Blob;
import java.util.Date;
public class TransactionHistoryList {
   private Date TDate;
    private Blob img;
    private double amount;
    private double deposit;
    private double withdraw;

    public  TransactionHistoryList(){}
    public TransactionHistoryList(Blob img, Date T_date, double amount, double deposit, double withdraw){
        this.img = img;
        this.TDate = T_date;
        this.amount = amount;
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

}


