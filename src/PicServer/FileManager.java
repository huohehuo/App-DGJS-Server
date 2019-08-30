package PicServer;


import Utils.Lg;

import javax.naming.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FileManager {

    private static FileManager mInstance;

    public static FileManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (FileManager.class) {
                if (mInstance == null) {
                    mInstance = new FileManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 通过图片文件夹的路径获取该目录下的图片
     */
    public static List<String> getImgListByDir(String dir) {
        ArrayList<String> imgPaths = new ArrayList<>();
        File directory = new File(dir);
        if (directory == null || !directory.exists()) {
            Lg.e("directory为空");
            return imgPaths;
        }
        File[] files = directory.listFiles();
        //对文件进行排序
        Lg.e("files",files);
        if (null==files || files.length<= 0)return imgPaths;
        Arrays.sort(files, new FileComparator());
        for (File file : files) {
            String path = file.getAbsolutePath();
            if (FileManager.isPicFile(path)) {
//                if (path.contains("camera")){
                    imgPaths.add(path);
//                }
            }
        }
        return imgPaths;
    }

    /**
     * 是否是图片文件
     */
    public static boolean isPicFile(String path) {
        path = path.toLowerCase();
        if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")) {
            return true;
        }
        return false;
    }

    public void deletePic(String dir){
        File f = new File(dir);
        if (f.exists()) {
            f.delete();
        }
    }



    static class FileComparator implements Comparator<File> {

        @Override
        public int compare(File lhs, File rhs) {
            if (lhs.lastModified() < rhs.lastModified()) {
                return 1;
            } else {
                return -1;
            }
        }
    }



}
