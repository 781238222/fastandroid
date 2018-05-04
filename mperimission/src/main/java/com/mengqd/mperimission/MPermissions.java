package com.mengqd.mperimission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package: com.jd.retail.take.terminal.utils
 * User: mengqingdong
 * Email: mengqingdong@jd.com
 * Date: 2018/5/3
 * Time: 10:08
 * Description: 动态申请权限封装
 */

public class MPermissions {


    static Activity activity;
    List<String> permissionList;
    static PermissionCallback permissionCallback;
    int requestCode;
    private MPermissions(Builder builder) {
        this.activity = builder.activity;
        this.permissionList = builder.permissionList;
        this.permissionCallback = builder.permissionCallback;
        this.requestCode = builder.requestCode;
        checkPermissions();
    }

    /**
     * 在Activity.onRequestPermissionsResult方法中回调此方法
     * 处理申请权限结果
     *
     * @param requestCode  request code
     * @param permissions  permission list
     * @param grantResults 结果集合
     */
    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //申请通过的权限列表
        List<String> grantedList = new ArrayList<>();
        //申请失败的权限列表
        List<String> deniedList = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                grantedList.add(permissions[i]);
            } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                deniedList.add(permissions[i]);
            }
        }
        if (deniedList.size() == 0) {
            //没有未通过的权限
            if (null != permissionCallback) {
                permissionCallback.onPermissionsAllGranted(requestCode,null);
            }
            return;
        }
        //有未通过权限且这些权限没有勾选不在提示
        List<String> temporaryDeniedList = new ArrayList<>();
        //有未通过权限且这些权限勾选不在提示
        List<String> foreverDeniedList = new ArrayList<>();
        for (String permission : deniedList) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                temporaryDeniedList.add(permission);
            } else {
                foreverDeniedList.add(permission);
            }
        }
        if (foreverDeniedList.size() > 0) {
            //有未通过权限且这些权限勾选不在提示
            if (null != permissionCallback) {
                permissionCallback.onPermissionsForeverDenied(requestCode, foreverDeniedList);
            }
            return;
        }
        //有未通过权限且这些权限没有勾选不在提示
        if (null != permissionCallback) {
            permissionCallback.onPermissionsTemporaryDenied(requestCode, temporaryDeniedList);
        }
    }

    /**
     * 检测并申请权限
     */
    @TargetApi(23)
    private void checkPermissions() {
        List<String> applyPermissionList = new ArrayList<>();
        for (String permission : this.permissionList) {
            if (!hasPermission(activity, permission)) {
                applyPermissionList.add(permission);
            }
        }
        if (applyPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(activity, applyPermissionList.toArray(new String[applyPermissionList.size()]),
                   requestCode );
        } else {
            //拥有全部权限
            if (null != permissionCallback) {
                permissionCallback.onPermissionsAllGranted(this.requestCode,null);
            }
        }
    }

    /**
     * 检测是否有当前权限
     */
    private boolean hasPermission(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static Builder createBuilder(Activity activity) {
        return new Builder(activity);
    }

    public static final class Builder {
        private static final int REQUEST_PERMISSION_DEFAULT_CODE = 100;
        Activity activity;
        List<String> permissionList;
        PermissionCallback permissionCallback;
        int requestCode = REQUEST_PERMISSION_DEFAULT_CODE;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder requestPermissions(List<String> permissionList) {
            this.permissionList = permissionList;
            return this;
        }

        public Builder requestPermissions(String[] permissions) {
            this.permissionList = Arrays.asList(permissions);
            return this;
        }

        public Builder requestPermissions(String permission) {
            if (TextUtils.isEmpty(permission)) {
                return this;
            }
            if (null == permissionList) {
                permissionList = new ArrayList<>();
            }
            permissionList.add(permission);
            return this;
        }

        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public Builder setPermissionCallback(PermissionCallback permissionCallback) {
            this.permissionCallback = permissionCallback;
            return this;
        }

        public MPermissions build() {
            MPermissions mPermissions = new MPermissions(this);
            return mPermissions;
        }
    }

}
