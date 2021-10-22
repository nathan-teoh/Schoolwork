
public class ComparableDemo {
	
	public void init(Object arr[]) {
		// initialize each array element by instantializing Employee class
		arr[0] = new Employee("Abby", 3000, 1, null, 'f');
		arr[1] = new Employee("John", 2000, 2, (Employee)arr[0], 'm');
		arr[2] = new Employee("Tim", 2000, 2, (Employee)arr[0], 'm');
		arr[3] = new Employee("Tony", 1000, 3, (Employee)arr[0], 'm');
	}
	
	// This method finds the largest object in the array.
	public Object max(Object arr[]) {
		Object largest = arr[0];
		for(Object b : arr) {  //try best to use the enhanced for loop.
			Employee cur = (Employee)b; //We have to type cast b into Employee type, why?
			if (cur.compareTo(largest) > 0) // checking if cur is bigger than the currently largest
				largest = b;
		}
		return largest;	
	}
	
	//This method finds and returns the Employee object in the array whose name equals to the name string
	public Object find(String name, Object arr[]) {
		for(Object b : arr) {  //Please try best to use the enhanced for loop.
			Employee cur = (Employee)b; //We have to type cast b into Employee type, why?
			if(cur.getName().equals(name)){ // this is the correct way to compare string content. Please DO NOT USE == 
				return b;
			}
		}
		return null; // return null, if the target name not found.
	}
	
	// This method finds how many male Employees are in the array
	public int numMale(Object arr[]) {
        int count = 0;
		for(Object b : arr) {
			Employee cur = (Employee) b;
			if(cur.getGender() == 'm') // This is the correct way to compare character variable with character constant.
				count ++;			   // char constant should be enclosed by single quotes.
		}
		return count;
	}
	
	public static void main(String args[]) {
		ComparableDemo demo = new ComparableDemo();
		
		// create one-dimensional array of 4 Java Object references.
		Object arr[] = new Object[4];
		
		// populate the array with Employee objects
		demo.init(arr);
		//call max() method to find the largest object in the array
		Object largest = demo.max(arr);
		System.out.println("The Largest object is:\n" + largest);
		
		//search the name "John"
		Object target = demo.find("John", arr);
		System.out.println("Employee with name John is found:\n" + target);	
		
		//find out how many male employees in the array
		int n = demo.numMale(arr);
		System.out.println("There are " + n + " male employees in the array.");
	
		//Demo boss fields pointing to different person
		Employee person = (Employee) arr[1];
		((Employee)arr[2]).boss = person;	
		System.out.println("Boss of Tim is changed:");
		System.out.println((Employee)arr[2]);
	}
}

class Employee implements Comparable {
	private String name;
	private int wage;
	private int rank;
	private char gender; //either 'f' or 'm'
	public Employee boss; //here just try to demo public modifier, 
						  //Make boss public may negatively affect Object-Oriented encapsulation.
	
	
	public Employee(String n, int w, int r, Employee b, char g) {
		this.name = n; this.wage = w; 
		this.rank = r; this.boss = b;
		this.gender = g;
	}
	
	@Override
	//This method describes how we would like to compare two Employee objects.
	//Should we compare two such objects based on rank or wage?
	//It is up to the programmer to decide how the comparison will be made.
	//comareTo(Object other) method will compare this object with other object you passed in.
	//if this object is bigger than the other, it returns a positive number.
	//if this object is smaller than the other, it returns a negative number.
	//if this object equals to the other, it returns zero.
	public int compareTo(Object other) {
		//here comparison is based on Employee's wage
		Employee another = (Employee) other;
		return this.wage - another.wage; //this can be replaced with a if-else statement. How?
	}
	public String getName() {
		return this.name;
	}
	public char getGender() {
		return this.gender;
	}
	@Override
	public String toString() {
		String r = "";
		r += "Name:" + this.name + ",Wage:" + 
				this.wage + ",Rank:" + this.rank + 
				",Gendar:" + this.gender +
				",Boss:{" + this.boss + "}";
		return r;
	}
}