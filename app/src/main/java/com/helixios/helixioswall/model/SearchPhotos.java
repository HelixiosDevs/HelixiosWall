package com.helixios.helixioswall.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchPhotos {
    @SerializedName("photos")
    private PhotosNest mPhotosNest;
    private String stat;

    public SearchPhotos(PhotosNest photosNest, String stat) {
        mPhotosNest = photosNest;
        this.stat = stat;
    }

    public PhotosNest getPhotosNest() {
        return mPhotosNest;
    }

    public void setPhotosNest(PhotosNest photosNest) {
        mPhotosNest = photosNest;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public static final class PhotosNest {

        private int page;
        private int pages;

        public PhotosNest(int page, int pages, int perpage, String total, List<Photo> photos_list) {
            this.page = page;
            this.pages = pages;
            this.perpage = perpage;
            this.total = total;
            this.photos_list = photos_list;
        }

        private int perpage;
        private String total;
        @SerializedName("photo")
        private List<Photo> photos_list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPerpage() {
            return perpage;
        }

        public void setPerpage(int perpage) {
            this.perpage = perpage;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<Photo> getPhotos_list() {
            return photos_list;
        }

        public void setPhotos_list(List<Photo> photos_list) {
            this.photos_list = photos_list;
        }
    }
}
