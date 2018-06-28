import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSingleton02 {
	public static void main(String[] args) {
		String s1=DateUtils.format(new Date());
		System.out.println(s1);
		
		String s2=DateUtils.format(new Date());
		System.out.println(s2);
		
		SimpleDateFormat sd1=DateUtils.getInstance();
		SimpleDateFormat sd2=DateUtils.getInstance();
		SimpleDateFormat sd3=DateUtils.getInstance();
		System.out.println(sd1==sd2);
		System.out.println(sd3==sd2);
	}

}

//如何实现线程内部单例？借助ThreadLocal
//ThreadLocal提供了这样的一种机制：
//1.可以将某个对象绑定到当前线程（存map)
//2.可从当前线程获取某个对象（key是谁）
class DateUtils{

	//SimpleDateFormat对象在多线程中不可共享，因为线程不安全
	private static ThreadLocal<SimpleDateFormat> td=new ThreadLocal<>();
	public static SimpleDateFormat getInstance(){

		//1.从当前线程获取
		SimpleDateFormat sdf=td.get();
		//2.当前线程没有则创建
		if(sdf==null){
			//3.将对象绑定到当前线程（存储到线程中ThreadLocalMap了）
			sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			td.set(sdf);//key-->td
		}
		//4.返回SimpleDateFormat对象
		return sdf;
	}
	public static String format(Date date){
		return getInstance().format(date);
	}
}

