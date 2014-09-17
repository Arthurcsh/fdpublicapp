package com.wondersgroup.fdpublicapp.common.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Properties;
import com.wondersgroup.fdpublicapp.common.custom.FDViewUtil;
import com.wondersgroup.fdpublicapp.common.database.FDSQLHelper;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;
import com.wondersgroup.fdpublicapp.personal.mode.FDUser;
import com.wondersgroup.fdpublicapp.widget.FDUIHelper;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

/**
 * 全局应用程序类：数据库、应用配置及访问网络数据
 * @author chengshaohua
 * @created 2014-2-12
 */
public class FDAppContext extends Application {
	
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;
	
	public static final int PAGE_SIZE = 20;           //默认分页大小
	private static final int CACHE_TIME = 60*60000;   //缓存失效时间
	private boolean login = false;	                  //登录状态
	private int loginUid = 0;	                      //登录用户的id
	private String saveImagePath;                     //保存图片路径
	private static FDAppContext mAppApplication;
	private FDSQLHelper sqlHelper;
	private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();
	public static final String FD_USER_FILE = "fd_user";
	
	private Handler unLoginHandler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 1){
				FDViewUtil.showToast(FDAppContext.this, "用户没有登录,请先登录..");
				FDUIHelper.showLoginDialog(FDAppContext.this);
			}
		}		
	};
	
	public void onCreate() {
		super.onCreate();
        //注册App异常崩溃处理器
//        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        
        initContext();
	}

	/**
	 * 初始化
	 */
	private void initContext(){
		//设置保存图片的路径
//		saveImagePath = getProperty(AppConfig.SAVE_IMAGE_PATH);
//		if(StringUtils.isEmpty(saveImagePath)){
//			setProperty(AppConfig.SAVE_IMAGE_PATH, AppConfig.DEFAULT_SAVE_IMAGE_PATH);
//			saveImagePath = AppConfig.DEFAULT_SAVE_IMAGE_PATH;
//		}
		
		mAppApplication = this;
		
		initLoginInfo();
	}
	
	/** 获取Application */
	public static FDAppContext getApplication() {
		return mAppApplication;
	}
	
	/** 获取数据库Helper */
	public FDSQLHelper getSQLHelper() {
		if (sqlHelper == null)
			sqlHelper = new FDSQLHelper(mAppApplication);
		return sqlHelper;
	}
	
	/**
	 * 检测当前系统声音是否为正常模式
	 * @return
	 */
	public boolean isAudioNormal() {
		AudioManager mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE); 
		return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
	}
	
	/**
	 * 检测网络是否可用
	 * @return
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 获取当前网络类型
	 * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
	 */
	public int getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}		
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if(!StringUtils.isEmpty(extraInfo)){
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}
	
	/**
	 * 判断当前版本是否兼容目标版本的方法
	 * @param VersionCode
	 * @return
	 */
	public static boolean isMethodsCompat(int VersionCode) {
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}
	
	
	/**
	 * 用户是否登录
	 * @return
	 */
	public boolean isLogin() {
		return login;
	}
	
	/**
	 * 获取登录用户id
	 * @return
	 */
	public int getLoginUid() {
		return this.loginUid;
	}
	
	/**
	 * 用户注销
	 */
	public void Logout() {
		this.login = false;
		this.loginUid = 0;
	}
	
	/**
	 * 未登录或修改密码后的处理
	 */
	public Handler getUnLoginHandler() {
		return this.unLoginHandler;
	}
	
	/**
	 * 初始化用户登录信息
	 */
	public void initLoginInfo() {
		FDUser loginUser = getLoginInfo();
		
		if(loginUser!=null){
			this.loginUid = loginUser.getId();
			this.login = true;
		}else{
			this.Logout();
		}
	}
	
	/**
	 * 用户登录验证
	 * @param account
	 * @param pwd
	 * @return
	 */
	public FDUser loginVerify(String account, String pwd) {
		return null;
	}
	
	
	
	/**
	 * 保存登录信息
	 * @param username
	 * @param pwd
	 */
	public void saveLoginInfo(final FDUser user) {
		if(user==null) return;
		
		this.loginUid = user.getId();
		this.login = true;
		if(user.getUsername()!=null) {
			setProperties(new Properties(){{
				setProperty("user.uid", String.valueOf(loginUid));
				setProperty("user.name", user.getUsername());
				setProperty("user.nickName", user.getNickName()==null?"":user.getNickName());
				setProperty("user.face", user.getHeadImgUrl()==null?"":user.getHeadImgUrl());
				setProperty("user.email", user.getEmail());
				setProperty("user.sex", ""+user.getSex());
				setProperty("user.birthday", user.getBirthday()==null?"":user.getBirthday());
				setProperty("user.mobile", user.getMobilePhone());
			}});
			
			saveObject(user, FD_USER_FILE);
		}
				
		System.out.println("Login save  ------------------------------------ "+user.getUsername());
	}
	
	/**
	 * 清除登录信息
	 */
	public void cleanLoginInfo() {
		this.loginUid = 0;
		this.login = false;
		removeProperty("user.uid","user.name","user.nickName", "user.face","user.sex","user.pwd","user.email",
				"user.location","user.birthday","user.mobile","user.score","user.isRememberMe");
	}
	
	/**
	 * 获取登录信息
	 * @return
	 */
	public FDUser getLoginInfo() {		
		FDUser user = new FDUser();		
		user.setId(StringUtils.toInt(getProperty("user.uid"), 0));
		user.setUsername(getProperty("user.name"));
		user.setNickName(getProperty("user.nickName"));
		user.setHeadImgUrl(getProperty("user.face"));
		user.setEmail(getProperty("user.email"));
		String userSex = getProperty("user.sex");
		user.setSex(Integer.valueOf(userSex!=null?userSex:"0"));
		user.setBirthday(getProperty("user.birthday"));
		user.setMobilePhone(getProperty("user.mobile"));
		
		return user;
	}
	
	/**
	 * 获取用户名
	 * @return  登陆用户名
	 */
	public String getLoginUserName() {
		String userName = null;
		FDUser loginUser = getLoginInfo();
		if(loginUser!=null) {
			userName = loginUser.getUsername();
		}
		return userName;
	}
	
	/**
	 * 获取App安装包信息
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try { 
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {    
			e.printStackTrace(System.err);
		} 
		if(info == null) info = new PackageInfo();
		return info;
	}
	
	/**
	 * 是否启动检查更新
	 * @return
	 */
	public boolean isCheckUp() {
		String perf_checkup = "auto_check";
		//默认是开启
		if(StringUtils.isEmpty(perf_checkup))
			return true;
		else
			return StringUtils.toBool(perf_checkup);
	}
	
	/**
	 * 设置启动检查更新
	 * @param b
	 */
	public void setConfigCheckUp(boolean b) {
		setProperty("auto_check", String.valueOf(b));
	}
	
	/**
	 * 判断缓存数据是否可读
	 * @param cachefile
	 * @return
	 */
	private boolean isReadDataCache(String cachefile) {
		return readObject(cachefile) != null;
	}
	
	/**
	 * 判断缓存是否存在
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile) {
		boolean exist = false;
		File data = getFileStreamPath(cachefile);
		if(data.exists())
			exist = true;
		return exist;
	}
	
	/**
	 * 判断缓存是否失效
	 * @param cachefile
	 * @return
	 */
	public boolean isCacheDataFailure(String cachefile) {
		boolean failure = false;
		File data = getFileStreamPath(cachefile);
		if(data.exists() && (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME)
			failure = true;
		else if(!data.exists())
			failure = true;
		return failure;
	}
	
	/**
	 * 清除app缓存
	 */
	public void clearAppCache() {
		//清除webview缓存
//				File file = CacheManager.getCacheFileBaseDir();  
//				if (file != null && file.exists() && file.isDirectory()) {  
//				    for (File item : file.listFiles()) {  
//				    	item.delete();  
//				    }  
//				    file.delete();  
//				}  		  
				deleteDatabase("webview.db");  
				deleteDatabase("webview.db-shm");  
				deleteDatabase("webview.db-wal");  
				deleteDatabase("webviewCache.db");  
				deleteDatabase("webviewCache.db-shm");  
				deleteDatabase("webviewCache.db-wal");  
				//清除数据缓存
				clearCacheFolder(getFilesDir(),System.currentTimeMillis());
				clearCacheFolder(getCacheDir(),System.currentTimeMillis());
				//2.2版本才有将应用缓存转移到sd卡的功能
				if(isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)){
//					clearCacheFolder(MethodsCompat.getExternalCacheDir(this),System.currentTimeMillis());
				}
				//清除编辑器保存的临时内容
				Properties props = getProperties();
				for(Object key : props.keySet()) {
					String _key = key.toString();
					if(_key.startsWith("temp"))
						removeProperty(_key);
				}
	}	
	
	/**
	 * 清除缓存目录
	 * @param dir 目录
	 * @param numDays 当前系统时间
	 * @return
	 */
	private int clearCacheFolder(File dir, long curTime) {          
	    int deletedFiles = 0;         
	    if (dir!= null && dir.isDirectory()) {             
	        try {                
	            for (File child:dir.listFiles()) {    
	                if (child.isDirectory()) {              
	                    deletedFiles += clearCacheFolder(child, curTime);          
	                }  
	                if (child.lastModified() < curTime) {     
	                    if (child.delete()) {                   
	                        deletedFiles++;           
	                    }    
	                }    
	            }             
	        } catch(Exception e) {       
	            e.printStackTrace();    
	        }     
	    }       
	    return deletedFiles;     
	}
	
	/**
	 * 将对象保存到内存缓存中
	 * @param key
	 * @param value
	 */
	public void setMemCache(String key, Object value) {
		memCacheRegion.put(key, value);
	}
	
	/**
	 * 从内存缓存中获取对象
	 * @param key
	 * @return
	 */
	public Object getMemCache(String key){
		return memCacheRegion.get(key);
	}
	
	/**
	 * 保存磁盘缓存
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void setDiskCache(String key, String value) throws IOException {
		FileOutputStream fos = null;
		try{
			fos = openFileOutput("cache_"+key+".data", Context.MODE_PRIVATE);
			fos.write(value.getBytes());
			fos.flush();
		}finally{
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 获取磁盘缓存数据
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDiskCache(String key) throws IOException {
		FileInputStream fis = null;
		try{
			fis = openFileInput("cache_"+key+".data");
			byte[] datas = new byte[fis.available()];
			fis.read(datas);
			return new String(datas);
		}finally{
			try {
				fis.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 保存对象
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = openFileOutput(file, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				oos.close();
			} catch (Exception e) {}
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 读取对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file){
		if(!isExistDataCache(file)) return null;
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable)ois.readObject();
		}catch(FileNotFoundException e){
		}catch(Exception e){
			e.printStackTrace();
			//反序列化失败 - 删除缓存文件
			if(e instanceof InvalidClassException){
				File data = getFileStreamPath(file);
				data.delete();
			}
		}finally{
			try {
				ois.close();
			} catch (Exception e) {}
			try {
				fis.close();
			} catch (Exception e) {}
		}
		return null;
	}

	/**
	 * 获取内存中保存图片的路径
	 * @return
	 */
	public String getSaveImagePath() {
		return saveImagePath;
	}
	
	/**
	 * 设置内存中保存图片的路径
	 * @return
	 */
	public void setSaveImagePath(String saveImagePath) {
		this.saveImagePath = saveImagePath;
	}	
	
	public boolean containsProperty(String key){
		Properties props = getProperties();
		 return props.containsKey(key);
	}
	
	public void setProperties(Properties ps){
		AppConfig.getAppConfig(this).set(ps);
	}

	public Properties getProperties(){
		return AppConfig.getAppConfig(this).get();
	}
	
	public void setProperty(String key,String value){
		AppConfig.getAppConfig(this).set(key, value);
	}
	public void setProperty(String key,boolean value){
		AppConfig.getAppConfig(this).set(key, StringUtils.getBool(value));
	}
	public boolean getBooleanProperty(String key) {
		String booleanPro = AppConfig.getAppConfig(this).get(key);
		return StringUtils.toBool(booleanPro);
	}
	
	public String getProperty(String key){
		return AppConfig.getAppConfig(this).get(key);
	}
	public void removeProperty(String...key){
		AppConfig.getAppConfig(this).remove(key);
	}
	
	public void onTerminate() {
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
		//整体摧毁的时候调用这个方法
	}
}
