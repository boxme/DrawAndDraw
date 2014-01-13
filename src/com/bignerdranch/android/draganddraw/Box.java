package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

/*
 * To keep track of the data from more than one MotionEvent to create a box
 */
public class Box {
	private PointF mOrigin;
	private PointF mCurrent;
	
	public Box(PointF origin) {
		mOrigin = mCurrent = origin;								//Starting coord of the box
	}
	
	public void setCurrent(PointF current) {						//Change the current coord 
		mCurrent = current;
	}
	
	public PointF getOrigin() {
		return mOrigin;
	}
	
	public PointF getCurrent() {
		return mCurrent;
	}
}
