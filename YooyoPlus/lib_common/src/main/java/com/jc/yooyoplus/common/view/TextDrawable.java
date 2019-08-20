package com.jc.yooyoplus.common.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;

/**
 * Textview转换成drawable
 */
public class TextDrawable extends Drawable {
    /** 系统平台的常用 typeface */
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int MONOSPACE = 3;

    private Resources mResources;
    private TextPaint mTextPaint;
    /**计算、绘制文字的layout*/
    private StaticLayout mTextLayout;
    /** 范围内的文本的对齐方式 */
    private Layout.Alignment mTextAlignment = Layout.Alignment.ALIGN_NORMAL;
    /** 文本绘制的选项 */
    private Path mTextPath;
    /** 文字颜色列表 */
    private ColorStateList mTextColors;
    /**文本rect */
    private Rect mTextBounds;
    /**要绘制的文字 */
    private CharSequence mText = "";

    /** 当前theme的属性列表，默认值 */
    private static final int[] themeAttributes = {
            android.R.attr.textAppearance
    };
    private static final int[] appearanceAttributes = {
            android.R.attr.textSize,
            android.R.attr.typeface,
            android.R.attr.textStyle,
            android.R.attr.textColor
    };


    public TextDrawable(Context context) {
        super();
        mResources = context.getResources();
        mTextBounds = new Rect();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.density = mResources.getDisplayMetrics().density;
        mTextPaint.setDither(true);

        int textSize = 15;
        ColorStateList textColor = null;
        int styleIndex = -1;
        int typefaceIndex = -1;

        TypedArray a = context.getTheme().obtainStyledAttributes(themeAttributes);
        int appearanceId = a.getResourceId(0, -1);
        a.recycle();

        TypedArray ap = null;
        if (appearanceId != -1) {
            ap = context.obtainStyledAttributes(appearanceId, appearanceAttributes);
        }
        if (ap != null) {
            for (int i=0; i < ap.getIndexCount(); i++) {
                int attr = ap.getIndex(i);
                switch (attr) {
                    case 0: //Text Size
                        textSize = a.getDimensionPixelSize(attr, textSize);
                        break;
                    case 1: //Typeface
                        typefaceIndex = a.getInt(attr, typefaceIndex);
                        break;
                    case 2: //Text Style
                        styleIndex = a.getInt(attr, styleIndex);
                        break;
                    case 3: //Text Color
                        textColor = a.getColorStateList(attr);
                        break;
                    default:
                        break;
                }
            }

            ap.recycle();
        }

        setTextColor(textColor != null ? textColor : ColorStateList.valueOf(0xFF000000));
        setRawTextSize(textSize);

        Typeface tf = null;
        switch (typefaceIndex) {
            case SANS:
                tf = Typeface.SANS_SERIF;
                break;

            case SERIF:
                tf = Typeface.SERIF;
                break;

            case MONOSPACE:
                tf = Typeface.MONOSPACE;
                break;
        }

        setTypeface(tf, styleIndex);
    }


    /**
     * 设置要显示的text
     * lvke
     * 2015-9-17上午10:37:42
     * @param text
     */
    public void setText(CharSequence text) {
        if (text == null) text = "";

        mText = text;

        measureContent();
    }

    /**
     * 获取当前的text
     * lvke
     * 2015-9-17上午10:38:02
     * @return
     */
    public CharSequence getText() {
        return mText;
    }

    /**
     * 获取当前字体大小
     * lvke
     * 2015-9-17上午10:38:17
     * @return
     */
    public float getTextSize() {
        return mTextPaint.getTextSize();
    }

    /**
     * 设置字体大小
     * lvke
     * 2015-9-17上午10:38:30
     * @param size
     */
    public void setTextSize(float size) {
//    	size = size/mTextPaint.density;
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    /**
     * 设置字体大小。使用指定的数值类型。
     * lvke
     * 2015-9-17上午10:40:16
     * @param unit 字体大小是使用sp or dp
     * @param size
     */
    public void setTextSize(int unit, float size) {
        float dimension = TypedValue.applyDimension(unit, size,
                mResources.getDisplayMetrics());
        setRawTextSize(dimension);
    }

    /**
     * 设置字体大小。以原始像素。
     * lvke
     * 2015-9-17上午10:41:22
     * @param size
     */
    private void setRawTextSize(float size) {
        if (size != mTextPaint.getTextSize()) {
            mTextPaint.setTextSize(size);

            measureContent();
        }
    }

    /**
     * 获取text的水平拉伸系数
     * lvke
     * 2015-9-17上午10:41:55
     * @return
     */
    public float getTextScaleX() {
        return mTextPaint.getTextScaleX();
    }

    /**
     * 设置text的水平拉伸系数
     * lvke
     * 2015-9-17上午10:42:17
     * @param size
     */
    public void setTextScaleX(float size) {
        if (size != mTextPaint.getTextScaleX()) {
            mTextPaint.setTextScaleX(size);
            measureContent();
        }
    }

    /**
     * 获取当前text的对齐方式
     * lvke
     * 2015-9-17上午10:42:57
     * @return
     */
    public Layout.Alignment getTextAlign() {
        return mTextAlignment;
    }

    /**
     * 设置文本的对齐方式。是相对于自身布局的对齐方式。
     *
     * lvke
     * 2015-9-17上午10:45:12
     * @param align  可以尝试以下值
     *   {@link Layout.Alignment#ALIGN_NORMAL},
     *   {@link Layout.Alignment#ALIGN_NORMAL},
     *   {@link Layout.Alignment#ALIGN_OPPOSITE}.
     */
    public void setTextAlign(Layout.Alignment align) {
        if (mTextAlignment != align) {
            mTextAlignment = align;
            measureContent();
        }
    }

    /**
     * 设置text 的Typeface
     * lvke
     * 2015-9-17上午10:48:09
     * @param tf
     */
    public void setTypeface(Typeface tf) {
        if (mTextPaint.getTypeface() != tf) {
            mTextPaint.setTypeface(tf);

            measureContent();
        }
    }

    /**
     * 设置字体和风格 。Typeface
     * lvke
     * 2015-9-17上午10:48:56
     * @param tf Typeface
     * @param style
     */
    public void setTypeface(Typeface tf, int style) {
        if (style > 0) {
            if (tf == null) {
                tf = Typeface.defaultFromStyle(style);
            } else {
                tf = Typeface.create(tf, style);
            }

            setTypeface(tf);
            // now compute what (if any) algorithmic styling is needed
            int typefaceStyle = tf != null ? tf.getStyle() : 0;
            int need = style & ~typefaceStyle;
            mTextPaint.setFakeBoldText((need & Typeface.BOLD) != 0);
            mTextPaint.setTextSkewX((need & Typeface.ITALIC) != 0 ? -0.25f : 0);
        } else {
            mTextPaint.setFakeBoldText(false);
            mTextPaint.setTextSkewX(0);
            setTypeface(tf);
        }
    }

    /**
     * 获取当前的Typeface
     * lvke
     * 2015-9-17上午10:49:19
     * @return
     */
    public Typeface getTypeface() {
        return mTextPaint.getTypeface();
    }

    public void setTextColor(int color) {
        setTextColor(ColorStateList.valueOf(color));
    }

    public void setTextColor(ColorStateList colorStateList) {
        mTextColors = colorStateList;
        updateTextColors(getState());
    }

    /**
     * Path.绘制出的text，显示的轨迹。可以的任何形状的，比如直线，圆形、S形等等。
     * 调用{@link #setBounds(int, int, int, int) setBounds()}来决定使用什么Path，
     * 且必须在绘制之前调用。
     * lvke
     * 2015-9-17上午10:51:39
     * @param path
     */
    public void setTextPath(Path path) {
        if (mTextPath != path) {
            mTextPath = path;
            measureContent();
        }
    }

    /**
     * 计算text的大小、尺寸
     * lvke
     * 2015-9-17上午10:55:50
     */
    private void measureContent() {
        if (mTextPath != null) {
            //如果指定了path，我们无法计算尺寸
            mTextLayout = null;
            mTextBounds.setEmpty();
        } else {
            //计算text的尺寸
            float desired = Layout.getDesiredWidth(mText, mTextPaint);
            mTextLayout = new StaticLayout(mText, mTextPaint, (int)desired,
                    mTextAlignment, 1.0f, 0.0f, false);
            mTextBounds.set(0, 0, mTextLayout.getWidth(), mTextLayout.getHeight());
        }

        invalidateSelf();
    }

    private boolean updateTextColors(int[] stateSet) {
        int newColor = mTextColors.getColorForState(stateSet, Color.WHITE);
        if (mTextPaint.getColor() != newColor) {
            mTextPaint.setColor(newColor);
            return  true;
        }

        return false;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mTextBounds.set(bounds);
    }

    @Override
    public boolean isStateful() {
        return mTextColors.isStateful();
    }

    @Override
    protected boolean onStateChange(int[] state) {
        //Upon state changes, grab the correct text color
        return updateTextColors(state);
    }

    @Override
    public int getIntrinsicHeight() {
        //Return the vertical bounds measured, or -1 if none
        if (mTextBounds.isEmpty()) {
            return -1;
        } else {
            return (mTextBounds.bottom - mTextBounds.top);
        }
    }

    @Override
    public int getIntrinsicWidth() {
        //Return the horizontal bounds measured, or -1 if none
        if (mTextBounds.isEmpty()) {
            return -1;
        } else {
            return (mTextBounds.right - mTextBounds.left);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (mTextPath == null) {
            //根据layout来绘制text
            mTextLayout.draw(canvas);
        } else {
            //根据指定的path来绘制text
            canvas.drawTextOnPath(mText.toString(), mTextPath, 0, 0, mTextPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (mTextPaint.getAlpha() != alpha) {
            mTextPaint.setAlpha(alpha);
        }
    }

    @Override
    public int getOpacity() {
        return mTextPaint.getAlpha();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (mTextPaint.getColorFilter() != cf) {
            mTextPaint.setColorFilter(cf);
        }
    }
}
