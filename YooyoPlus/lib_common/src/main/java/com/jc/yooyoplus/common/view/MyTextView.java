package com.jc.yooyoplus.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jc.yooyoplus.common.CommonUtils;
import com.jc.yooyoplus.common.R;

/**
 * 继承TextView,设置字体样式yooyoico.ttf,字体图标
 * @author jackie
 * @date 创建时间：2015-10-9 下午3:26:09 
 *
 */
@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {
	
	/**绘制textview的时候，底部起始点*/
	private float bootomY_ = 0f;
	private int height;
	
	private boolean redPoint = false;
	private Paint pointPaint;
	private int radius;
	
	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
//		initView(context);
		initXml(context, attrs);
	}
	
	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
//		initView(context);
		initXml(context, attrs);
	}

	private void initView(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/fontawesome-webfont.ttf"));
	}
	
	private void initXml(Context context, AttributeSet attrs){
		if (isInEditMode()) {
			return;
		}
		setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/fontawesome-webfont.ttf"));
		TypedArray arr = getContext().obtainStyledAttributes(attrs,
				R.styleable.MyTextVIew);
		if (arr == null) {
			return;
		}
		try {
			String bootomY = arr.getString(R.styleable.MyTextVIew_bootomY);
			if (!TextUtils.isEmpty(bootomY)) {
				bootomY_ = Float.valueOf(bootomY);
			}
			redPoint = arr.getBoolean(R.styleable.MyTextVIew_redPoint, false);
		} finally {
			// TODO: handle finally clause
			arr.recycle();
		}
		
		
		if(redPoint) {
			initPoint();
		}
	}
	
	/**
	 * 初始化圆点画笔、半径
	 */
	private void initPoint(){
		pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		pointPaint.setColor(getResources().getColor(R.color.red));
		pointPaint.setTextAlign(Paint.Align.CENTER);
		radius = CommonUtils.dip2px(getContext(), 4);
	}

	public void setRedPoint(boolean isNeed) {
		this.redPoint = isNeed;
		if(isNeed){
			initPoint();
		}
		postInvalidate();
	}
	
	private boolean resetWidth;
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (bootomY_ <= 0f) {
			super.onDraw(canvas);
		}else {
			height = getHeight();
			TextPaint paint = getPaint();
			paint.setTextSize(getTextSize());
			paint.setAntiAlias(true);
			ColorStateList list = getTextColors();
			paint.setColor(list.getDefaultColor());
			if (getText() != null) {
				canvas.drawText(getText().toString(), 0, getHeight()+bootomY_*2, paint);
			}
		}
		
		if(redPoint){
			String text = getText().toString();
			Drawable[] drawable = getCompoundDrawables();
			if(text.length() > 0 && drawable[0] == null && drawable[1] == null && drawable[2] == null && drawable[3] == null){
				Rect mTextBound = new Rect();
				TextPaint paint = getPaint();
				paint.getTextBounds(text, 0, text.length(), mTextBound);
				
				int viewWidth = getMeasuredWidth();
				
				int textRight = getPaddingLeft() + mTextBound.width();
				if((textRight+radius*3) > (viewWidth - getPaddingRight()) && !resetWidth){
					setWidth(viewWidth + radius*3);
					resetWidth = true;
				}
				canvas.drawCircle(textRight+radius*2, 
						(getMeasuredHeight() - mTextBound.height())/2 + radius, radius, pointPaint);
			} else {
				canvas.drawCircle(getMeasuredWidth() - getPaddingRight() - radius, 
				    	getPaddingTop() + radius, radius, pointPaint);
			}
		}
	}
	
	public float getBootomY_() {
		return bootomY_;
	}

	public void setBootomY_(float bootomY_) {
		this.bootomY_ = bootomY_;
	}
	
	public void setDrawableTop(Bitmap bitmap){
		@SuppressWarnings("deprecation")
        Drawable drawable =new BitmapDrawable(bitmap);
		drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
		setCompoundDrawables(null, drawable, null, null);
		invalidate();
	}

	public boolean isRedPoint() {
		return redPoint;
	}
}
