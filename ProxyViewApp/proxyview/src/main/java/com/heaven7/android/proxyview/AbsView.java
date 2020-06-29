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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
     * Called from layout when this view should
     * assign a size and position to each of its children.
     *
     * Derived classes with children should override
     * this method and call layout on each of
     * their children.
     * @param changed This is a new size or position for this view
     * @param left Left position, relative to parent
     * @param top Top position, relative to parent
     * @param right Right position, relative to parent
     * @param bottom Bottom position, relative to parent
     */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

    }

    /**
     * Called when the current configuration of the resources being used
     * by the application have changed.  You can use this to decide when
     * to reload resources that can changed based on orientation and other
     * configuration characteristics.  You only need to use this if you are
     * not relying on the normal {@link android.app.Activity} mechanism of
     * recreating the activity instance upon a configuration change.
     *
     * @param newConfig The new resource configuration.
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

    //--------------- internal ----------------------
    /**
     * get the parameter class
     * @return the parameter class
     */
    public Class<?> getParameterClass(){
        return getSuperclassTypeParameter(getClass(), 0);
    }
    static Class<?> getSuperclassTypeParameter(Class<?> subclass, int index) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return (Class<?>) parameterized.getActualTypeArguments()[index];
    }
}
