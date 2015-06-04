package terap;

import java.util.HashSet;

public class TControl {
	
	HashSet<String> hasFactor = new HashSet<String>();
	
	public void addRecord(String desease, String[] factors) {
		
	}
	
	public String getNextFactor() {
		
		return null;
	}
	
	public void applyAnswer(boolean ans) {
		
	}
	
	public void checkFactor(String factor) {
		if (!isFactorOld(factor)) {
			hasFactor.add(factor);
		}
	}
	
	public boolean isFactorOld(String factor) {
		return hasFactor.contains(factor);
	}
	
	public int matchesCount() {
		
		return 0;
	}
	
	public String match() {
		
		return null;
	}
	
	public void clear() {
		
	}
	
}
