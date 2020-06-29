package com.heaven7.android.proxyview;

import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * the content view of {@linkplain ProxyViewGroup}.
 * @param <P> the parameter type
 */
public abstract class AbsViewGroup<P extends Parcelable> extends AbsView<P> {

    public AbsViewGroup(ViewGroup proxyView) {
        super(proxyView);
    }

    @Override
    public ViewGroup getProxyView() {
        return (ViewGroup) super.getProxyView();
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

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.LayoutParams(getContext(), attrs);
    }
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return p;
    }
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof ViewGroup.LayoutParams;
    }
}
