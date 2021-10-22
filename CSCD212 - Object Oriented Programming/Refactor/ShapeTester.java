import shapes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ShapeTester {
	public static void main(String args[]) {
		ShapeFactory sf = ShapeFactory.getFactory();
		
		Triangle tiny = sf.createTriangle(0.2, 0.1);
		Circle circ = sf.createCircle(1.2);
		Square sq = sf.createSquare(22.2);
		Rectangle rect = sf.createRectangle(2, 3);
		Triangle tri = sf.createTriangle(2,10);
		Rectangle recc = sf.createRectangle(123, 456);
		Triangle abcd = sf.createTriangle(123.212, 66.66);
		Circle circ2 = sf.createCircle(1234.23);
		Square sq2 = sf.createSquare(123.624);
		Square sq3 = sf.createSquare(321.123);
		ArrayList al = new ArrayList<Shapes>();
		al.add(sq2);
		al.add(recc);
		al.add(tri);
		al.add(sq3);
		al.add(circ2);
		al.add(abcd);
		al.add(circ);
		al.add(sq);
		al.add(rect);
		
		al.add(tiny);
		
		System.out.println("## Printing array BEFORE sorting ##");
		printCollection(al);
		
		Collections.sort(al);
		System.out.println("## Printing array AFTER sorting ##");
		printCollection(al);
	}
	
	protected static void printCollection(ArrayList listIn) {
		for(Object shape : listIn) {
			Shapes shapePrint = (Shapes) shape;
			System.out.println(shape.toString());
		}
		System.out.println("\n");
	}
}
