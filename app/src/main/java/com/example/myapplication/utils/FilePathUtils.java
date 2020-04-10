package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/** 文件根目录
 *
 */
public class FilePathUtils {


    private static String PackageName="/com.zgkj.zgw";

    private static String path;

    private static File file;

    /**
     * 获取文件夹根目录
     * @param cotext 当前的上下文
     * @param insidePackageName 包名,当传入null时默认在，名为（com.zgkj.zgw）的包中
     *                          * @return
     */
    public static File accessPath(Context cotext,String insidePackageName){
        if (SDExistence()){
            path=filePath()+PackageName;
            file=createFolder();
            if (insidePackageName!=null){
                path=file.getPath()+"/"+insidePackageName;
                file=createFolder();
            }
        }else{
            ToastUtils.showToast("无SD卡，请插入SD卡");
        }
        Log.e("TAG",file.getPath());
        return file;
    }

    /**
     * 判断是否有sd卡，true为有SD卡，false为没有SD卡
     * @return
     */
    public static boolean SDExistence(){
         return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机内存的跟目录
     * @return
     */
    public static String filePath(){
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 创建文件夹
     * @return
     */
    public static File createFolder(){
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    /**
     *  生成文件路径
     * @param file 路径file对象
     * @param fileName 文件的名字
     * @return
     */
    public static String getFileName(File file,String fileName){
        Log.e("TAG",file.getPath()+"/"+fileName);
        return file.getPath()+"/"+fileName;
    }

    /**
     * 保存bitmap对象，并且生成图片保存在本地
     * @param bitmap
     * @param outFile
     * @return
     */
    public static boolean saveBitMap(Bitmap bitmap,File outFile){
        try {
            FileOutputStream fileOut=new FileOutputStream(outFile);
            if(bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOut)){
                fileOut.flush();
                fileOut.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {

            Log.e("文件","deleteFile path" + filePath);

        if (!TextUtils.isEmpty(filePath)) {
            final File file = new File(filePath);

                Log.e("文件","deleteFile path exists " + file.exists());

            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 删除文件夹下所有文件
     *
     * @return
     */
    public static void deleteDirectoryAllFile(String directoryPath) {
        final File file = new File(directoryPath);
        deleteDirectoryAllFile(file);
    }

    public static void deleteDirectoryAllFile(File file) {
        if (!file.exists()) {
            return;
        }

        boolean rslt = true;// 保存中间结果
        if (!(rslt = file.delete())) {// 先尝试直接删除
            // 若文件夹非空。枚举、递归删除里面内容
            final File subs[] = file.listFiles();
            final int size = subs.length - 1;
            for (int i = 0; i <= size; i++) {
                if (subs[i].isDirectory())
                    deleteDirectoryAllFile(subs[i]);// 递归删除子文件夹内容
                rslt = subs[i].delete();// 删除子文件夹本身
            }
            // rslt = file.delete();// 删除此文件夹本身
        }

        if (!rslt) {

            return;
        }
    }


}
