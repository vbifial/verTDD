package test;

import java.util.Random;
import java.util.TreeSet;

import terap.TControl;
import junit.framework.TestCase;

public class TCTester extends TestCase {
	
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
	
	public void testCheckFactorNonPresenceRandom() {
		int n = r.nextInt(100) + 1;
		String factors[] = new String[n];
		TreeSet<String> set = new TreeSet<String>();
		for (int i = 0; i < n; i++) {
			int m = r.nextInt(100) + 1;
			StringBuilder factor = new StringBuilder();
			for (int j = 0; j < m; j++) 
				factor.append((char)('a' + r.nextInt(26)));
			factors[i] = factor.toString();
			set.add(factors[i]);
			this.m.checkFactor(factors[i]);
		}
		n = r.nextInt(200) + 1;
		for (int i = 0; i < n; i++) {
			int m = r.nextInt(100) + 1;
			StringBuilder factor = new StringBuilder();
			for (int j = 0; j < m; j++) 
				factor.append((char)('a' + r.nextInt(26)));
			String sfactor = factor.toString();
			assertEquals(this.m.isFactorOld(sfactor), set.contains(sfactor));
		}
	}
	
	public void testRecordsSimple() {
		String desease = "cold";
		String[] factors = {"cough", "fever", "headsick"};
		m.addRecord(desease, factors);
		for (int i = 0; i < factors.length; i++) {
			assertTrue(m.isFactorOld(factors[i]));
		}
		String[] badFactors = {"illness", "", "cat", "heat"};
		for (int i = 0; i < badFactors.length; i++) {
			assertFalse(m.isFactorOld(badFactors[i]));
		}
	}
	
	public void testMatchesSimple() {
		String desease = "cold";
		String[] factors = {"cough", "fever", "headsick"};
		String desease2 = "sick";
		String[] factors2 = {"headsick"};
		String desease3 = "cancer";
		String[] factors3 = {"fever"};
		String desease4 = "cold2";
		String[] factors4 = {"cough"};
		m.addRecord(desease, factors);
		m.addRecord(desease2, factors2);
		m.addRecord(desease3, factors3);
		m.addRecord(desease4, factors4);
		assertEquals(4, m.matchesCount());
	}
	
	public void testFactorsIterationSimple() {
		String desease = "cold";
		String[] factors = {"cough", "fever", "headsick"};
		String desease2 = "sick";
		String[] factors2 = {"headsick"};
		String desease3 = "cancer";
		String[] factors3 = {"fever"};
		m.addRecord(desease, factors);
		m.addRecord(desease2, factors2);
		m.addRecord(desease3, factors3);
		int cnt = 0;
		while (true) {
			String s = m.getNextFactor();
			if (s == null)
				break;
			cnt++;
		}
		assertEquals(3, cnt);
	}
	
	public void testApplyAnswerSimple() {
		String desease = "cold";
		String[] factors = {"cough", "fever", "headsick"};
		String desease2 = "sick";
		String[] factors2 = {"headsick"};
		String desease3 = "cancer";
		String[] factors3 = {"fever"};
		m.addRecord(desease, factors);
		m.addRecord(desease2, factors2);
		m.addRecord(desease3, factors3);
		assertEquals(3, m.matchesCount());
		String s = "";
		while (!s.equals("fever")) {
			s = m.getNextFactor();
		}
		m.applyAnswer(true);
		assertEquals(2, m.matchesCount());
	}
	
	public void testMatchingSimple() {
		String desease = "cold";
		String[] factors = {"cough", "fever", "headsick"};
		String desease2 = "sick";
		String[] factors2 = {"headsick"};
		String desease3 = "cancer";
		String[] factors3 = {"fever"};
		m.addRecord(desease, factors);
		m.addRecord(desease2, factors2);
		m.addRecord(desease3, factors3);
		assertEquals(3, m.matchesCount());
		String s = "";
		while (!s.equals("cough")) {
			s = m.getNextFactor();
		}
		m.applyAnswer(true);
		assertEquals(1, m.matchesCount());
		String r = m.match();
		assertEquals("cold", r);
	}
	
}
