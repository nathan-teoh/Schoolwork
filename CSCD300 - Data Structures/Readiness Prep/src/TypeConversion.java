public class TypeConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String intStr = "6780";
		int a = 409;
		//char c = 'a';
		char c2 = '6';
		
		//convert string to numerical
		int num = Integer.parseInt(intStr);
		System.out.println("The numerical value for string " + intStr + " is " + num);
		
		//convert numerical value to string
		String intStr2 = "" + a;
		System.out.println("The string for the numerical value " + a + " is " + intStr2);

		//convert char to numerical value
		// int ic2 = (int) c2; this does not work.
		int ic2 = c2 - '0'; // Integer.parseInt("" + c2);
		System.out.println("The the numerical value for char \'" + c2 + "\' is " + ic2);
		
		//visit each digit in an integer value
		int v = 456780;
		String strV = "" + v;
		for(char c3 : strV.toCharArray()) {
			System.out.print((c3 - '0') + ",");
		}		
	}

}
