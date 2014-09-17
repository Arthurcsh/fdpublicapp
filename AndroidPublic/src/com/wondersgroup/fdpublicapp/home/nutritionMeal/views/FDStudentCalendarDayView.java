package com.wondersgroup.fdpublicapp.home.nutritionMeal.views;

import java.util.Calendar;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.home.nutritionMeal.views.FDStudentCalendarView.NutritionMealCalendarInterface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout.LayoutParams;

/**
 *  营养餐日历单元
 * @author chengshaohua
 *
 */
public class FDStudentCalendarDayView extends View {
	public interface OnItemClick {
		public void OnClick(FDStudentCalendarDayView item);
	}

	private OnItemClick itemClick = null;
	private Activity context;

	private final static float fTextSize = 22;
	private final static int iMargin = 1;
	private final static int iAlphaInactiveMonth = 0x88;
	public static int ANIM_ALPHA_DURATION = 100;

	private int iDateYear = 0;
	private int iDateMonth = 0;
	private int iDateDay = 0;
	private int iDayOfWeek = 0;

	private Paint pt = new Paint();
	private RectF rect = new RectF();
	private String sDate = "";

	private boolean bSelected = false;
	private boolean bIsActiveMonth = false;
	private boolean bToday = false;
	private boolean bHoliday = false;
	private boolean bTouchedDown = false;
	private boolean hasMeals = false;
	private String mealsDate;
	private NutritionMealCalendarInterface nutritionMealCalendarListener;
	
	public FDStudentCalendarDayView(Context context, int iWidth, int iHeight) {
		super(context);
		this.context = (Activity) context;
		setFocusable(true);
		setLayoutParams(new LayoutParams(iWidth, iHeight));
	}

	public boolean isSelected() {
		return this.bSelected;
	}

	public void setSelected(boolean bEnable) {
		if (this.bSelected != bEnable) {
			this.bSelected = bEnable;
			this.invalidate();
		}
	}

	public void setData(int iYear, int iMonth, int iDay, boolean bToday, boolean bHoliday, int iActiveMonth, int iDayOfWeek) {
		iDateYear = iYear;
		iDateMonth = iMonth;
		iDateDay = iDay;

		this.sDate = Integer.toString(iDateDay);
		this.bIsActiveMonth = (iDateMonth == iActiveMonth);
		this.bToday = bToday;
		this.bHoliday = bHoliday;
		this.iDayOfWeek = iDayOfWeek;
	}

	public void setItemClick(OnItemClick itemClick) {
		this.itemClick = itemClick;
	}

	private int getTextHeight() {
		return (int) (-pt.ascent() + pt.descent());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean bResult = super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
			doItemClick();
		}
		return bResult;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean bResult = super.onKeyUp(keyCode, event);
		return bResult;
	}

	public void doItemClick() {
		if (itemClick != null) {
			itemClick.OnClick(this);
			if (hasMeals) {
				if(nutritionMealCalendarListener!=null) {
					nutritionMealCalendarListener.onCalendarSelected(mealsDate, getDate());
				}
			}
		}
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		invalidate();
	}

	public Calendar getDate() {
		Calendar calDate = Calendar.getInstance();
		calDate.clear();
		calDate.set(Calendar.YEAR, iDateYear);
		calDate.set(Calendar.MONTH, iDateMonth);
		calDate.set(Calendar.DAY_OF_MONTH, iDateDay);
		return calDate;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// init rectangles
		rect.set(0, 0, this.getWidth(), this.getHeight());
		rect.inset(1, 1);

		// drawing
		final boolean bFocused = IsViewFocused();

		drawDayView(canvas, bFocused);
		if (bIsActiveMonth) {
			drawImageAndDayNumber(canvas, bFocused);
		}
	}

	private void drawDayView(Canvas canvas, boolean bFocused) {
		if (bSelected || bFocused) {
			LinearGradient lGradBkg = null;

			if (bFocused) {
				lGradBkg = new LinearGradient(rect.left, 0, rect.right, 0, FDStudentCalendarDayStyle.iColorBkgFocusDark,
						FDStudentCalendarDayStyle.iColorBkgFocusLight, Shader.TileMode.CLAMP);
			}

			if (bSelected) {
				lGradBkg = new LinearGradient(rect.left, 0, rect.right, 0, FDStudentCalendarDayStyle.iColorBkgSelectedDark,
						FDStudentCalendarDayStyle.iColorBkgSelectedLight, Shader.TileMode.CLAMP);
			}

			if (lGradBkg != null) {
				pt.setShader(lGradBkg);
				canvas.drawRect(rect, pt);
			}
			pt.setShader(null);
		} else {
			pt.setColor(FDStudentCalendarDayStyle.getColorBkg(bHoliday, bToday));
			// if (bIsActiveMonth)
			pt.setAlpha(iAlphaInactiveMonth);
			canvas.drawRect(rect, pt);
		}
	}

	public void drawImageAndDayNumber(Canvas canvas, boolean bFocused) {
		// draw day number
		pt.setTypeface(null);
		pt.setAntiAlias(true);
		pt.setShader(null);
		pt.setFakeBoldText(true);
		pt.setTextSize(fTextSize);

		pt.setUnderlineText(false);
		if (bToday)
			pt.setUnderlineText(true);

		int iTextPosX = (int) rect.right - (int) pt.measureText(sDate);
		int iTextPosY = (int) rect.bottom + (int) (-pt.ascent()) - getTextHeight();

		iTextPosX -= ((int) rect.width() >> 1) - ((int) pt.measureText(sDate) >> 1);
		iTextPosY -= ((int) rect.height() >> 1) - (getTextHeight() >> 1);

		// 有营养餐，画出图标
		if (hasMeals && !bHoliday && bIsActiveMonth) {
			Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.nutrition_meals);
			pt.setColor(Color.RED);
			canvas.drawBitmap(b, iDateDay < 10 ? iTextPosX - 5 : iTextPosX, iTextPosY - 28, pt);
		}

		// 再画出日期
		iTextPosY += 20;
		// draw text
		if (bSelected || bFocused) {
			if (bSelected)
				pt.setColor(FDStudentCalendarDayStyle.iColorTextSelected);
			if (bFocused)
				pt.setColor(FDStudentCalendarDayStyle.iColorTextFocused);
		} else {
			pt.setColor(FDStudentCalendarDayStyle.getColorText(bHoliday, bToday, iDayOfWeek));
		}

		// if (bIsActiveMonth)
		pt.setAlpha(iAlphaInactiveMonth);

		canvas.drawText(sDate, iTextPosX, iTextPosY + iMargin, pt);

		pt.setUnderlineText(false);
	}

	public boolean IsViewFocused() {
		return (this.isFocused() || bTouchedDown);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean bHandled = false;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			bHandled = true;
			bTouchedDown = true;
			invalidate();
			startAlphaAnimIn(FDStudentCalendarDayView.this);
		}
		if (event.getAction() == MotionEvent.ACTION_CANCEL) {
			bHandled = true;
			bTouchedDown = false;
			invalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			bHandled = true;
			bTouchedDown = false;
			invalidate();
			doItemClick();
		}
		return bHandled;
	}

	public static void startAlphaAnimIn(View view) {
		AlphaAnimation anim = new AlphaAnimation(0.5F, 1);
		anim.setDuration(ANIM_ALPHA_DURATION);
		anim.startNow();
		view.startAnimation(anim);
	}

	public int getiDateMonth() {
		return iDateMonth;
	}

	public void setiDateMonth(int iDateMonth) {
		this.iDateMonth = iDateMonth;
	}

	public boolean isHasMeals() {
		return hasMeals;
	}

	public void setHasMeals(boolean hasMeals) {
		this.hasMeals = hasMeals;
	}
	
	public void setHasMeals(boolean hasMeals, String indexDate) {
		this.hasMeals = hasMeals;
		this.mealsDate = indexDate;
	}
	
	public void setNutritionMealCalendarListener(NutritionMealCalendarInterface calendarListener) {
		this.nutritionMealCalendarListener = calendarListener;
	}
}

class FDStudentCalendarDayStyle {
	// methods
	private static String[] getWeekDayNames() {
		String[] vec = new String[10];

		vec[Calendar.SUNDAY] = "日";
		vec[Calendar.MONDAY] = "一";
		vec[Calendar.TUESDAY] = "二";
		vec[Calendar.WEDNESDAY] = "三";
		vec[Calendar.THURSDAY] = "四";
		vec[Calendar.FRIDAY] = "五";
		vec[Calendar.SATURDAY] = "六";
		return vec;
	}

	public static String getWeekDayName(int iDay) {
		return vecStrWeekDayNames[iDay];
	}

	// fields
	private final static String[] vecStrWeekDayNames = getWeekDayNames();

	// fields
	public final static int iColorFrameHeader = 0xff666666;
	public final static int iColorFrameHeaderHoliday = 0xff707070;
	public final static int iColorTextHeader = 0xffcccccc;

	public final static int iColorText = 0xffdddddd;
	public final static int iColorBkg = 0xff888888;
	public final static int iColorBkgHoliday = 0xffaaaaaa;

	public final static int iColorTextToday = 0xff002200;
	public final static int iColorBkgToday = 0xff88bb88; // 0xff88bb88;  0xFF34B3

	public final static int iColorTextSelected = 0xff001122;
	public final static int iColorBkgSelectedLight = 0xffbbddff;
	public final static int iColorBkgSelectedDark = 0xff225599;

	public final static int iColorTextFocused = 0xff221100;
	public final static int iColorBkgFocusLight = 0xffffddbb;
	public final static int iColorBkgFocusDark = 0xffaa5500;

	// methods
	public static int getColorFrameHeader(boolean bHoliday) {
		if (bHoliday) {
			return iColorFrameHeaderHoliday;
		}
		return iColorFrameHeader;
	}

	public static int getColorTextHeader(boolean bHoliday, int iWeekDay) {
		if (bHoliday) {
			return Color.GRAY;
		}
		return iColorTextHeader;
	}

	public static int getColorText(boolean bHoliday, boolean bToday, int iDayOfWeek) {
		if (bToday)
			return iColorTextToday;
		if (bHoliday) {
			return Color.GRAY;
		}
		return iColorTextSelected; // iColorText;
	}

	public static int getColorBkg(boolean bHoliday, boolean bToday) {
		if (bToday)
			return iColorBkgToday;
		if (bHoliday)
			return iColorBkgHoliday;
		return iColorBkg;
	}

	public static int getWeekDay(int index, int iFirstDayOfWeek) {
		int iWeekDay = -1;
		if (iFirstDayOfWeek == Calendar.MONDAY) {
			iWeekDay = index + Calendar.MONDAY;
			if (iWeekDay > Calendar.SATURDAY)
				iWeekDay = Calendar.SUNDAY;
		}
		if (iFirstDayOfWeek == Calendar.SUNDAY) {
			iWeekDay = index + Calendar.SUNDAY;
		}
		return iWeekDay;
	}
	
}
