package as_is_prog.ukagaka;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GIPS
{

	//デバッグモードの判定用変数
	static boolean debug = true;

	//モード切り替えを１度だけにする用の変数(絶対他のやり方あるやろ)
	private static boolean changeFlag =true;

	//全てのテストケースが終了しているかの判定用変数
	static boolean endCheck = false;

	private static StringBuilder sb = new StringBuilder();

	//改行文字の置換
	private static String preprocess(String x)
	{
		return x.replaceAll("\r\n", "<br>").replaceAll("\r", "<br>").replaceAll("\n", "<br>");
	}

	public static void print(String x)
	{
		if(debug)
		{
			System.out.print(x);
		}
		else
		{
			sb.append(preprocess(x));
		}
	}

	public static void println(String x)
	{
		if(debug)
		{
			System.out.println(x);
		}
		else
		{
			print(x + "<br>");
		}
	}

	public static void print(short x)
	{
		print(String.valueOf(x));
	}

	public static void println(short x)
	{
		println(String.valueOf(x));
	}

	public static void print(int x)
	{
		print(String.valueOf(x));
	}

	public static void println(int x)
	{
		println(String.valueOf(x));
	}

	public static void print(long x)
	{
		print(String.valueOf(x));
	}

	public static void println(long x)
	{
		println(String.valueOf(x));
	}

	public static void print(float x)
	{
		print(String.valueOf(x));
	}

	public static void println(float x)
	{
		println(String.valueOf(x));
	}

	public static void print(double x)
	{
		print(String.valueOf(x));
	}

	public static void println(double x)
	{
		println(String.valueOf(x));
	}
	//------------潰れたら考えるゾーン--------------
	public static void print(Object x)
	{
		print(String.valueOf(x));
	}

	public static void println(Object x)
	{
		println(String.valueOf(x));
	}
	//-------------------------------------------
	public static void print(boolean x)
	{
		print(String.valueOf(x));
	}

	public static void println(boolean x)
	{
		println(String.valueOf(x));
	}

	//デバッグモードに切り替え

	public static void debugMode()
	{
		if(changeFlag)
		{
			debug = true;
			changeFlag = false;
		}

	}

	//デバッグモードの解除
	public static void releaseMode()
	{
		if(changeFlag)
		{
			debug = false;
			changeFlag = false;
		}
	}

	public  static void end()
	{
		//endcheckがtrueで送信,falseでもう一度mainメソッドを呼び出す
		if(endCheck)
		{
			try
			{
				new UkagakaSSTPConnection("test").sendNotify1_1("OnGIPSChallenge", sb.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			StackTraceElement ste = new Throwable().getStackTrace()[1];
			try
			{
				Class<?> c = Class.forName(ste.getClassName());
				Method m = c.getMethod(ste.getMethodName());
				m.invoke(null);
			}
			catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}
}