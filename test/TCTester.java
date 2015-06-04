package test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Random;

import terap.TControl;
import junit.framework.TestCase;

public class TCTester extends TestCase {

	
	BufferedReader in;
	PrintWriter out;
	TControl m;
	Random r;
	
	@Override
	protected void setUp() throws Exception {
		m = new TControl();
		r = new Random();
	}
	
	public void testCheckFactorNonPresenceSimple() {
		String factor = "illness";
		m.checkFactor(factor);
		String checking = "huba-buba";
		assertFalse(m.isFactorOld(checking));
	}
	
}
