import java.util.Arrays;

public class StringUtils {
	
	public static void main(String[] args){
		String str1 = deleteSubString ("s. I am always ","I have the simplest tastes. I am always satisfied with the best");
		String str2 = mergeStrings ("abcdefg","bcgfhi");
		String str3 = sortStringWords("To Be Or Not To Be");
		System.out.println(str1);
	}
	
	public static String sortStringWords (String str){
		String[] strArr = str.split(" ");
		Arrays.sort(strArr);
		int n = strArr.length;
		String sol = "";
		for (int i=0; i<n;i++){
			sol += strArr[n-1-i];
			if(i!=n-1){
				sol +=" ";
			}
		}
		return sol;
	}
	
	public static String deleteSubString(String sub, String s){
		if(!s.contains(sub))
			return s;
		int subLen = sub.length();
		int subIdx = s.indexOf(sub);
		String sub1 = s.substring(0,subIdx);
		String sub2 = s.substring(subIdx+subLen);
		return sub1+sub2;	
	}
	public static String mergeStrings(String a, String b){
		String[] arr = a.split("");
		String sol = "";
		for(String chr: arr){
			if(b.contains(chr)){
				sol += chr;
			}
		}
		return sol;
	}

}
