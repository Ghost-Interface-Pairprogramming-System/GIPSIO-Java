package as_is_prog.ukagaka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GIPSScanner_Socket
{

	Queue<String> buffer;
	Scanner sc;

	public GIPSScanner_Socket()
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
				// localhost,Port 12304にアスセス
				Socket socket = new Socket("localhost", 12304);
				// Serverに対して問題集に対応した文字列送信 ←必要・・・？
				PrintWriter priW = new PrintWriter(socket.getOutputStream());
				priW.write("challenge\n");
				priW.flush();
				// 送信した文字列に対応した問題集の取得
				BufferedReader bufR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				buffer = new LinkedList<String>();
				String st;
				while ((st = bufR.readLine()) != null)
				{
					buffer.add(st);
				}
				bufR.close();
				socket.close();
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
