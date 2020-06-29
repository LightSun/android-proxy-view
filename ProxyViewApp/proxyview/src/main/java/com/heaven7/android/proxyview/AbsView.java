package com.heaven7.android.proxyview;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * the content view of {@linkplain ProxyView}
 * @param <P> the parameter type
 */
public abstract class AbsView<P extends Parcelable> {

    private final View proxyView;
    private P parameter;

    public AbsView(View proxyView) {
        if(!(proxyView instanceof ProxyView) && !(proxyView instanceof ProxyViewGroup)){
            throw new IllegalStateException("proxy view must be ProxyView or ProxyViewGroup.");
        }
        this.proxyView = proxyView;
    }

    public View getProxyView() {
        return proxyView;
    }

    public Resources getResources() {
        return proxyView.getResources();
    }

    public Context getContext() {
        return proxyView.getContext();
    }

    public void invalidate() {
        getProxyView().invalidate();
    }

    public void postInvalidate() {
        getProxyView().postInvalidate();
    }

    public void post(Runnable task) {
        getProxyView().post(task);
    }

    public void postDelayed(Runnable action, long delayMillis) {
        getProxyView().postDelayed(action, delayMillis);
    }

    /**
     * get the parameter of content view
     * @return the parameter
     */
    public P getParameter() {
        return parameter;
    }
    //you should refresh ui on here
    /**
     * set the parameter. this can be called from {@linkplain View#onRestoreInstanceState(Parcelable)} so you should refresh ui here.
     * @param parameter the parameter
     */
    public void setParameter(P parameter) {
        this.parameter = parameter;
    }
    /**
     * call this to initialize
     * @param ta the type array. may be null
     */
    public void attach(TypedArray ta) {
        parameter = onCreate(ta);
    }

    /**
     * the styled id often used for {@linkplain Context#obtainStyledAttributes(AttributeSet, int[])}.
     * @return the style id
     */
    public abstract int[] getStyleId();

    /**
     * called by {@linkplain #attach(TypedArray)}
     * @param ta the type array
     * @return the parameter
     */
    protected abstract P onCreate(TypedArray ta);

    /**
     * called on destroy content view
     */
    public void onDestroy() {

    }

    /**
     * called on detach this
     */
    public void onDetach(){

    }
    //--------------------------------------

    /**
     * called on draw
     * @param canvas the canvas
     */
    public void onDraw(Canvas canvas) {

    }

    /**
     * called before dispatch draw
     * @param canvas the canvas
     */
    public void onPreDispatchDraw(Canvas canvas) {

    }
    /**
     * called after dispatch draw
     * @param canvas the canvas
     */
    public void onPostDispatchDraw(Canvas canvas) {

    }

    /**
     * called on touch
     * @param event the event
     * @return true if touch handled
     */
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * called on measure
     * @param widthMeasureSpec the width measure spec
     * @param heightMeasureSpec the height measure spec
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ProxyViewDelegate pv = (ProxyViewDelegate) getProxyView();
        int w = View.getDefaultSize(pv.getSuggestedMinimumWidth(), widthMeasureSpec);
        int h = View.getDefaultSize(pv.getSuggestedMinimumHeight(), heightMeasureSpec);
        pv.applyMeasuredWidthHeight(w, h);
    }

    /**
     * called on layout
     * @param changed true if changed
     * @param left the left
     * @param top the top
     * @param right the right
     * @param bottom the bottom
     */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    /**
     * called on size changed
     * @param w the new width
     * @param h the new height
     * @param oldw the old width
     * @param oldh the old height
     */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

    }

    /**
     * called on configuration changed
     * @param newConfig the config
     */
    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void requestLayout() {

    }
    //---------------------------------------------
    public boolean setForegroundGravity(int foregroundGravity) {
        return false;
    }
    public CharSequence getAccessibilityClassName(){
        return getClass().getName();
    }

    public int getBaseline() {
        return -1;
    }
    public void onRtlPropertiesChanged(int layoutDirection) {

    }
}
