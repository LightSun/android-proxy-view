package com.heaven7.android.proxyview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ProxyView extends View implements ProxyViewDelegate<AbsView> {

    private AbsView mView;

    public ProxyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ProxyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    @TargetApi(21)
    public ProxyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
    protected void init(Context context, AttributeSet attrs){
        if(attrs == null){
            throw new IllegalStateException();
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProxyView);
        try {
            String cn = a.getString(R.styleable.ProxyView_lib_proxy_view_class);
            mView = (AbsView) Class.forName(cn).getConstructor(View.class).newInstance(this);
            if(mView.getStyleId() != null){
                final int ap = AbsView.getSystemAttrId("style");
                TypedArray a2;
                if (ap != 0) {
                    a2 = context.obtainStyledAttributes(ap, mView.getStyleId());
                }else {
                    a2 = context.obtainStyledAttributes(attrs, mView.getStyleId());
                }
                try {
                    mView.onInitialize(a2);
                }finally {
                    a2.recycle();
                }
            }else {
                mView.onInitialize(null);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            a.recycle();
        }
    }

    @Override @SuppressWarnings("unchecked")
    public <T extends AbsView> T getContentView() {
        return (T) mView;
    }
    @Override
    public void setContentView(AbsView view) {
        if(view == null){
            throw new NullPointerException();
        }
        if(mView != null){
            mView.onDetach();
        }
        this.mView = view;
        requestLayout();
        invalidate();
    }
    public void applyMeasuredWidthHeight(int measureWidth, int measureHeight){
        super.setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mView.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mView.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mView.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        mView.onConfigurationChanged(newConfig);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, mView.getParameter());
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mView.setParameter(ss.params);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mView.onTouchEvent(event);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        mView.onDraw(canvas);
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        mView.onPreDispatchDraw(canvas);
        super.dispatchDraw(canvas);
        mView.onPostDispatchDraw(canvas);
    }
    @Override
    protected void onDetachedFromWindow() {
        mView.onDetachedFromWindow();
        super.onDetachedFromWindow();
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mView.onAttachToWindow();
    }

    @Override
    public int getSuggestedMinimumWidth() {
        return mView.getSuggestedMinimumWidth(super.getSuggestedMinimumWidth());
    }
    @Override
    public int getSuggestedMinimumHeight() {
        return mView.getSuggestedMinimumHeight(super.getSuggestedMinimumHeight());
    }
    public void requestLayout(){
        super.requestLayout();
        mView.requestLayout();
    }
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        mView.setVisibility(visibility);
    }

    //-------------------------- use less -------------------
    public void setFitsSystemWindows(boolean fitSystemWindows) {
        super.setFitsSystemWindows(fitSystemWindows);
        mView.setFitsSystemWindows(fitSystemWindows);
    }
    public void setForegroundGravity(int foregroundGravity){
        if(!mView.setForegroundGravity(foregroundGravity)){
            super.setForegroundGravity(foregroundGravity);
        }
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return mView.getAccessibilityClassName();
    }
    public int getBaseline(){
        return mView.getBaseline();
    }
    public void onRtlPropertiesChanged(int layoutDirection){
        mView.onRtlPropertiesChanged(layoutDirection);
    }
    public void drawableStateChanged() {
        super.drawableStateChanged();
        mView.drawableStateChanged();
    }
    @Override
    protected boolean verifyDrawable(Drawable who) {
        return mView.verifyDrawable(who, super.verifyDrawable(who));
    }
}
