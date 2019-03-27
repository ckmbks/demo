package ckmbks.framework.date;

import java.util.Date;

import ckmbks.framework.date.DateField;
import ckmbks.framework.date.DateTime;
import ckmbks.framework.date.DateUtil;
import ckmbks.framework.lang.Range;

/**
 * 日期范围
 * 
 * @author looly
 * @since 4.1.0
 */
public class DateRange extends Range<ckmbks.framework.date.DateTime> {

	/**
	 * 构造，包含开始和结束日期时间
	 * 
	 * @param start 起始日期时间
	 * @param end 结束日期时间
	 * @param unit 步进单位
	 */
	public DateRange(Date start, Date end, final ckmbks.framework.date.DateField unit) {
		this(start, end, unit, 1);
	}

	/**
	 * 构造，包含开始和结束日期时间
	 * 
	 * @param start 起始日期时间
	 * @param end 结束日期时间
	 * @param unit 步进单位
	 * @param step 步进数
	 */
	public DateRange(Date start, Date end, final ckmbks.framework.date.DateField unit, final int step) {
		this(start, end, unit, step, true, true);
	}

	/**
	 * 构造
	 * 
	 * @param start 起始日期时间
	 * @param end 结束日期时间
	 * @param unit 步进单位
	 * @param step 步进数
	 * @param isIncludeStart 是否包含开始的时间
	 * @param isIncludeEnd 是否包含结束的时间
	 */
	public DateRange(Date start, Date end, final DateField unit, final int step, boolean isIncludeStart, boolean isIncludeEnd) {
		super(ckmbks.framework.date.DateUtil.date(start), DateUtil.date(end), new Steper<ckmbks.framework.date.DateTime>() {

			@Override
			public ckmbks.framework.date.DateTime step(ckmbks.framework.date.DateTime current, ckmbks.framework.date.DateTime end, int index) {
				DateTime dt = current.offsetNew(unit, step);
				if (dt.isAfter(end)) {
					return null;
				}
				return current.offsetNew(unit, step);
			}
		}, isIncludeStart, isIncludeEnd);
	}

}
