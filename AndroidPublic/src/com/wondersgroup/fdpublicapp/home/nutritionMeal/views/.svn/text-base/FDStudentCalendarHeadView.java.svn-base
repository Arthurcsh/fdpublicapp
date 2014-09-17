package com.wondersgroup.fdpublicapp.home.nutritionMeal.views;

import java.util.Calendar;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

public class FDStudentCalendarHeadView extends View {
	// fields
	private final static int iDayHeaderFontSize = 16;

	// fields
	private Paint pt = new Paint();
	private RectF rect = new RectF();
	private int iWeekDay = -1;
	private boolean bHoliday = false;

	// methods
	public FDStudentCalendarHeadView(Context context, int iWidth, int iHeight) {
		super(context);
		setLayoutParams(new LayoutParams(iWidth, iHeight));
	}

	public void setData(int iWeekDay) {
		this.iWeekDay = iWeekDay;
		this.bHoliday = false;
		if ((iWeekDay == Calendar.SATURDAY) || (iWeekDay == Calendar.SUNDAY))
			this.bHoliday = true;
	}

	private void drawDayHeader(Canvas canvas) {
		if (iWeekDay != -1) {
			// background
			pt.setColor(FDStudentCalendarDayStyle.getColorFrameHeader(bHoliday));
			canvas.drawRect(rect, pt);

			// text
			pt.setTypeface(null);
			pt.setTextSize(iDayHeaderFontSize);
			pt.setAntiAlias(true);
			pt.setFakeBoldText(true);
			pt.setColor(FDStudentCalendarDayStyle.getColorTextHeader(bHoliday, iWeekDay));

			final int iTextPosY = getTextHeight();
			final String sDayName = FDStudentCalendarDayStyle.getWeekDayName(iWeekDay);

			// draw day name
			final int iDayNamePosX = (int) rect.left + ((int) rect.width() >> 1) - ((int) pt.measureText(sDayName) >> 1);
			canvas.drawText(sDayName, iDayNamePosX, rect.top + iTextPosY + 4, pt);
		}
	}

	private int getTextHeight() {
		return (int) (-pt.ascent() + pt.descent());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		rect.set(0, 0, this.getWidth(), this.getHeight());
		rect.inset(1, 1);
		drawDayHeader(canvas);
	}
}
