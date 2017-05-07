package cn.mahjong.utils;


import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个线程安全的日期处理类
 * 
 * @author jiangman
 * @date 2015-3-30
 */
public class DateTimeUtil {

	public final static String PATTEN_DATE = "yyyy-MM-dd";
	public final static String PATTEN_DATE1 = "yyyyMMdd";
	public final static String PATTEN_TIME = "hh:mm:ss";
	public final static String PATTEN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public final static String PATTEN_DATETIME1 = "yyyyMMddHHmmss";
	public final static String PATTEN_HOURANDMINUTE = "hh:mm";

	/** 锁对象 */
	private static final Object lockObj = new Object();

	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (tl == null) {
			synchronized (lockObj) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
					//System.out.println("put new sdf of pattern " + pattern + " to map");

					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
					// SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {
							//System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}

		return tl.get();
	}
	
	/**
	 * 当前线程销毁时，释放资源。。
	 */
	public static void clearThreadLocal(){
		try {
			Field[] fields= DateTimeUtil.class.getFields();
			for (Field field : fields) {
				if (field.getType().equals(String.class)) {
					if (sdfMap.containsKey(field.get(DateTimeUtil.class))) {
						sdfMap.get(field.get(DateTimeUtil.class)).remove();
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		clearThreadLocal();
	}

	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern)
			throws ParseException {
		return getSdf(pattern).parse(dateStr);
	}
}
