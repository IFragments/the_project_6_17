package com.cl.frame;

import io.reactivex.disposables.Disposable;

public interface ICommonPresenter<B> extends ICommonView {
    void getData(int whichApi,B... pPS);
//    void getData(int whichApi,int loadType,P... pPS);
//    default void getTrendsData(int whichApi,int loadType,P... pPS){}

    void addObserver(Disposable pDisposable);
}
