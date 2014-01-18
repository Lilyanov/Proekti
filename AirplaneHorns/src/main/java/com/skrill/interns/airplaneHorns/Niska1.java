/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.airplaneHorns;

import java.util.List;

public class Niska1 implements Runnable{
	
	List<Station> stations;
	int id;
	
	public Niska1(List<Station> stations, int id) {
		this.stations = stations;
		this.id = id;
	}

	
	public void run() {
		while(true) {
			for(int i = 0; i < stations.size(); i++) {
				Station current = stations.get(i);
				synchronized(current) {
					if(current.getPartA() > 0) {
						current.removePartA(3);
						System.out.println("3 A are removed from " + current.name + "from  mashina " + id);
					}
					if(current.getPartB() > 0) {
						current.removePartB(3);
						System.out.println("3 B are removed from " + current.name + "from  mashina " + id);
					}
					if(current.getPartC() > 0) {
						current.removePartC(3);
						System.out.println("3 C are removed from " + current.name + "from  mashina " + id);
					}
					System.out.println("Mashina " + id + " sleep for a while");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Mashina " + id + " wake up");
					//current.locker.unlock();
				}
			}
		}
	}

}