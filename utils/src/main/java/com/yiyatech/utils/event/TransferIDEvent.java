package com.yiyatech.utils.event;

public class TransferIDEvent {
    int pro;
    int spId;
    String name;

    public TransferIDEvent(int pro, int spId, String name) {
        this.pro = pro;
        this.spId = spId;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPro(int pro) {
        this.pro = pro;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public int getPro() {
        return pro;
    }

    public int getSpId() {
        return spId;
    }
}
