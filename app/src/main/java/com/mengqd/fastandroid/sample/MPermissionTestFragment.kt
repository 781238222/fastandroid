package com.mengqd.fastandroid.sample

import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mengqd.mperimission.AbstractDefaultPermissionCallback
import com.mengqd.mperimission.MPermissions
import com.mengqd.mperimission.PermissionCallback

/**
 * Package: com.mengqd.fastandroid.sample
 * User: mengqingdong
 * Email: mengqingdong@jd.com
 * Date: 2018/5/3
 * Time: 14:22
 * Description: 测试 mpermission
 */
class MPermissionTestFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_permission_test, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
          MPermissions.createBuilder(activity)
                  .requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION))
                  .setPermissionCallback(object :PermissionCallback{
                      /**
                       * 有未通过权限且这些权限没有勾选不在提示
                       * @param permissions permission list
                       */
                      override fun onPermissionsTemporaryDenied(permissions: MutableList<String>?) {
                          TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                      }

                      /**
                       * 有未通过权限且这些权限勾选不在提示
                       * @param permissions permission list
                       */
                      override fun onPermissionsForeverDenied(permissions: MutableList<String>?) {
                          TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                      }

                      /**
                       * 获取全部权限
                       * @param permissions permission list
                       */
                      override fun onPermissionsAllGranted(permissions: MutableList<String>?) {
                          TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                      }
                  })
//        MPermissions.createBuilder(activity)
//                .requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.ACCESS_COARSE_LOCATION))
//                .setPermissionCallback(object : AbstractDefaultPermissionCallback(activity) {
//                    /**
//                     * 获取全部权限
//                     */
//                    override fun onPermissionsAllGranted(permissions: MutableList<String>?) {
//
//                    }
//
//                    /**
//                     * 有未通过权限且这些权限勾选不在提示
//                     * 理想情况下应该跳转到权限设置界面，但国内room五花八门，除了跳转不一样外，有的即使
//                     * 跳转了也无法修改，所以此情况下大家酌情处理
//                     */
//                    override fun onPermissionsForeverDenied(permissions: MutableList<String>?) {
//                    }
//                })
//                .build()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        MPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}