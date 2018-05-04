[English](https://github.com/781238222/fastandroid/blob/master/mperimission/README_en.md)
[中文](https://github.com/781238222/fastandroid/blob/master/mperimission/README.md)

# Dynamic permission application

1, the introduction of the jar package:
    
> implementation 'com.mengqd:mpermissions:1.0.0'

2, used in the Activity:

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
                                 public void onPermissionsAllGranted(int requestCode,List<String> permissions) {
                                     
                                 }
             
                                 @Override
                                 public void onPermissionsTemporaryDenied(int requestCode,List<String> permissions) {
             
                                 }
             
                                 @Override
                                 public void onPermissionsForeverDenied(int requestCode,List<String> permissions) {
             
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

  Note: The result is submitted in onRequestPermissionsResult.
  
  3, use in Fragment:

  The same as in the Activity, the only difference is that the onRequestPermissionsResult method of the activity calls the onRequestPermissionsResult of the fragment.
  method

```
 mPermissionTestFragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
```

  
  4, PermissionCallback has a default implementation of the abstract class, this class implements the permission to refuse to re-apply for failed permissions
   

```
MPermissions.createBuilder(activity)
                    .requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION})
                    .setPermissionCallback(new AbstractDefaultPermissionCallback(activity) {
                        @Override
                        Public void onPermissionsAllGranted(int requestCode,List<String> permissions) {
                            
                        }
                    }).build();
```
    
Pay attention to this type of implementation of the check box "not prompted" permission application box, under ideal circumstances should jump to the permission settings interface, but the domestic room variety, in addition to the jump is not the same, some even It can't be changed even if it jumps, so in this case, we handle it as appropriate.

Update log:
2018-5-4:
  1, increase callback parameters requestCode.
 
2018-5-3:
  1, to achieve single / multiple permissions dynamic application.
  2. The parameter in the callback is the set of corresponding permissions.