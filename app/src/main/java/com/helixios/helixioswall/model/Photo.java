package com.helixios.helixioswall.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "photo_table")
public class Photo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int primary_id;
    @Nullable
    private Long time;
    @Nullable
    private String id;
    @Nullable
    private String owner;
    @Nullable
    private String secret;
    @Nullable
    private String server;
    @Nullable
    private Integer farm;
    @Nullable
    private String title;
    @Nullable
    private Integer ispublic;
    @Nullable
    private Integer isfriend;
    @Nullable
    private Integer isfamily;
    @Nullable
    private String datetaken;
    @Nullable
    private String iconserver;
    @Nullable
    private Integer iconfarm;
    @Nullable
    private String originalsecret;
    @Nullable
    private String originalformat;
    @Nullable
    private String url_o;
    @Nullable
    private Integer height_o;
    @Nullable
    private Integer width_o;
    private int height_z;
    private int width_z;
    @Nullable
    @SerializedName("ownername")
    private String owner_name;
    @Nullable
    private String tags;
    // Size URLs returned via the url_* extras. Flickr omits a size when the caller may not
    // access it (owner preference, or Free-account photos above 1024px since May 2025).
    @Nullable
    private String url_z;
    @Nullable
    private String url_c;
    @Nullable
    private String url_l;
    @Nullable
    private String url_h;
    @Nullable
    private String url_k;

    private static final String STATIC_HOST = "https://live.staticflickr.com/";

    public Photo(@Nullable String id, @Nullable String owner, @Nullable String secret, @Nullable String server, @Nullable Integer farm, @Nullable String title, @Nullable Integer ispublic, @Nullable Integer isfriend, @Nullable Integer isfamily, @Nullable String datetaken, @Nullable String iconserver, @Nullable Integer iconfarm, @Nullable String originalsecret, @Nullable String originalformat, @Nullable String url_o, @Nullable Integer height_o, @Nullable Integer width_o, int height_z, int width_z, @Nullable String owner_name, @Nullable String tags) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
        this.datetaken = datetaken;
        this.iconserver = iconserver;
        this.iconfarm = iconfarm;
        this.originalsecret = originalsecret;
        this.originalformat = originalformat;
        this.url_o = url_o;
        this.height_o = height_o;
        this.width_o = width_o;
        this.height_z = height_z;
        this.width_z = width_z;
        this.owner_name = owner_name;
        this.tags = tags;
    }

    protected Photo(Parcel in) {
        id = in.readString();
        owner = in.readString();
        secret = in.readString();
        server = in.readString();
        if (in.readByte() == 0) {
            farm = null;
        } else {
            farm = in.readInt();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            ispublic = null;
        } else {
            ispublic = in.readInt();
        }
        if (in.readByte() == 0) {
            isfriend = null;
        } else {
            isfriend = in.readInt();
        }
        if (in.readByte() == 0) {
            isfamily = null;
        } else {
            isfamily = in.readInt();
        }
        datetaken = in.readString();
        iconserver = in.readString();
        if (in.readByte() == 0) {
            iconfarm = null;
        } else {
            iconfarm = in.readInt();
        }
        originalsecret = in.readString();
        originalformat = in.readString();
        url_o = in.readString();
        if (in.readByte() == 0) {
            height_o = null;
        } else {
            height_o = in.readInt();
        }
        if (in.readByte() == 0) {
            width_o = null;
        } else {
            width_o = in.readInt();
        }
        height_z = in.readInt();
        width_z = in.readInt();
        owner_name = in.readString();
        tags = in.readString();
        url_z = in.readString();
        url_c = in.readString();
        url_l = in.readString();
        url_h = in.readString();
        url_k = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public int getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(int primary_id) {
        this.primary_id = primary_id;
    }
    @Nullable
    public Long getTime() {
        return time;
    }

    public void setTime(@Nullable Long time) {
        this.time = time;
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getOwner() {
        return owner;
    }

    @Nullable
    public String getSecret() {
        return secret;
    }

    @Nullable
    public String getServer() {
        return server;
    }

    @Nullable
    public Integer getFarm() {
        return farm;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public Integer getIspublic() {
        return ispublic;
    }

    @Nullable
    public Integer getIsfriend() {
        return isfriend;
    }

    @Nullable
    public Integer getIsfamily() {
        return isfamily;
    }

    @Nullable
    public String getDatetaken() {
        return datetaken;
    }

    @Nullable
    public String getIconserver() {
        return iconserver;
    }

    @Nullable
    public Integer getIconfarm() {
        return iconfarm;
    }

    @Nullable
    public String getOriginalsecret() {
        return originalsecret;
    }

    @Nullable
    public String getOriginalformat() {
        return originalformat;
    }

    @Nullable
    public String getUrl_o() {
        return url_o;
    }

    @Nullable
    public Integer getHeight_o() {
        return height_o;
    }

    @Nullable
    public Integer getWidth_o() {
        return width_o;
    }

    public int getHeight_z() {
        return height_z;
    }

    public int getWidth_z() {
        return width_z;
    }

    @Nullable
    public String getOwner_name() {
        return owner_name;
    }

    @Nullable
    public String getTags() {
        return tags;
    }
    //Grid thumbnail, 640px on the longest edge
    @NotNull
    public String getUrl_z() {
        return url_z != null ? url_z : buildStaticUrl("z");
    }

    public void setUrl_z(@Nullable String url_z) {
        this.url_z = url_z;
    }

    @Nullable
    public String getUrl_c() {
        return url_c;
    }

    public void setUrl_c(@Nullable String url_c) {
        this.url_c = url_c;
    }

    @Nullable
    public String getUrl_l() {
        return url_l;
    }

    public void setUrl_l(@Nullable String url_l) {
        this.url_l = url_l;
    }

    @Nullable
    public String getUrl_h() {
        return url_h;
    }

    public void setUrl_h(@Nullable String url_h) {
        this.url_h = url_h;
    }

    @Nullable
    public String getUrl_k() {
        return url_k;
    }

    public void setUrl_k(@Nullable String url_k) {
        this.url_k = url_k;
    }

    //Image shown on the photo screen, at most 1024px, which is always accessible
    @NotNull
    public String getDisplayUrl() {
        if (url_l != null) return url_l;
        if (url_c != null) return url_c;
        return buildStaticUrl("b");
    }

    //Largest image the API key may access, used for downloads and wallpapers
    @NotNull
    public String getBestDownloadUrl() {
        if (url_o != null) return url_o;
        if (url_k != null) return url_k;
        if (url_h != null) return url_h;
        return getDisplayUrl();
    }

    //Sizes below 1600px share the photo secret, so their URLs can be built without the extras
    private String buildStaticUrl(String sizeSuffix) {
        return STATIC_HOST + server + "/" + id + "_" + secret + "_" + sizeSuffix + ".jpg";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(owner);
        dest.writeString(secret);
        dest.writeString(server);
        if (farm == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(farm);
        }
        dest.writeString(title);
        if (ispublic == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ispublic);
        }
        if (isfriend == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isfriend);
        }
        if (isfamily == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isfamily);
        }
        dest.writeString(datetaken);
        dest.writeString(iconserver);
        if (iconfarm == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(iconfarm);
        }
        dest.writeString(originalsecret);
        dest.writeString(originalformat);
        dest.writeString(url_o);
        if (height_o == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(height_o);
        }
        if (width_o == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(width_o);
        }
        dest.writeInt(height_z);
        dest.writeInt(width_z);
        dest.writeString(owner_name);
        dest.writeString(tags);
        dest.writeString(url_z);
        dest.writeString(url_c);
        dest.writeString(url_l);
        dest.writeString(url_h);
        dest.writeString(url_k);
    }
}
