package com.helixios.helixioswall.networking;

import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlickrApi {

    @GET("services/rest/?method=flickr.photos.search&api_key=e90d3ec2187a5e2ca38e9cde3914a600&tags=helixios-wall%2Chelixios&tag_mode=any&per_page=499&extras=url_o&format=json&nojsoncallback=1")
    Call<SearchPhotos> getHomePhotos();

    @GET("services/rest/?method=flickr.photos.search&api_key=e90d3ec2187a5e2ca38e9cde3914a600&per_page=499&extras=url_o&format=json&nojsoncallback=1")
    Call<SearchPhotos> getCategoryPhotos();
}
