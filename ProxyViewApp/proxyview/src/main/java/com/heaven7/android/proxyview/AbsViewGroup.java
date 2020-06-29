package com.heaven7.android.proxyview;

import android.os.Parcelable;
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
}
