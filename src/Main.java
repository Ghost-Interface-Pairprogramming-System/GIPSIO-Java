import as_is_prog.ukagaka.GIPS;
import as_is_prog.ukagaka.GIPSScanner;

public class Main
{
	public static void main(String[] args)
	{
		//GIPS.debugMode();
		GIPS.releaseMode();
		GIPSScanner gipsScanner = new GIPSScanner();

		int i =gipsScanner.nextInt();
		int x = i;
		GIPS.println(x);

		GIPS.end();
	}
}
