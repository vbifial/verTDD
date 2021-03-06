package terap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Vector;

public class TControl {
	
	HashSet<String> hasFactor = new HashSet<String>();
	TreeSet<String> matchedDiseases = new TreeSet<String>();
	Vector<String> diseases = new Vector<String>();
	Vector<String> vfactors = new Vector<String>();
	HashMap<String, HashSet<String>> records = new HashMap<String, HashSet<String>>();
	int curFactor = -1;
	
	public void addRecord(String disease, String[] factors) {
		diseases.add(disease);
		matchedDiseases.add(disease);
		HashSet<String> set = new HashSet<String>();
		records.put(disease, set);
		
		for (int i = 0; i < factors.length; i++) {
			checkFactor(factors[i]);
			set.add(factors[i]);
		}
	}
	
	public String getNextFactor() {
		curFactor++;
		if (curFactor < vfactors.size())
			return vfactors.get(curFactor);
		return null;
	}
	
	public void applyAnswer(boolean ans) {
		if (matchesCount() == 0)
			return;
		String factor = vfactors.get(curFactor);
		String cur = matchedDiseases.first();
		while (cur != null) {
			String next = matchedDiseases.higher(cur);
			HashSet<String> set = records.get(cur);
			if (set.contains(factor) != ans)
				matchedDiseases.remove(cur);
			cur = next;
		}
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
		return matchedDiseases.size();
	}
	
	public String match() {
		if (matchesCount() == 1)
			return matchedDiseases.first();
		return null;
	}
	
	public void clear() {
		
	}
	
}
