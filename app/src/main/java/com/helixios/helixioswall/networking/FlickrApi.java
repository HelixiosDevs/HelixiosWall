package com.helixios.helixioswall.networking;

import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlickrApi {

    @GET("services/rest/?method=flickr.photos.search&api_key=e90d3ec2187a5e2ca38e9cde3914a600&tags=nier&extras=url_o&format=json&nojsoncallback=1")
    Call<SearchPhotos> getHomePhotos();

}
