

/**
 *  * 何为单例模式？
 * 1)是一种设计套路,一种经验总结。
 * 2)是保证此类的实例内存中只有一份的设计方案
 * 单例模式的类型？
 * 1)懒汉单例(对象何时需要何时创建)
 * 2)饿汉单例(类加载时创建)
 * 单例模式的实现：
 * 1.构造方法私有化
 * 2.提供一个静态方法返回此类实例，从而供外界使用 
 *
 */
//测试多线程访问单例  222222222222222222
public class TestSingleton01 {
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new Thread(){
				public void run() {
					System.out.println(Singleton01.getInstance());
					System.out.println(Singleton01.getInstance());
					System.out.println(Singleton01.getInstance());
					System.out.println(Singleton01.getInstance());
				};
			}.start();
		}
	}
	
}

//1.懒汉加载，延迟加载
class Singleton01{
	private static Singleton01 instance;

	//生成私有无参构造方法
	public Singleton01() {
	}
	public static synchronized Singleton01 getInstance(){
		if(instance==null){
			instance=new Singleton01();
		}
		return instance;
	}
}

//2.懒汉单例优化（性能）
class Singleton02{
	/**
	 * volatile作用
	 * 1）保证线程可见（一个线程修改了此值，其他线程立刻可见）
	 * 2）进制指令重排
	 */
	private static volatile Singleton02 instance;

	private Singleton02() {
	}
	public static Singleton02 getInstance(){
		if(instance==null){
			synchronized(Singleton02.class){
				if(instance==null){
					instance=new Singleton02();
				}
			}//保证共享变量的可见性和原子性
		}
		return instance;
	}
}
//3.饿汉单例
class Singleton03{  //场景：小对象，频繁用，一初始化就new对象

	private Singleton03() {
	}
	private static Singleton03 instance=new Singleton03();
	public static Singleton03 getInstance(){
		return instance;
	}
	public void show(){}
	
}

//4.饿汉单例优化（按需加载，延迟加载）,做成内部类的形式
class Singleton04{  //场景：大对象，频繁用
	//int array[]=new int[2048]
	public Singleton04() {
	}
	static class Inner{
		static Singleton04 instance=new Singleton04();
	}
	public static Singleton04 getInstance(){
		return Inner.instance;
	}
	public static void show(){}
	public void display(){}
}

//5.饿汗单例(基于枚举)
enum Singleton05{   //固定值定义一般会用枚举
	INISTANCE;     //单例对象，类加载时创建
	public void show(){
		System.out.println("show");
	}
}
