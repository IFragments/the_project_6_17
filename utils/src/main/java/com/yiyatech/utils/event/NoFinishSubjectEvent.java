package com.yiyatech.utils.event;

public class NoFinishSubjectEvent {
    int pro;
    int spId;

    public NoFinishSubjectEvent(int pro, int spId) {
        this.pro = pro;
        this.spId = spId;
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
