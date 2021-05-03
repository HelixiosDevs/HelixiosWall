package com.helixios.helixioswall.model;

import java.util.List;

import androidx.annotation.Nullable;

public class PhotoSizes {

    @Nullable
    private PhotoSizes.SizesNest mSizes;
    @Nullable
    private String stat;

    public static final class SizesNest {
        private Boolean canblog;
        private Boolean canprint;
        private Boolean candownload;
        private List<size> mSizesList;

        public List<size> getSizesList() {
            return mSizesList;
        }

        public void setSizesList(List<size> sizesList) {
            mSizesList = sizesList;
        }

        public Boolean getCanblog() {
            return canblog;
        }

        public void setCanblog(Boolean canblog) {
            this.canblog = canblog;
        }

        public Boolean getCanprint() {
            return canprint;
        }

        public void setCanprint(Boolean canprint) {
            this.canprint = canprint;
        }

        public Boolean getCandownload() {
            return candownload;
        }

        public void setCandownload(Boolean candownload) {
            this.candownload = candownload;
        }

        public static final class size {
            private String label;
            private Integer height;
            private Integer width;
            private String source;
            private String url;
            private String media;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public Integer getHeight() {
                return height;
            }

            public void setHeight(Integer height) {
                this.height = height;
            }

            public Integer getWidth() {
                return width;
            }

            public void setWidth(Integer width) {
                this.width = width;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }
        }
    }
}
