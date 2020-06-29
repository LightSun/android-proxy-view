package com.heaven7.android.proxyview;

/**
 * the proxy view delegate
 * @param <V> the real content view type
 */
public interface ProxyViewDelegate<V extends AbsView> {

    /**
     * get the min width
     * @return the min width
     */
    int getSuggestedMinimumWidth();

    /**
     * get the min height
     * @return the min height
     */
    int getSuggestedMinimumHeight();

    /**
     * apply measured width and height
     * @param w the width
     * @param h the height
     */
    void applyMeasuredWidthHeight(int w, int h);
    /**
     * get the real content view
     * @param <T> the content view type
     * @return the content view
     */
    <T extends V> T getContentView();

    /**
     * set content view
     * @param v the view type
     */
    void setContentView(V v);
}
