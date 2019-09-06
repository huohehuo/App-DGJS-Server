package PicServer;

import Bean.DownloadReturnBean;
import Utils.CommonJson;
import Utils.Lg;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/ImageGetting")
public class ImageGetting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        Gson gson = new Gson();
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String json = request.getParameter("json");
        String SQL = "";
        String con = "";
        //先确定下面的Assist服务端电脑的位置，再创建与app端传过来的文件夹名称相对应的文件夹，用于构成服务端完成文件夹地址，用于app端查看服务端该文件夹地址下的图片
        if(json!=null&&!json.equals("")){
            DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                downloadReturnBean.imgAddresses = dealPic(FileManager.getImgListByDir("E:/FZ-kingdee/FzApp-DG-JSDZ/App-DGJS-Server/out/artifacts/Assist/"+json));
                Lg.e("得到服务端文件夹文件地址：",downloadReturnBean);
                writer.write(CommonJson.getCommonJson(true,gson.toJson(downloadReturnBean)));
        }
    }

    //截取文件夹中的图片数据
    private ArrayList<ImgAddress> dealPic(List<String> list){
        ArrayList<ImgAddress> container = new ArrayList<>();
        if (null==list || list.size()<=0){
            container.add(new ImgAddress("图片数据为空"));
            return container;
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                String[] split = list.get(i).split("\\\\");
                container.add(new ImgAddress(split[split.length-1],list.size()));
            }
        }catch (Exception e){
            container.add(new ImgAddress("图片数据解析错误"));
            return container;
        }
        return container;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
