package com.helixios.helixioswall.networking;

import com.helixios.helixioswall.model.SearchPhotos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {
//This interface declares method which will be built by retrofit to get required data through api calls.

    // Size extras: z=640, c=800, l=1024, h=1600, k=2048, o=original. Flickr only returns the
    // sizes this key may access; h/k/o are withheld for Free-account photos (since May 2025)
    // or when the owner restricts them, so Photo falls back to smaller sizes.
    String EXTRAS = "owner_name%2Coriginal_format%2Curl_z%2Curl_c%2Curl_l%2Curl_h%2Curl_k%2Curl_o";

    // per_page is capped at 500 by Flickr
    @GET("services/rest/?method=flickr.groups.pools.getPhotos&group_id=14741456%40N25&extras=" + EXTRAS + "&per_page=500&format=json&nojsoncallback=1")
    Call<SearchPhotos> getHomePhotos();

    // media=photos keeps videos out of the wallpaper lists
    @GET("services/rest/?method=flickr.photos.search&media=photos&extras=" + EXTRAS + "&per_page=500&format=json&nojsoncallback=1")
    Call<SearchPhotos> getCategoryPhotos(@Query("tags") String tags, @Query("user_id") String user_id);
}