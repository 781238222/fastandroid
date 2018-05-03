[English](https://github.com/781238222/fastandroid/blob/master/mperimission/README_en.md)
[中文](https://github.com/781238222/fastandroid/blob/master/mperimission/README.md)
# 动态权限申请

1、引入jar包：
    
> implementation 'com.mengqd:mpermissions:1.0.0'

2、在Activity中使用：
  ```
  public class MainActivity extends AppCompatActivity {
  	    @Override
  	    protected void onCreate(Bundle savedInstanceState) {
  	        super.onCreate(savedInstanceState);
  	        setContentView(R.layout.activity_main);
  	        
  	          MPermissions.createBuilder(activity)
                             .requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                     Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                     Manifest.permission.ACCESS_COARSE_LOCATION})
                             .setPermissionCallback(new PermissionCallback() {
                                 @Override
                                 public void onPermissionsAllGranted(List<String> permissions) {
                                     
                                 }
             
                                 @Override
                                 public void onPermissionsTemporaryDenied(List<String> permissions) {
             
                                 }
             
                                 @Override
                                 public void onPermissionsForeverDenied(List<String> permissions) {
             
                                 }
                             }).build();
                            
  	    }
  	
  	    @Override
  	    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
  	        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  	         MPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
  	  }
  	}
  ```
  注意：onRequestPermissionsResult中提交处理结果。
  
  3、在Fragment中使用：
  和在Activity中使用一样，唯一的却别是activity的onRequestPermissionsResult方法中要调用fragment的onRequestPermissionsResult
  方法
  ```
  mPermissionTestFragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
  ```
  
  4、PermissionCallback有个默认实现的抽象类，此类实现了权限拒绝后再次申请未通过的权限
   
    ```
     MPermissions.createBuilder(activity)
                    .requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION})
                    .setPermissionCallback(new AbstractDefaultPermissionCallback(activity) {
                        @Override
                        public void onPermissionsAllGranted(List<String> permissions) {
                            
                        }
                    }).build();
    ```
    
    注意此类实现了勾选“不在提示”的权限申请框，理想情况下应该跳转到权限设置界面，但国内room五花八门，除了跳转不一样外，有的即使
    跳转了也无法修改，所以此情况下大家酌情处理。