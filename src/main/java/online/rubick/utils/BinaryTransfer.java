package online.rubick.utils;

/**
 * 数据转换工具;
 * 物联网设备上报的数据一般都是16进制数据，需要转换成标准的可识别数据;
 * @author 张峻峰
 * @Date 2017年10月27日
 */
public class BinaryTransfer {
	
	public static byte[] ascToBin(String hexString) {
		byte interByte[] = hexString.getBytes();
		byte[] result = new byte[interByte.length / 2];
		for (int i = 0; i < result.length; i++) {
			byte hi = interByte[i * 2];
			byte lo = interByte[i * 2 + 1];
			int h = hi > 0x40 ? 10 + hi - 0x41 : hi - 0x30;
			int l = lo > 0x40 ? 10 + lo - 0x41 : lo - 0x30;
			result[i] = (byte) (h << 4 | (l & 0x0f));
		}
		return result;
	}

	public static String binToAsc(byte[] val) {
		StringBuffer str = new StringBuffer(val.length * 2);
		for (int i = 0; i < val.length; i++) {
			char hi = Character.forDigit((val[i] >> 4) & 0x0F, 16);
			char lo = Character.forDigit(val[i] & 0x0F, 16);
			str.append(Character.toUpperCase(hi));
			str.append(Character.toUpperCase(lo));
		}
		return str.toString().trim();
	}
}
