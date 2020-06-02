package com.cl.base;

import android.widget.ImageView;
import android.widget.TextView;

import com.cl.data.Device;
import com.cl.frame.FrameApplication;
import com.cl.frame.secret.SystemUtils;
import com.cl.the_projext.R;
import com.yiyatech.utils.NetworkUtils;

import butterknife.BindView;

public abstract class BaseSplashActivity extends BaseMvpActivity {
    @BindView(R.id.advert_image)
    public ImageView advertImage;
    @BindView(R.id.time_view)
    public TextView timeView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_guide;
    }

    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }
}
