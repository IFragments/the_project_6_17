package com.cl.the_projext;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cl.base.BaseMvpActivity;
import com.cl.frame.ICommonModule;

public class HomeActivity extends BaseMvpActivity {
    private NavController navController;

    @Override
    public ICommonModule setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setUpView() {
        navController = Navigation.findNavController(this, R.id.project_fragment_control);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
