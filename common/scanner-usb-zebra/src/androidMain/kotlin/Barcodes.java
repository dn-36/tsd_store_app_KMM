import android.os.Parcel;
import android.os.Parcelable;

public class Barcodes implements Parcelable {
    byte[] barcodeData;
    int barcodeType;
    int fromScannerID;

    public Barcodes(byte[] barcodeData, int barcodeType, int fromScannerID) {
        this.barcodeData=barcodeData;
        this.barcodeType=barcodeType;
        this.fromScannerID=fromScannerID;
    }
    public Barcodes(Parcel in) {
        this.barcodeData=in.readString().getBytes();
        this.barcodeType=in.readInt();
        this.fromScannerID=in.readInt();
    }

    public byte[] getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(byte[] barcodeData) {
        this.barcodeData = barcodeData;
    }

    public int getFromScannerID() {
        return fromScannerID;
    }

    public void setFromScannerID(int fromScannerID) {
        this.fromScannerID = fromScannerID;
    }

    public int getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(int barcodeType) {
        this.barcodeType = barcodeType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(new String(barcodeData));
        parcel.writeInt(barcodeType);
        parcel.writeInt(fromScannerID);
    }
    public static final Creator<Barcodes> CREATOR = new Creator<Barcodes>() {

        @Override
        public Barcodes createFromParcel(Parcel source) {
            return new Barcodes(source);
        }

        @Override
        public Barcodes[] newArray(int size) {
            return new Barcodes[size];
        }
    };
}
