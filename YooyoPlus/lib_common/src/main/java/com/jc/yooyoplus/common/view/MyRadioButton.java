package com.jc.yooyoplus.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.jc.yooyoplus.common.CommonUtils;
import com.jc.yooyoplus.common.R;

/**
 * 单选按钮，使用fontawesome icon
 */
@SuppressLint("AppCompatCustomView")
public class MyRadioButton extends RadioButton {

    Context context_;
    /**字符集转draw*/
    private String text_draw_;
    /**选中后更换的字符集*/
    private String text_draw_select_;
    private TextDrawable drawable_;
    /**text_draw_的选中的颜色*/
    private int selectColor_ = Color.YELLOW;
    /**text_draw_的默认颜色*/
    private int selectColor_no_ = Color.BLACK;
    /**图标大小*/
    private int drawSize = -1;
    /**图标在RadioButton 里面的 位置*/
    private String ico_location ;
    private static final String ICO_LEFT = "left";
    private static final String ICO_RIGHT = "right";
    private static final String ICO_TOP = "top";
    private static final String ICO_BOTTOM = "bottom";


    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initXml2(context, attrs);
    }

    public MyRadioButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }


    private void init(Context context){
        context_ = context;
        text_draw_ = getText_draw_();
        selectColor_ = getSelectColor_();
        selectColor_no_ = getSelectColor_no_();
        ico_location = getIco_location();
        doMake();
    }


    private void initXml2(Context context, AttributeSet attrs){
        context_ = context;
        TypedArray arr = context_.obtainStyledAttributes(attrs,
                R.styleable.MyRadioButton);
        text_draw_ = arr.getString(R.styleable.MyRadioButton_txt_draw);
        text_draw_select_ = arr.getString(R.styleable.MyRadioButton_txt_draw_select);
        selectColor_ = arr.getColor(R.styleable.MyRadioButton_select_color, selectColor_);
        selectColor_no_ = arr.getColor(R.styleable.MyRadioButton_select_color_no, selectColor_no_);
//		drawSize = (int) arr.getDimension(R.styleable.MyRadioButton_drawsize, drawSize);
        drawSize = (int) arr.getDimensionPixelSize(R.styleable.MyRadioButton_drawsize, drawSize);
        ico_location = arr.getString(R.styleable.MyRadioButton_ico_location);
        if (!isCompositerules(ico_location)) {
            ico_location = ICO_TOP;//默认图标在顶部
        }
        doMake();
        arr.recycle();
    }



    /**
     * 处理自定义
     * 2015-10-12下午6:39:06
     */
    private void doMake(){
        float size = getTextSize();
        if (drawSize > 0) {
            size  = CommonUtils.px2sp(context_, drawSize);
        }
        drawable_ = new TextDrawable(context_);
        drawable_.setTypeface(Typeface.createFromAsset(context_.getAssets(),"fonts/fontawesome-webfont.ttf"));
        drawable_.setTextSize(size);
        drawable_.setText(text_draw_);
        drawable_.setTextColor(selectColor_no_);
//		setButtonDrawable(d);
        drawable_.setBounds(0, 0, drawable_.getMinimumWidth(), drawable_.getMinimumHeight());
        if (ico_location.equalsIgnoreCase(ICO_TOP)) {
            setCompoundDrawables(null, drawable_, null, null);
        }else if(ico_location.equalsIgnoreCase(ICO_BOTTOM)){
            setCompoundDrawables(null, null, null, drawable_);
        }else if(ico_location.equalsIgnoreCase(ICO_LEFT)){
            setCompoundDrawables(drawable_, null, null, null);
        }else if(ico_location.equalsIgnoreCase(ICO_RIGHT)){
            setCompoundDrawables(null, null, drawable_, null);
        }
        setOnCheckedChangeListener(new OnChecked_ChangeListener());
    }

    public class OnChecked_ChangeListener implements OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {

            if (isChecked) {
                drawable_.setTextColor(selectColor_);
                if (!TextUtils.isEmpty(text_draw_select_)) {
                    drawable_.setText(text_draw_select_);
                }
            }else {
                drawable_.setTextColor(selectColor_no_);
                drawable_.setText(text_draw_);
            }
        }

    }

    /**
     * ico_location属性是不是复合规则
     * 2015-10-12下午5:25:53
     * @return
     */
    private boolean isCompositerules(String icoLocation){
        if (!TextUtils.isEmpty(ico_location)) {
            if (icoLocation.equalsIgnoreCase(ICO_BOTTOM) || icoLocation.equalsIgnoreCase(ICO_TOP)
                    || icoLocation.equalsIgnoreCase(ICO_LEFT) || icoLocation.equalsIgnoreCase(ICO_RIGHT)) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public String getText_draw_() {
        return text_draw_;
    }

    public void setText_draw_(String text_draw_) {
        if (TextUtils.isEmpty(text_draw_)) {
            this.text_draw_ = "";
        }else {
            this.text_draw_ = text_draw_;
        }
    }

    public TextDrawable getDrawable_() {
        return drawable_;
    }

    public void setDrawable_(TextDrawable drawable_) {
        this.drawable_ = drawable_;
    }

    public String getTxt_draw_select_() {
        return text_draw_select_;
    }

    public void setTxt_draw_select_(String txt_draw_select_) {
        if (TextUtils.isEmpty(txt_draw_select_)) {
            txt_draw_select_ = "";
        }
        this.text_draw_select_ = txt_draw_select_;
    }

    public int getSelectColor_() {
        return selectColor_;
    }

    public void setSelectColor_(int selectColor_) {
        this.selectColor_ = selectColor_;
    }

    public int getSelectColor_no_() {
        return selectColor_no_;
    }

    public void setSelectColor_no_(int selectColor_no_) {
        this.selectColor_no_ = selectColor_no_;
    }

    public String getIco_location() {
        return TextUtils.isEmpty(ico_location)?ICO_TOP:ico_location;
    }

    public void setIco_location(String ico_location) {
        if (isCompositerules(ico_location)) {
            this.ico_location = ico_location;
        }
    }

    public int getDrawSize() {
        return drawSize;
    }

    public void setDrawSize(int drawSize) {
        this.drawSize = drawSize;
    }
}
