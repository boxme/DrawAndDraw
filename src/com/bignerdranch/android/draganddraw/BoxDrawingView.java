package com.bignerdranch.android.draganddraw;

/*
 * A custom view 
 */
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoxDrawingView extends View {
	public static final String TAG = "BoxDrawingView";
	private Box mCurrentBox;
	private ArrayList<Box> mBoxes = new ArrayList<Box>();
	private Paint mBoxPaint;
	private Paint mBackgroundPaint;
	
	// Used when creating the view in code
	public BoxDrawingView(Context context) {
		this(context, null);
	}
	
	// Used when inflating the view from XML
	public BoxDrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// Paint the boxes a nice semi-transparent red (ARGB)
		mBoxPaint = new Paint();
		mBoxPaint.setColor(0x22ff0000);									
		
		// Paint the background off-white
		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(0xfff8efe0);
	}
	
	//Called everytime a touch event happens
	public boolean onTouchEvent(MotionEvent event) {					//MotionEvent is a class that describes the touch event
		PointF curr = new PointF(event.getX(), event.getY());			//PointF is android graphics
		
//		Log.i(TAG, "Received event at x=" + curr.x + ", y=" + curr.y + ":");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mCurrentBox = new Box(curr);								//Reset drawing state
			mBoxes.add(mCurrentBox);
			break;
		case MotionEvent.ACTION_MOVE:
			if (mCurrentBox != null) {
				mCurrentBox.setCurrent(curr);							//Updates as the fingers move around the screen
				invalidate();											//Forces BoxDrawingView to redraw itself so the users can see
			}
			break;
		case MotionEvent.ACTION_UP:
			mCurrentBox = null;											//User's fingers leave the screen
			break;
		case MotionEvent.ACTION_CANCEL:
			mCurrentBox = null;
			break;
		}
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//Fill the background
		canvas.drawPaint(mBackgroundPaint);
		
		for (Box box : mBoxes) {												//Draw every box 
			float left = Math.min(box.getOrigin().x, box.getCurrent().x);
			float right = Math.max(box.getOrigin().x, box.getCurrent().x);
			float top = Math.min(box.getOrigin().y, box.getCurrent().y);
			float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
			
			canvas.drawRect(left, top, right, bottom, mBoxPaint);					
		}
	}
	
	@Override
	public Parcelable onSaveInstanceState() {									//Saving state of custom view
		Bundle bundle = new Bundle();
		bundle.putParcelable("instanceState", super.onSaveInstanceState());
		bundle.putSerializable("ArrayListBox", mBoxes);
		return bundle;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			this.mBoxes = (ArrayList<Box>) bundle.getSerializable("ArrayListBox");
			state = bundle.getParcelable("instanceState");
		}
		super.onRestoreInstanceState(state);
	}
}
