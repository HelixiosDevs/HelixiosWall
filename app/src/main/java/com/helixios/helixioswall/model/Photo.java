package com.helixios.helixioswall.model;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;

public class Photo {
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
    private String owner_name;
    @Nullable
    private String tags;

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
    @NotNull
    public final String getUrl_z() {
        return "https://farm" + this.farm + ".staticflickr.com/" + this.server + "/" + this.id + "_" + this.secret + ".jpg";
    }
}
