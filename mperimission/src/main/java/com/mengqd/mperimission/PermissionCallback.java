package com.mengqd.mperimission;

import java.util.List;

/**
 * Package: com.mengqd.mperimission
 * User: mengqingdong
 * Email: mengqingdong@jd.com
 * Date: 2018/5/3
 * Time: 12:21
 * Description:
 * 权限结果回调
 * 3个回调方法是互斥的，每次只会有一个回调
 */

public interface PermissionCallback {
    /**
     * 获取全部权限
     * @param permissions permission list
     * @param requestCode requestCode
     */
    void onPermissionsAllGranted(int requestCode,List<String> permissions);

    /**
     * 有未通过权限且这些权限没有勾选不在提示
     * @param permissions permission list
     * @param requestCode requestCode
     */
    void onPermissionsTemporaryDenied(int requestCode,List<String> permissions);

    /**
     * 有未通过权限且这些权限勾选不在提示
     * @param permissions permission list
     * @param requestCode requestCode
     */
    void onPermissionsForeverDenied(int requestCode,List<String> permissions);
}
