package FitnessTracker.FTProject;

public class SqlInjectionChecker {
	
	public boolean checkString(String str) {
		String matches[] = {";"," "};
		boolean flag = true;
		for (String s : matches) {
			
			  if (str.contains(s)) {
				  flag = false;
			    
			  }

		}
		return flag;

	}
}
	