package com.example.susupay.Model;

public class TransactionDetails {
    String transaction_mode, transaction_date;
    int imageView;
    double transaction_amount = 0.00;

    public TransactionDetails(
            int imageView,
            String transaction_mode,
            double transaction_amount,
            String transaction_date){

        this.transaction_amount = transaction_amount;
        this.transaction_date = transaction_date;
        this.transaction_mode = transaction_mode;
        this.imageView = imageView;
    }

    public String getTransaction_mode() {
        return transaction_mode;
    }

    public void setTransaction_mode(String transaction_mode) {
        this.transaction_mode = transaction_mode;
    }

    public CharSequence getTransaction_date() {
        return (CharSequence) transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

}
