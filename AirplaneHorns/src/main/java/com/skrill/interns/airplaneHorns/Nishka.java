/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.airplaneHorns;

import java.util.List;

public class Nishka implements Runnable{
	
	List<Station> stations;
	
	public Nishka(List<Station> stations) {
		this.stations = stations;
	}

	
		public void run() {
			while(true) {
				for(int i = 0; i < stations.size(); i++) {
					Station current = stations.get(i);
					if(current.locker.tryLock()) {
						current.addPartA(10);
						current.addPartB(15);
						current.addPartC(20);
						System.out.println(current.getPartA() + " goes in " + current.name);
						System.out.println(current.getPartB() + " goes in " + current.name);
						System.out.println(current.getPartC() + " goes in " + current.name);
						System.out.println("Loading...");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Loading END");
						
						current.locker.unlock();
					}
				}
			}
		}

}
