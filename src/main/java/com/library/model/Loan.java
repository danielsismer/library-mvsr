package com.library.model;

import java.sql.Date;

public class Loan {

    private int id;
    private int bookd_id;
    private int user_id;
    private Date loan_date;
    private Date return_date;

    public Loan(int id, int bookd_id, int user_id, Date loan_date, Date return_date) {
        this.id = id;
        this.bookd_id = bookd_id;
        this.user_id = user_id;
        this.loan_date = loan_date;
        this.return_date = return_date;
    }

    public Loan(int bookd_id, int user_id, Date loan_date, Date return_date) {
        this.bookd_id = bookd_id;
        this.user_id = user_id;
        this.loan_date = loan_date;
        this.return_date = return_date;
    }

    public Loan(int bookd_id, int user_id) {
        this.bookd_id = bookd_id;
        this.user_id = user_id;
    }

    public Loan(int id, Date return_date) {
        this.id = id;
        this.return_date = return_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookd_id() {
        return bookd_id;
    }

    public void setBookd_id(int bookd_id) {
        this.bookd_id = bookd_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date) {
        this.loan_date = loan_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", bookd_id=" + bookd_id +
                ", user_id=" + user_id +
                ", loan_date=" + loan_date +
                ", return_date=" + return_date +
                '}';
    }
}
