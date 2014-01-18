/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.airplaneHorns;

import java.util.ArrayList;
import java.util.List;

public class Example {
	private static List<Station> stations = new ArrayList<Station>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 for (int i = 0; i < 2; i++) {
	            stations.add(new Station("Station " + i));
	     }
		 
		 Thread t1 = new Thread(new Nishka(stations));
		 Thread t2 = new Thread(new Nishka(stations));
		 t1.start();
		 t2.start();
		 
		 Thread m1 = new Thread(new Niska1(stations, 1));
		 Thread m2 = new Thread(new Niska1(stations, 2));
		 m1.start();
		 m2.start();

	}

}
