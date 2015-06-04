package terap;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Vector;

public class TControl {
	
	HashSet<String> hasFactor = new HashSet<String>();
	TreeSet<String> matchedDeseases = new TreeSet<String>();
	Vector<String> deseases = new Vector<String>();
	Vector<String> vfactors = new Vector<String>();
	int curFactor = 0;
	
	public void addRecord(String desease, String[] factors) {
		deseases.add(desease);
		matchedDeseases.add(desease);
		
		for (int i = 0; i < factors.length; i++) {
			checkFactor(factors[i]);
		}
	}
	
	public String getNextFactor() {
		if (curFactor < vfactors.size())
			return vfactors.get(curFactor++);
		return null;
	}
	
	public void applyAnswer(boolean ans) {
		
	}
	
	public void checkFactor(String factor) {
		if (!isFactorOld(factor)) {
			vfactors.add(factor);
			hasFactor.add(factor);
		}
	}
	
	public boolean isFactorOld(String factor) {
		return hasFactor.contains(factor);
	}
	
	public int matchesCount() {
		return matchedDeseases.size();
	}
	
	public String match() {
		
		return null;
	}
	
	public void clear() {
		
	}
	
}
