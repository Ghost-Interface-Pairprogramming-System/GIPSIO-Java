package as_is_prog.ukagaka;

import java.io.IOException;

public class GIPS
{

	protected static boolean debug = true;

	private static StringBuilder sb = new StringBuilder();

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

	public static void debugMode()
	{
		debug = true;
	}

	public static void releaseMode()
	{
		debug = false;
	}

	public static void end()
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

	public static void ragnarøk()
	{
		releaseMode();
	}

	public static void nirvāṇa()
	{
		end();
	}

	public static class Q
	{
		public static class E
		{
			public static void D()
			{
				end();
			}
		}
	}
}
