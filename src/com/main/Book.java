package com.main;

public class Book extends LibraryItem{

    private String auther;

    Book(String T, int I, boolean S, String A){
        super(T,I,S);
        auther=A;
    }

    public String getAuther() {
        return this.auther;
    }

    public void setAuther(String Auther){
        this.auther = Auther;
    }

    @Override
    public void print(){
        super.print();
        System.out.println(" Auther: " + auther + ", Checked Out Status: " + this.isCheckedOutStatus());
    }
}
