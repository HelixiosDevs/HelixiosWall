package com.helixios.helixioswall;

import static org.junit.Assert.assertEquals;

import com.helixios.helixioswall.model.Photo;

import org.junit.Test;

public class PhotoUrlTest {

    private static Photo photo(String urlO) {
        return new Photo("123", "owner", "abc", "65535", 66, "title", 1, 0, 0, null, null, null,
                null, null, urlO, null, null, 0, 0, "name", null);
    }

    @Test
    public void buildsLiveStaticUrlsWhenExtrasMissing() {
        Photo p = photo(null);
        assertEquals("https://live.staticflickr.com/65535/123_abc_z.jpg", p.getUrl_z());
        assertEquals("https://live.staticflickr.com/65535/123_abc_b.jpg", p.getDisplayUrl());
        assertEquals("https://live.staticflickr.com/65535/123_abc_b.jpg", p.getBestDownloadUrl());
    }

    @Test
    public void prefersLargestAccessibleSizeForDownload() {
        Photo p = photo(null);
        p.setUrl_l("l.jpg");
        p.setUrl_h("h.jpg");
        assertEquals("h.jpg", p.getBestDownloadUrl());
        assertEquals("l.jpg", p.getDisplayUrl());

        p.setUrl_k("k.jpg");
        assertEquals("k.jpg", p.getBestDownloadUrl());

        assertEquals("o.png", photo("o.png").getBestDownloadUrl());
    }
}
