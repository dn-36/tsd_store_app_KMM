package com.profile;

import android.os.Parcel;
import android.os.Parcelable;

public class Barcode implements Parcelable {
    private byte[] barcodeData;
    private int barcodeType;
    private int fromScannerID;

    public Barcode(byte[] barcodeData, int barcodeType, int fromScannerID) {
        this.barcodeData = barcodeData;
        this.barcodeType = barcodeType;
        this.fromScannerID = fromScannerID;
    }

    protected Barcode(Parcel in) {
        this.barcodeData = in.createByteArray();
        this.barcodeType = in.readInt();
        this.fromScannerID = in.readInt();
    }

    public byte[] getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(byte[] barcodeData) {
        this.barcodeData = barcodeData;
    }

    public int getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(int barcodeType) {
        this.barcodeType = barcodeType;
    }

    public int getFromScannerID() {
        return fromScannerID;
    }

    public void setFromScannerID(int fromScannerID) {
        this.fromScannerID = fromScannerID;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeByteArray(barcodeData);
        parcel.writeInt(barcodeType);
        parcel.writeInt(fromScannerID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Barcode> CREATOR = new Creator<Barcode>() {
        @Override
        public Barcode createFromParcel(Parcel in) {
            return new Barcode(in);
        }

        @Override
        public Barcode[] newArray(int size) {
            return new Barcode[size];
        }
    };
}