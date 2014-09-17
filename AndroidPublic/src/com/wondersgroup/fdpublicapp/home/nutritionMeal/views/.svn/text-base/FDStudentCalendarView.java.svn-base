package com.wondersgroup.fdpublicapp.home.nutritionMeal.views;

import java.util.ArrayList;
import java.util.Calendar;
import com.wondersgroup.fdpublicapp.R;
import com.wondersgroup.fdpublicapp.common.util.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *  营养餐学校菜单日历组件
 * @author chengshaohua
 *
 */
public class FDStudentCalendarView extends LinearLayout {
	private Activity context;

	private ArrayList<FDStudentCalendarDayView> dayCellViews = new ArrayList<FDStudentCalendarDayView>();
	private Calendar calStartDate = Calendar.getInstance();
	private Calendar calToday = Calendar.getInstance();
	private Calendar calCalendar = Calendar.getInstance();
	private Calendar calSelected = Calendar.getInstance();

	private LinearLayout layContent, layLastMonth, layNextMonth;
	private int iFirstDayOfWeek = Calendar.SUNDAY;
	private int iMonthViewCurrentMonth = 0;
	private int iMonthViewCurrentYear = 0;
	public static final int SELECT_DATE_REQUEST = 111;
	public static final int MIN_CELL_SIZE = 60;
	private static int iDayCellSize = MIN_CELL_SIZE;
	private static final int iDayHeaderHeight = 40;
	private TextView yearMonthTextView;
	private NutritionMealCalendarInterface nutritionMealCalendarListener;       // 切换时间回调
	private ArrayList<String> mealDays;                                         // 学校营养餐日
	
	public FDStudentCalendarView(Context context) {
		super(context);
		this.context = (Activity) context;
		
		initCalendarView();
	}

	public FDStudentCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = (Activity) context;
		
		initCalendarView();
	}

	public void initCalendarView() {
		iFirstDayOfWeek = Calendar.SUNDAY;

		// 设置单个日期格的宽度
		DisplayMetrics dm = new DisplayMetrics();
		// 取得窗口属性
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 单格的宽度最小不能小于 MIN_CELL_SIZE
		 iDayCellSize = (dm.widthPixels - 40)/7;
		 if (iDayCellSize < MIN_CELL_SIZE) {
			 iDayCellSize = MIN_CELL_SIZE;
		 }

		// 添加标题部分
		final View calendarTile = LayoutInflater.from(context).inflate(R.layout.fd_student_calendar_title, null);
		addView(calendarTile);
		yearMonthTextView = (TextView) calendarTile.findViewById(R.id.fd_student_calendar_year_month_text);
		layLastMonth = (LinearLayout)calendarTile.findViewById(R.id.fd_student_calendar_last_month);
		layNextMonth = (LinearLayout)calendarTile.findViewById(R.id.fd_student_calendar_next_month);
		// 添加日历体部分
		addView(generateContentView());

		calStartDate = getCalendarStartDate();
		showYesrMonthInTitle(iMonthViewCurrentMonth, iMonthViewCurrentYear);
		updateCalendar();
		
		layLastMonth.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				mealDays = null;           // 清除餐次
				setPrevMonthViewItem();
			}
		});
		layNextMonth.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				mealDays = null;
				setNextMonthViewItem();
			}
		});
	}

	private View generateContentView() {
		LinearLayout layMain = createLayout(LinearLayout.VERTICAL,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		layContent = createLayout(LinearLayout.VERTICAL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		generateCalendar(layContent);
		layMain.addView(layContent);

		return layMain;
	}

	private LinearLayout createLayout(final int iOrientation, final int widthGravy, final int heihtGravy) {
		LinearLayout lay = new LinearLayout(context);
		lay.setLayoutParams(new LayoutParams(widthGravy, heihtGravy));
		lay.setOrientation(iOrientation);
		return lay;
	}

	private void generateCalendar(LinearLayout layContent) {
		layContent.addView(generateCalendarHeader());
		dayCellViews.clear();
		for (int iRow = 0; iRow < 5; iRow++) {
			layContent.addView(generateCalendarRow());
		}
	}

	private View generateCalendarRow() {
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		for (int iDay = 0; iDay < 7; iDay++) {
			FDStudentCalendarDayView dayCell = new FDStudentCalendarDayView(context, iDayCellSize, iDayCellSize);
			dayCell.setItemClick(mOnDayCellClick);
			dayCellViews.add(dayCell);
			layRow.addView(dayCell);
		}
		return layRow;
	}

	private View generateCalendarHeader() {
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL,LinearLayout.LayoutParams.MATCH_PARENT, iDayHeaderHeight);
		for (int iDay = 0; iDay < 7; iDay++) {
			FDStudentCalendarHeadView day = new FDStudentCalendarHeadView(context, iDayCellSize, iDayHeaderHeight);
			final int iWeekDay = FDStudentCalendarDayStyle.getWeekDay(iDay, iFirstDayOfWeek);
			day.setData(iWeekDay);
			layRow.addView(day);
		}
		return layRow;
	}

	private Calendar getCalendarStartDate() {
		calToday.setTimeInMillis(System.currentTimeMillis());
		calToday.setFirstDayOfWeek(iFirstDayOfWeek);

		if (calSelected.getTimeInMillis() == 0) {
			calStartDate.setTimeInMillis(System.currentTimeMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		} else {
			calStartDate.setTimeInMillis(calSelected.getTimeInMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		}
		updateStartDateForMonth();

		return calStartDate;
	}

	private FDStudentCalendarDayView updateCalendar() {
		FDStudentCalendarDayView daySelected = null;
		boolean bSelected = false;
		final boolean bIsSelection = (calSelected.getTimeInMillis() != 0);
		final int iSelectedYear = calSelected.get(Calendar.YEAR);
		final int iSelectedMonth = calSelected.get(Calendar.MONTH);
		final int iSelectedDay = calSelected.get(Calendar.DAY_OF_MONTH);
		calCalendar.setTimeInMillis(calStartDate.getTimeInMillis());
		for (int i = 0; i < dayCellViews.size(); i++) {
			final int iYear = calCalendar.get(Calendar.YEAR);
			final int iMonth = calCalendar.get(Calendar.MONTH);
			final int iDay = calCalendar.get(Calendar.DAY_OF_MONTH);
			final int iDayOfWeek = calCalendar.get(Calendar.DAY_OF_WEEK);
			FDStudentCalendarDayView dayCell = dayCellViews.get(i);
			if(nutritionMealCalendarListener!=null) {
				dayCell.setNutritionMealCalendarListener(nutritionMealCalendarListener);
			}
			// check today
			boolean bToday = false;
			if (calToday.get(Calendar.YEAR) == iYear)
				if (calToday.get(Calendar.MONTH) == iMonth)
					if (calToday.get(Calendar.DAY_OF_MONTH) == iDay)
						bToday = true;
			// check holiday
			boolean bHoliday = false;
			if ((iDayOfWeek == Calendar.SATURDAY) || (iDayOfWeek == Calendar.SUNDAY))
				bHoliday = true;
			if ((iMonth == Calendar.JANUARY) && (iDay == 1))
				bHoliday = true;

			dayCell.setData(iYear, iMonth, iDay, bToday, bHoliday, iMonthViewCurrentMonth, iDayOfWeek);
			
			/** 设置营养餐日 **/
			dayCell.setHasMeals(false);
			if(mealDays!=null) {
				for(int n=0;n<mealDays.size();n++) {
					String mealDate = mealDays.get(n);
					if(mealDate==null) continue;
					
					if(StringUtils.toDate2(mealDate).getDate()==iDay) {
						dayCell.setHasMeals(true, mealDate);
						break;
					}
				}
			}
			
			bSelected = false;
			if (bIsSelection)
				if ((iSelectedDay == iDay) && (iSelectedMonth == iMonth) && (iSelectedYear == iYear)) {
					bSelected = true;
				}
			dayCell.setSelected(bSelected);
			if (bSelected)
				daySelected = dayCell;
			calCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		layContent.invalidate();
		return daySelected;
	}

	private void updateStartDateForMonth() {
		iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);
		iMonthViewCurrentYear = calStartDate.get(Calendar.YEAR);
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		// update days for week
		int iDay = 0;
		int iStartDay = iFirstDayOfWeek;
		if (iStartDay == Calendar.MONDAY) {
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
			if (iDay < 0)
				iDay = 6;
		}
		if (iStartDay == Calendar.SUNDAY) {
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			if (iDay < 0)
				iDay = 6;
		}
		calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);
	}

	private void setPrevMonthViewItem() {
		iMonthViewCurrentMonth--;
		if (iMonthViewCurrentMonth == -1) {
			iMonthViewCurrentMonth = 11;
			iMonthViewCurrentYear--;
		}
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
		calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
		updateDate();
		showYesrMonthInTitle(iMonthViewCurrentMonth, iMonthViewCurrentYear);
		
		setChangedCalendar();
	}

	private void setNextMonthViewItem() {
		iMonthViewCurrentMonth++;
		if (iMonthViewCurrentMonth == 12) {
			iMonthViewCurrentMonth = 0;
			iMonthViewCurrentYear++;
		}
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
		calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
		updateDate();
		showYesrMonthInTitle(iMonthViewCurrentMonth, iMonthViewCurrentYear);
		
		setChangedCalendar();
	}

	/**
	 *  日历时间改变时监听
	 */
	private void setChangedCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(iMonthViewCurrentYear, iMonthViewCurrentMonth, calToday.get(Calendar.DAY_OF_MONTH));
		if(nutritionMealCalendarListener!=null) {
			nutritionMealCalendarListener.onChangeMonthMeal(calendar);
		}
	}
	
	private FDStudentCalendarDayView.OnItemClick mOnDayCellClick = new FDStudentCalendarDayView.OnItemClick() {
		public void OnClick(FDStudentCalendarDayView item) {
			calSelected.setTimeInMillis(item.getDate().getTimeInMillis());
			item.setSelected(true);
			updateCalendar();
		}
	};

	private void showYesrMonthInTitle(int iMonthViewCurrentMonth, int iMonthViewCurrentYear) {
		StringBuffer buf = new StringBuffer();
		buf.append(iMonthViewCurrentYear);
		buf.append("年");
		buf.append(format(iMonthViewCurrentMonth + 1));
		buf.append("月");
		yearMonthTextView.setText(buf.toString());
	}

	private void updateDate() {
		updateStartDateForMonth();
		updateCalendar();
	}

	private String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}
	
	public interface NutritionMealCalendarInterface {
		public void onCalendarSelected(String indexDate, Calendar calendar);
		public void onChangeMonthMeal(Calendar calendar);
	}
	public void setNutritionMealCalendarListener(NutritionMealCalendarInterface calendarListener) {
		this.nutritionMealCalendarListener = calendarListener;
	}
	
	public ArrayList<String> getMealDays() {
		return mealDays;
	}

	public void setMealDays(ArrayList<String> mealDays) {
		this.mealDays = mealDays;
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<mealDays.size();i++) {
			builder.append(" "+mealDays.get(i));
		}
		System.out.println(builder);
		updateCalendar();
	}
}
