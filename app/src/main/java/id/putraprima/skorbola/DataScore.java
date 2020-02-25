package id.putraprima.skorbola;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
public class DataScore implements Parcelable {
    private String homeText, awayText;
    private int homeScore, awayScore;
    private Uri homeUri, awayUri;

    public DataScore(String homeText, String awayText, int homeScore, int awayScore, Uri homeUri, Uri awayUri) {
        this.homeText = homeText;
        this.awayText = awayText;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    public String getHomeText() {
        return homeText;
    }

    public void setHomeText(String homeText) {
        this.homeText = homeText;
    }

    public String getAwayText() {
        return awayText;
    }

    public void setAwayText(String awayText) {
        this.awayText = awayText;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeText);
        dest.writeString(this.awayText);
        dest.writeInt(this.homeScore);
        dest.writeInt(this.awayScore);
        dest.writeParcelable(this.homeUri, flags);
        dest.writeParcelable(this.awayUri, flags);
    }

    protected DataScore(Parcel in) {
        this.homeText = in.readString();
        this.awayText = in.readString();
        this.homeScore = in.readInt();
        this.awayScore = in.readInt();
        this.homeUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<DataScore> CREATOR = new Creator<DataScore>() {
        @Override
        public DataScore createFromParcel(Parcel source) {
            return new DataScore(source);
        }

        @Override
        public DataScore[] newArray(int size) {
            return new DataScore[size];
        }
    };
}
