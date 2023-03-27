package testUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import pageUtilities.PageContext;

public class ReportHelper {

	PageContext context;

	public ReportHelper(PageContext context) {
		this.context = context;
	}

	public static String getScreenshotName() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
		return "Failed_" + timeStamp + "_.png";
	}

}
