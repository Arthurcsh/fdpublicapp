package com.wondersgroup.fdpublicapp.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.view.View;
import android.widget.ProgressBar;
/**
 * 下载APK, 下载完后启动安装
 * 文件保存路径 EXTERNAL_STORAGE/FDPublicApp/AndroidPublic.apk
 * @author chengshaohua
 *
 */
public class FDApkUpdateTask extends AsyncTask<String, Integer, String>{
	private String FILE_DIRECTORY = "FDPublicApp";
	private String FILE_NAME = "AndroidPublic.apk";
    private Context context;
    private PowerManager.WakeLock mWakeLock;
    private	ProgressBar progressBar;         //下载进度条
    
    public FDApkUpdateTask(Context context,ProgressBar pb) {
        this.context = context;
        this.progressBar = pb;
    }

    protected String doInBackground(String... sUrl) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(sUrl[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            
            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();
            // download the file
            input = connection.getInputStream();
            //output = new FileOutputStream("/sdcard/file_name.extension");
            //判断文件夹是否存在,不存在就创建
            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + FILE_DIRECTORY);
            if(!folder.exists()){
            	if(!folder.mkdirs()){//如果文件夹创建失败，就不创建
            		FILE_DIRECTORY = "";
            	}
            }
            //文件存在就先删除
            File file = new File(Environment.getExternalStorageDirectory()
    				+ (StringUtils.isEmpty(FILE_DIRECTORY)?"":(File.separator + FILE_DIRECTORY)) 
    				+ File.separator + FILE_NAME);
            boolean isSuccess = true;
            if(file.exists()){
            	isSuccess = file.delete();
            }
            //如果删除失败，重新命名文件,这种情况很少
            if(!isSuccess){
            	FILE_NAME = "fdsuperapp"+(Calendar.getInstance().getTimeInMillis())+".apk";
            	file = new File(Environment.getExternalStorageDirectory()
        				+ (StringUtils.isEmpty(FILE_DIRECTORY)?"":(File.separator + FILE_DIRECTORY)) + File.separator + FILE_NAME);
            }
            
            output = new FileOutputStream(file);
            
            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            	ignored.printStackTrace();
            }

            if (connection != null)
                connection.disconnect();
        }
        return null;
    }
    
    protected void onPreExecute() {
        super.onPreExecute();
        // take CPU lock to prevent CPU from going off if the user 
        // presses the power button during download
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        mWakeLock.acquire();
       // mProgressDialog.show();
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        // if we get here, length is known, now set indeterminate to false
        //mProgressDialog.setIndeterminate(false);
        //mProgressDialog.setMax(100);
        //mProgressDialog.setProgress(progress[0]);
        //Log.d(LOG_TAG, "progress : "+progress[0]);
        progressBar.setProgress(progress[0]);
    }

    protected void onPostExecute(String result) {
        mWakeLock.release();
        //mProgressDialog.dismiss();
        if (result != null)
        	//errorTextView.setText("下载出现错误，请重新下载.");
        	FDViewUtil.showToast(context, "下载出现错误，请重新下载.");
            //Toast.makeText(context,"Download error: "+result, Toast.LENGTH_SHORT).show();
        else
            //Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();
        	//uninstallAPK();
        	installAPK();
    }
    
    private void uninstallAPK() {
    	Uri packageURI = Uri.parse("package:com.wondersgroup.fdsuperapp");
    	Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
    	uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	context.startActivity(uninstallIntent);
    }
    
    /**
     * 通过反射静默卸载
     */
    private void uninstallAPKSilently() {
    	Class<?> pmService;  
        Class<?> activityTherad;  
        Method method;  
		try {
			activityTherad = Class.forName("android.app.ActivityThread");
			Class<?> paramTypes[] = getParamTypes(activityTherad,"getPackageManager");
			method = activityTherad.getMethod("getPackageManager", paramTypes);
			Object PackageManagerService = method.invoke(activityTherad);

			pmService = PackageManagerService.getClass();

			Class<?> paramTypes1[] = getParamTypes(pmService, "deletePackage");
			method = pmService.getMethod("deletePackage", paramTypes1);

			method.invoke(PackageManagerService, "com.wondersgroup.fdpublicapp", null, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    /**
	 * 启动安装APK
	 */
	private void installAPK() {
    	File file = new File(Environment.getExternalStorageDirectory()
    			+ (StringUtils.isEmpty(FILE_DIRECTORY)?"":(File.separator + FILE_DIRECTORY)) + File.separator + FILE_NAME);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
	
	/**
	 * 通过反射静默安装
	 */
	private void install() {  
		Uri  packageUri = getPackageUri();
        try {  
            Class<?> pmService;  
            Class<?> activityTherad;  
            Method method;  
              
            activityTherad = Class.forName("android.app.ActivityThread");
            Class<?> paramTypes[] = getParamTypes(activityTherad , "getPackageManager");  
            method = activityTherad.getMethod("getPackageManager", paramTypes);  
            Object PackageManagerService = method.invoke(activityTherad);  
              
            pmService = PackageManagerService.getClass(); 
              
            Class<?> paramTypes1[] = getParamTypes(pmService , "installPackage");  
            method = pmService.getMethod("installPackage", paramTypes1);  
            method.invoke(PackageManagerService , packageUri , null , 0 , null);  
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {
            e.printStackTrace();  
        }catch (ClassNotFoundException e1) {  
            e1.printStackTrace();  
        }  
    }  
      
    private Class<?>[] getParamTypes(Class<?> cls, String mName) {  
        Class<?> cs[] = null;  
  
        Method[] mtd = cls.getMethods();  
        for (int i = 0; i < mtd.length; i++) {  
            if (!mtd[i].getName().equals(mName)) {  
                continue;  
            }  
            cs = mtd[i].getParameterTypes();
        }  
        return cs;  
    }  
      
    private Uri getPackageUri() {  
        File file = new File(Environment.getExternalStorageDirectory()
    			+ (StringUtils.isEmpty(FILE_DIRECTORY)?"":(File.separator + FILE_DIRECTORY)) + File.separator + FILE_NAME);
        return Uri.fromFile(file);
    }
}
