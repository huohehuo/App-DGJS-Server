package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String getTime(boolean b) {
		SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd" : "yyyyMMdd");
		Date curDate = new Date();
		Lg.e("date", curDate);
		String str = format.format(curDate);
		return str;
	}

	public static String getTimeLong(boolean b) {
		SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd-HH-mm-ss" : "yyyyMMddHHmmss");
		Date curDate = new Date();
		Lg.e("date", curDate);
		String str = format.format(curDate);
		return str;
	}
}
