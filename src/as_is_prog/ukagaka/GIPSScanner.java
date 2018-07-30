package as_is_prog.ukagaka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class GIPSScanner
{
	private ArrayList<String[]> list = new ArrayList<>();
	private boolean bufferChecker = true;
	private StringBuilder sb ;
	private int counterTC=-1; //テストケース用カウンター
	private int counterNo = -1; //テストケースにおけるテスト用の変数カウンター
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
						sb.append(st);
					}
					list.add(sb.toString().split(""));//区切り文字不明.w.
					//切断
					bufR.close();
					socket.close();
					bufferChecker = false;
					counterTC ++;
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
			if(counterTC <= list.size())
			{
				GIPS.endCheck = true;
			}
			counterNo++;
			return list.get(counterTC)[counterNo];
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
			if(counterTC <= list.size())
			{
				GIPS.endCheck = true;
			}
			counterNo++;
			return Integer.valueOf(list.get(counterTC)[counterNo]);
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
			if(counterTC <= list.size())
			{
				GIPS.endCheck = true;
			}
			counterNo++;
			return Double.valueOf(list.get(counterTC)[counterNo]);
		}
	}
}

