package com.mengqd.mperimission;

import android.app.Activity;

import java.util.List;

/**
 * Package: com.mengqd.mperimission
 * User: mengqingdong
 * Email: mengqingdong@jd.com
 * Date: 2018/5/3
 * Time: 15:40
 * Description: 默认PermissionCallback
 * 1、回调 onPermissionsTemporaryDenied 时重新申请权限
 */

public abstract class AbstractDefaultPermissionCallback implements PermissionCallback {
    Activity activity;

    public AbstractDefaultPermissionCallback(Activity activity) {
        this.activity = activity;
    }

    /**
     * 有未通过权限且这些权限没有勾选不在提示
     *
     * @param permissions 未通过权限list
     */
    @Override
    public void onPermissionsTemporaryDenied(List<String> permissions) {
        MPermissions.createBuilder(activity)
                .requestPermissions(permissions)
                .setPermissionCallback(this)
                .build();
    }

    /**
     * 有未通过权限且这些权限勾选不在提示
     * 理想情况下应该跳转到权限设置界面，但国内room五花八门，除了跳转不一样外，有的即使
     * 跳转了也无法修改，所以此情况下大家酌情处理
     *
     * @param permissions 不在提示权限list
     */
    @Override
    public void onPermissionsForeverDenied(List<String> permissions) {

    }
}
