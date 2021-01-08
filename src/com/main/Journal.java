package com.main;

public class Journal extends LibraryItem{

    private int volume;

    Journal(String T, int I, boolean S, int V){
        super(T,I,S);
        volume=V;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public void print() {
        super.print();
        System.out.println(" Volume: " + volume + ", Checked Out Status: " + this.isCheckedOutStatus());
    }
}
