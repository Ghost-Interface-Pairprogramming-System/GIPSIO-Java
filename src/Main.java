import as_is_prog.ukagaka.GIPS;
import as_is_prog.ukagaka.GIPSScanner_Socket;

public class Main
{
	public static void main(String[] args)
	{

		GIPS.releaseMode();

		GIPSScanner_Socket gipsScanner = new GIPSScanner_Socket();

		String i1 =gipsScanner.nextLine();
		String i2 = gipsScanner.nextLine();
		String ans = i1+i2;
		GIPS.debugMode();
		GIPS.println(ans);
	}
}
