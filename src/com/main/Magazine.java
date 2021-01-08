package com.main;

public class Magazine extends LibraryItem {

    private int issueNumber;

    Magazine(String T, int I, boolean S, int IN) {
        super(T, I, S);
        issueNumber = IN;
    }

    public int getIssueNumber() {
        return issueNumber;
    }
    public void setIssueNumber(int num){
        this.issueNumber=num;
    }

    @Override
    public void print(){
        super.print();
        System.out.println(" Issue Number: " + issueNumber + ", Checked Out Status: " + this.isCheckedOutStatus());
    }
}
