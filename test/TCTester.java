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
	
	public void testCheckFactorPresenceSimple() {
		String factor = "headsick";
		m.checkFactor(factor);
		assertTrue(m.isFactorOld(factor));
	}
	
	public void testCheckFactorPresenceRandom() {
		int n = r.nextInt(100) + 1;
		String factors[] = new String[n];
		for (int i = 0; i < n; i++) {
			int m = r.nextInt(100) + 1;
			StringBuilder factor = new StringBuilder();
			for (int j = 0; j < m; j++) 
				factor.append((char)('a' + r.nextInt(26)));
			factors[i] = factor.toString();
			this.m.checkFactor(factors[i]);
		}
		for (int i = 0; i < n; i++) {
			assertTrue(m.isFactorOld(factors[i]));
		}
	}
	
}
