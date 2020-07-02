package com.heaven7.android.proxyview.app;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.heaven7.android.proxyview.AbsViewGroup;

public class LinearView extends AbsViewGroup<LinearView.P, LinearView.LP> {

    private static final int[] ATTRS ={
            getSystemAttrId("orientation")
    };

    public LinearView(ViewGroup proxyView) {
        super(proxyView);
    }

    @Override
    public int[] getStyleId() {
        return new int[]{getSystemAttrId("orientation")};
    }
    @Override
    protected P onCreate(TypedArray ta) {
        int orientation = ta.getInt(0, LinearLayout.HORIZONTAL);
        System.out.println("orientation = " + orientation);
        P p = new P();
        p.orientation = orientation;
        return p;
    }
    @Override
    public int[] getLayoutStyleId() {
        return new int[]{ getSystemAttrId("layout_weight"), getSystemAttrId("layout_gravity")};
    }

    @Override
    public LP onCreateLayoutParameter(TypedArray ta) {
        return super.onCreateLayoutParameter(ta);
    }

    public static class P implements Parcelable {
        public int orientation =-1;
        public int gravity = -1;

        public boolean baselineAligned = true;
        public boolean measureWithLargestChild = false;
        public int showDividers = 0;

        public float weightSum = -1f;
        public int baselineAlignedChildIndex = -1;
        public int dividerPadding = 0;
        public int dividerId;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.orientation);
            dest.writeInt(this.gravity);
            dest.writeByte(this.baselineAligned ? (byte) 1 : (byte) 0);
            dest.writeByte(this.measureWithLargestChild ? (byte) 1 : (byte) 0);
            dest.writeInt(this.showDividers);
            dest.writeFloat(this.weightSum);
            dest.writeInt(this.baselineAlignedChildIndex);
            dest.writeInt(this.dividerPadding);
            dest.writeInt(this.dividerId);
        }

        public P() {
        }

        protected P(Parcel in) {
            this.orientation = in.readInt();
            this.gravity = in.readInt();
            this.baselineAligned = in.readByte() != 0;
            this.measureWithLargestChild = in.readByte() != 0;
            this.showDividers = in.readInt();
            this.weightSum = in.readFloat();
            this.baselineAlignedChildIndex = in.readInt();
            this.dividerPadding = in.readInt();
            this.dividerId = in.readInt();
        }

        public static final Parcelable.Creator<P> CREATOR = new Parcelable.Creator<P>() {
            @Override
            public P createFromParcel(Parcel source) {
                return new P(source);
            }

            @Override
            public P[] newArray(int size) {
                return new P[size];
            }
        };
    }
    public static class LP implements Parcelable {
       float weight = 0;
       int gravity = -1;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeFloat(this.weight);
            dest.writeInt(this.gravity);
        }

        public LP() {
        }

        protected LP(Parcel in) {
            this.weight = in.readFloat();
            this.gravity = in.readInt();
        }

        public static final Parcelable.Creator<LP> CREATOR = new Parcelable.Creator<LP>() {
            @Override
            public LP createFromParcel(Parcel source) {
                return new LP(source);
            }

            @Override
            public LP[] newArray(int size) {
                return new LP[size];
            }
        };
    }
}
