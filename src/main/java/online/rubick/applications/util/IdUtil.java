package online.rubick.applications.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdUtil {

	private static Random rand;

	static {
		rand = new Random();
	}

	public static String getId() {
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + rand.nextInt(1000);
	}

}
