package online.rubick.applications.util;

/**
 * 
 * @author lyg
 *
 * 2018年2月11日
 */
public class DistanceCalcUtils {

	private static final double EARTH_RADIUS = 6378137; //赤道半径（单位m）
	
	private static double rad(double d) {
		return d * Math.PI / 180;
	}
	
	/**
	 * 基于余弦定理求两经纬度距离 
	 * 
	 * @param lng1 第一点的经度
	 * @param lat1 第一点的纬度
	 * @param lng2 第二点的经度
	 * @param lat2 第二点的纬度
	 * @return  距离单位M
	 */
	public static double latLngDistance(double lng1, double lat1, double lng2, double lat2) {
		double radlat1 = rad(lat1);
		double radlat2 = rad(lat2);
		double radLng1 = rad(lng1);
		double radlng2 = rad(lng2);
		
		if(radlat1 < 0) {
			radlat1 = Math.PI / 2 + Math.abs(radlat1);
		}
		if(radlat1 > 0) {
			radlat1 = Math.PI / 2 - Math.abs(radlat1);
		}
		if(radLng1 < 0) {
			radLng1 = Math.PI * 2 - Math.abs(radLng1);
		}
		if(radlat2 < 0) {
			radlat2 = Math.PI / 2 + Math.abs(radlat2);
		}
		if(radlat2 > 0) {
			radlat2 = Math.PI / 2 - Math.abs(radlat2);
		}
		if(radlng2 < 0) {
			radlng2 = Math.PI * 2 - Math.abs(radlng2);
		}
		
		double x1 = EARTH_RADIUS * Math.cos(radLng1) * Math.sin(radlat1);
		double y1 = EARTH_RADIUS * Math.sin(radLng1) * Math.sin(radlat1);
		double z1 = EARTH_RADIUS * Math.cos(radlat1);
		
		double x2 = EARTH_RADIUS * Math.cos(radlng2) * Math.sin(radlat2);
		double y2 = EARTH_RADIUS * Math.sin(radlng2) * Math.sin(radlat2);
		double z2 = EARTH_RADIUS * Math.cos(radlat2);
		
		double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
		double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
		double dist = theta * EARTH_RADIUS;
		return dist;
	}
}
