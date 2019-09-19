import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
//		String pattern = "text=\"[^\"]*\"";
//		String linija = "text=\"Ja sam Marijana\"";
//		Pattern myPattern = Pattern.compile(pattern);
//		Matcher matcher = myPattern.matcher(linija);
//		List<String> pronadjeno = new ArrayList<>();
//		
//		while (matcher.find()) {
//		    pronadjeno.add(matcher.group());
//		}
//
//		for(String recenica : pronadjeno) {
//			System.out.println(recenica);
//		}
		
		String s = "Hej?\"";
		if(s.endsWith("\\?\""))
			System.out.println(true);
		else
			System.out.println(false);
				
		
	}
	
	
}
