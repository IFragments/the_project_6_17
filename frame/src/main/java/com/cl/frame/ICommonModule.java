package com.cl.frame;

public interface ICommonModule<S> {
    void getData(ICommonPresenter pPresenter,int whichApi,S... params);
}
