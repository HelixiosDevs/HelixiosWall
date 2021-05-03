package com.helixios.helixioswall.model;

import java.util.ArrayList;

public class PhotoDetails {
    PhotoDetailNest PhotoObject;
    private String stat;

    // Getter Methods

    public PhotoDetailNest getPhoto() {
        return PhotoObject;
    }

    public String getStat() {
        return stat;
    }

    // Setter Methods

    public void setPhoto( PhotoDetailNest photoObject ) {
        this.PhotoObject = photoObject;
    }

    public void setStat( String stat ) {
        this.stat = stat;
    }
    public static final class PhotoDetailNest {
        private String id;
        private String secret;
        private String server;
        private float farm;
        private String dateuploaded;
        private float isfavorite;
        private float license;
        private float safety_level;
        private float rotation;
        Owner OwnerObject;
        Title TitleObject;
        Description DescriptionObject;
        Visibility VisibilityObject;
        Dates DatesObject;
        private String views;
        Editability EditabilityObject;
        Publiceditability PubliceditabilityObject;
        Usage UsageObject;
        Comments CommentsObject;
        Notes NotesObject;
        People PeopleObject;
        Tags TagsObject;
        Urls UrlsObject;
        private String media;


        // Getter Methods

        public String getId() {
            return id;
        }

        public String getSecret() {
            return secret;
        }

        public String getServer() {
            return server;
        }

        public float getFarm() {
            return farm;
        }

        public String getDateuploaded() {
            return dateuploaded;
        }

        public float getIsfavorite() {
            return isfavorite;
        }

        public float getLicense() {
            return license;
        }

        public float getSafety_level() {
            return safety_level;
        }

        public float getRotation() {
            return rotation;
        }

        public Owner getOwner() {
            return OwnerObject;
        }

        public Title getTitle() {
            return TitleObject;
        }

        public Description getDescription() {
            return DescriptionObject;
        }

        public Visibility getVisibility() {
            return VisibilityObject;
        }

        public Dates getDates() {
            return DatesObject;
        }

        public String getViews() {
            return views;
        }

        public Editability getEditability() {
            return EditabilityObject;
        }

        public Publiceditability getPubliceditability() {
            return PubliceditabilityObject;
        }

        public Usage getUsage() {
            return UsageObject;
        }

        public Comments getComments() {
            return CommentsObject;
        }

        public Notes getNotes() {
            return NotesObject;
        }

        public People getPeople() {
            return PeopleObject;
        }

        public Tags getTags() {
            return TagsObject;
        }

        public Urls getUrls() {
            return UrlsObject;
        }

        public String getMedia() {
            return media;
        }

        // Setter Methods

        public void setId( String id ) {
            this.id = id;
        }

        public void setSecret( String secret ) {
            this.secret = secret;
        }

        public void setServer( String server ) {
            this.server = server;
        }

        public void setFarm( float farm ) {
            this.farm = farm;
        }

        public void setDateuploaded( String dateuploaded ) {
            this.dateuploaded = dateuploaded;
        }

        public void setIsfavorite( float isfavorite ) {
            this.isfavorite = isfavorite;
        }

        public void setLicense( float license ) {
            this.license = license;
        }

        public void setSafety_level( float safety_level ) {
            this.safety_level = safety_level;
        }

        public void setRotation( float rotation ) {
            this.rotation = rotation;
        }

        public void setOwner( Owner ownerObject ) {
            this.OwnerObject = ownerObject;
        }

        public void setTitle( Title titleObject ) {
            this.TitleObject = titleObject;
        }

        public void setDescription( Description descriptionObject ) {
            this.DescriptionObject = descriptionObject;
        }

        public void setVisibility( Visibility visibilityObject ) {
            this.VisibilityObject = visibilityObject;
        }

        public void setDates( Dates datesObject ) {
            this.DatesObject = datesObject;
        }

        public void setViews( String views ) {
            this.views = views;
        }

        public void setEditability( Editability editabilityObject ) {
            this.EditabilityObject = editabilityObject;
        }

        public void setPubliceditability( Publiceditability publiceditabilityObject ) {
            this.PubliceditabilityObject = publiceditabilityObject;
        }

        public void setUsage( Usage usageObject ) {
            this.UsageObject = usageObject;
        }

        public void setComments( Comments commentsObject ) {
            this.CommentsObject = commentsObject;
        }

        public void setNotes( Notes notesObject ) {
            this.NotesObject = notesObject;
        }

        public void setPeople( People peopleObject ) {
            this.PeopleObject = peopleObject;
        }

        public void setTags( Tags tagsObject ) {
            this.TagsObject = tagsObject;
        }

        public void setUrls( Urls urlsObject ) {
            this.UrlsObject = urlsObject;
        }

        public void setMedia( String media ) {
            this.media = media;
        }
    }
    public static final class Urls {
        ArrayList<Object> url = new ArrayList<Object>();

        public ArrayList<Object> getUrl() {
            return url;
        }

        public void setUrl(ArrayList<Object> url) {
            this.url = url;
        }
    }
    public static final class Tags {
        ArrayList<Object> tag = new ArrayList<Object>();

        public ArrayList<Object> getTag() {
            return tag;
        }

        public void setTag(ArrayList<Object> tag) {
            this.tag = tag;
        }
// Getter Methods



        // Setter Methods


    }
    public static final class People {
        private float haspeople;


        // Getter Methods

        public float getHaspeople() {
            return haspeople;
        }

        // Setter Methods

        public void setHaspeople( float haspeople ) {
            this.haspeople = haspeople;
        }
    }
    public static final class Notes {
        ArrayList<Object> note = new ArrayList<Object>();

        public ArrayList<Object> getNote() {
            return note;
        }

        public void setNote(ArrayList<Object> note) {
            this.note = note;
        }
    }
    public static final class Comments {
        private float _content;


        // Getter Methods

        public float get_content() {
            return _content;
        }

        // Setter Methods

        public void set_content( float _content ) {
            this._content = _content;
        }
    }
    public static final class Usage {
        private float candownload;
        private float canblog;
        private float canprint;
        private float canshare;


        // Getter Methods

        public float getCandownload() {
            return candownload;
        }

        public float getCanblog() {
            return canblog;
        }

        public float getCanprint() {
            return canprint;
        }

        public float getCanshare() {
            return canshare;
        }

        // Setter Methods

        public void setCandownload( float candownload ) {
            this.candownload = candownload;
        }

        public void setCanblog( float canblog ) {
            this.canblog = canblog;
        }

        public void setCanprint( float canprint ) {
            this.canprint = canprint;
        }

        public void setCanshare( float canshare ) {
            this.canshare = canshare;
        }
    }
    public static final class Publiceditability {
        private float cancomment;
        private float canaddmeta;


        // Getter Methods

        public float getCancomment() {
            return cancomment;
        }

        public float getCanaddmeta() {
            return canaddmeta;
        }

        // Setter Methods

        public void setCancomment( float cancomment ) {
            this.cancomment = cancomment;
        }

        public void setCanaddmeta( float canaddmeta ) {
            this.canaddmeta = canaddmeta;
        }
    }
    public static final class Editability {
        private float cancomment;
        private float canaddmeta;


        // Getter Methods

        public float getCancomment() {
            return cancomment;
        }

        public float getCanaddmeta() {
            return canaddmeta;
        }

        // Setter Methods

        public void setCancomment( float cancomment ) {
            this.cancomment = cancomment;
        }

        public void setCanaddmeta( float canaddmeta ) {
            this.canaddmeta = canaddmeta;
        }
    }
    public static final class Dates {
        private String posted;
        private String taken;
        private float takengranularity;
        private float takenunknown;
        private String lastupdate;


        // Getter Methods

        public String getPosted() {
            return posted;
        }

        public String getTaken() {
            return taken;
        }

        public float getTakengranularity() {
            return takengranularity;
        }

        public float getTakenunknown() {
            return takenunknown;
        }

        public String getLastupdate() {
            return lastupdate;
        }

        // Setter Methods

        public void setPosted( String posted ) {
            this.posted = posted;
        }

        public void setTaken( String taken ) {
            this.taken = taken;
        }

        public void setTakengranularity( float takengranularity ) {
            this.takengranularity = takengranularity;
        }

        public void setTakenunknown( float takenunknown ) {
            this.takenunknown = takenunknown;
        }

        public void setLastupdate( String lastupdate ) {
            this.lastupdate = lastupdate;
        }
    }
    public static final class Visibility {
        private float ispublic;
        private float isfriend;
        private float isfamily;


        // Getter Methods

        public float getIspublic() {
            return ispublic;
        }

        public float getIsfriend() {
            return isfriend;
        }

        public float getIsfamily() {
            return isfamily;
        }

        // Setter Methods

        public void setIspublic( float ispublic ) {
            this.ispublic = ispublic;
        }

        public void setIsfriend( float isfriend ) {
            this.isfriend = isfriend;
        }

        public void setIsfamily( float isfamily ) {
            this.isfamily = isfamily;
        }
    }
    public static final class Description {
        private String _content;


        // Getter Methods

        public String get_content() {
            return _content;
        }

        // Setter Methods

        public void set_content( String _content ) {
            this._content = _content;
        }
    }
    public static final class Title {
        private String _content;


        // Getter Methods

        public String get_content() {
            return _content;
        }

        // Setter Methods

        public void set_content( String _content ) {
            this._content = _content;
        }
    }
    public static final class Owner {
        private String nsid;
        private String username;
        private String realname;
        private String location;
        private String iconserver;
        private float iconfarm;
        private String path_alias;


        // Getter Methods

        public String getNsid() {
            return nsid;
        }

        public String getUsername() {
            return username;
        }

        public String getRealname() {
            return realname;
        }

        public String getLocation() {
            return location;
        }

        public String getIconserver() {
            return iconserver;
        }

        public float getIconfarm() {
            return iconfarm;
        }

        public String getPath_alias() {
            return path_alias;
        }

        // Setter Methods

        public void setNsid( String nsid ) {
            this.nsid = nsid;
        }

        public void setUsername( String username ) {
            this.username = username;
        }

        public void setRealname( String realname ) {
            this.realname = realname;
        }

        public void setLocation( String location ) {
            this.location = location;
        }

        public void setIconserver( String iconserver ) {
            this.iconserver = iconserver;
        }

        public void setIconfarm( float iconfarm ) {
            this.iconfarm = iconfarm;
        }

        public void setPath_alias( String path_alias ) {
            this.path_alias = path_alias;
        }
    }
}
