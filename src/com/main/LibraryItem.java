package com.main;

public class LibraryItem {

    private String title;
    private int ID;
    private boolean checkedOutStatus;

    LibraryItem(){
        title = "";
        ID = 0;
        checkedOutStatus=false;
    }

    LibraryItem(String title, int ID, boolean status){
        this.title = title;
        this.ID = ID;
        this.checkedOutStatus=status;
    }

    public void setTitle(String newTitle){
        this.title=newTitle;
    }

    public String getTitle(){
        return title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isCheckedOutStatus() {
        return checkedOutStatus;
    }

    public void setCheckedOutStatus(boolean checkedOutStatus) {
        this.checkedOutStatus = checkedOutStatus;
    }

    public void print(){
        System.out.print("'"+title+"'"+ " ID: " + ID + ",");
    }
}
