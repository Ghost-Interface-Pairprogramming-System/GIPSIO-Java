package as_is_prog.ukagaka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GIPSScanner
{
	private Queue<String> buffer = new LinkedList<String>();
	private boolean bufferChecker = true;
	private Scanner sc;

	public GIPSScanner()
	{
		if (GIPS.debug) //trueならSystem.in,falseなら通信
		{
			sc = new Scanner(System.in);
		}
		else
		{
			if(bufferChecker)
			{
				try
				{
					// localhost,Port 12304にアスセス
					Socket socket = new Socket("localhost", 12304);
					// serverに対して問題集に対応した文字列送信
					PrintWriter priW = new PrintWriter(socket.getOutputStream());
					priW.write("challenge\n");
					priW.flush();
					// 送信した文字列に対応した問題集の取得
					BufferedReader bufR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String st;
					while ((st = bufR.readLine()) != null)
					{
						buffer.add(st);
					}
					//切断
					bufR.close();
					socket.close();
					bufferChecker = false;
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
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
			if(buffer.peek() == null)
			{
				GIPS.endCheck = true;
			}
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
			if(buffer.peek() == null)
			{
				GIPS.endCheck = true;
			}
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
			if(buffer.peek() == null)
			{
				GIPS.endCheck = true;
			}
			return Double.valueOf(buffer.poll());
		}
	}
}

