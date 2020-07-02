package com.heaven7.android.proxyview;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * the content view of {@linkplain ProxyViewGroup}.
 * @param <P> the parameter type
 * @param <LP> the layout parameter type
 */
public abstract class AbsViewGroup<P extends Parcelable, LP extends Parcelable> extends AbsView<P> {

    public AbsViewGroup(ViewGroup proxyView) {
        super(proxyView);
    }

    @Override
    public ViewGroup getProxyView() {
        return (ViewGroup) super.getProxyView();
    }
    /**
     * like
     * <code><pre>
     *
     *      <declare-styleable name="FrameLayout_Layout">
     *              <attr name="layout_gravity" />
     *      </declare-styleable>
     *
     * </pre></code>
     * @return the layout style id
     */
    public int[] getLayoutStyleId(){
        return null;
    }
    /**
     * create layout parameter.
     * @param ta the parameter map
     * @return the layout parameter
     * @see #getLayoutStyleId()
     */
    public LP onCreateLayoutParameter(TypedArray ta) {
       return null;
    }

    /**
     * called on intercept touch event
     * @param ev the event
     * @return true if consumed
     */
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
    /**
     * called on dispatch touch event
     * @param ev the event
     * @return true if consumed
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }
    /**
     * Return true if the pressed state should be delayed for children or descendants of this
     * ViewGroup. Generally, this should be done for containers that can scroll, such as a List.
     * This prevents the pressed state from appearing when the user is actually trying to scroll
     * the content.
     *
     * The default implementation returns true for compatibility reasons. Subclasses that do
     * not scroll should generally override this method and return false.
     */
    public boolean shouldDelayChildPressedState() {
        return true;
    }
    /**
     * Returns a safe set of layout parameters based on the supplied layout params.
     * When a ViewGroup is passed a View whose layout params do not pass the test of
     * {@link #checkLayoutParams(android.view.ViewGroup.LayoutParams)}, this method
     * is invoked. This method should return a new set of layout params suitable for
     * this ViewGroup, possibly by copying the appropriate attributes from the
     * specified set of layout params.
     *
     * @param p The layout parameters to convert into a suitable set of layout parameters
     *          for this ViewGroup.
     *
     * @return an instance of {@link android.view.ViewGroup.LayoutParams} or one
     *         of its descendants
     */
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return false;
    }
    /**
     * Returns a set of default layout parameters. These parameters are requested
     * when the View passed to {@link ViewGroup#addView(View)} has no layout parameters
     * already set. If null is returned, an exception is thrown from addView.
     *
     * @return a set of default layout parameters or null
     */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return null;
    }
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener listener, Runnable defaultSet) {
        defaultSet.run();
    }
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept, Runnable superRun){
        superRun.run();
    }
    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return false;
    }

    //------------------ internal ----------------------

    public Class<?> getLayoutParameterClass(){
        return getSuperclassTypeParameter(getClass(), 1);
    }
}
