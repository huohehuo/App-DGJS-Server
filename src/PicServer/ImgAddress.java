package PicServer;

public class ImgAddress {
    /**图片名字*/
    private String Error;
    private String picName;
    /**文件夹中图片的数量*/
    private int count;
    public ImgAddress() {
    }
    public ImgAddress(String picName, int count) {
        this.picName = picName;
        this.count = count;
        this.Error = "";
    }
    public ImgAddress(String error) {
        this.Error = error;
    }
}
