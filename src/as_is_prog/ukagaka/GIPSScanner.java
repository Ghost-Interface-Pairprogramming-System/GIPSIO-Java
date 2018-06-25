package as_is_prog.ukagaka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GIPSScanner
{

	Queue<String> buffer;
	Scanner sc;

	public GIPSScanner()
	{
		if (GIPS.debug)
		{
			// debugがtrueなら値をSystem.inから取るので通信しない
			sc = new Scanner(System.in);
		}
		else
		{
			try
			{
				/// http://127.0.0.1/challenge/testdataにgetアクセスして値を得て、bufferに入れる
				URL url = new URL("http://127.0.0.1/challenge/testdata");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				BufferedReader bufR = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				buffer = new LinkedList<String>();
				String st;
				while ((st = bufR.readLine()) != null)
				{
					buffer.add(st);
				}

				bufR.close();
				con.disconnect();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	// Scannerのメソッドを移植
	public String nextLine()
	{
		if (GIPS.debug)
		{
			return sc.nextLine();
		}
		else
		{
			return buffer.poll();
		}
	}

	public int nextInt()
	{
		if (GIPS.debug)
		{
			return sc.nextInt();
		}
		else
		{
			return Integer.valueOf(buffer.poll());
		}
	}

	public double nextDouble()
	{
		if (GIPS.debug)
		{
			return sc.nextDouble();
		}
		else
		{
			return Double.valueOf(buffer.poll());
		}
	}
}
