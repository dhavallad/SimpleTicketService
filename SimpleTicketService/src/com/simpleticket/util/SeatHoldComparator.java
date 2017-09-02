/**
 * 
 */
package com.simpleticket.util;

import java.util.Comparator;

import com.simpleticket.model.SeatHold;

/**
 * @author dhavallad
 *
 */
public class SeatHoldComparator implements Comparator<SeatHold> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(SeatHold o1, SeatHold o2) {
		// TODO Auto-generated method stub
		if (o1.getTimeHeld() == o2.getTimeHeld()) {
			return 0;
		} else {
			return o1.getTimeHeld() < o2.getTimeHeld() ? -1 : 1;
		}

	}

}
