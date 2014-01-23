/* All copyrights reserved, by Yavor Lilyanov */

package com.skrill.interns.DealershipExample;

import com.skrill.interns.DesignPatterns.Dealership;

public class Test {

	public static void main(String[] args) {

		Dealership yavorAutos = new Dealership("Yavor's Auto");
		Interface myInterface = new Interface(yavorAutos);
		myInterface.generalMenu();
		Insertions.closeScanner();

	}

}
