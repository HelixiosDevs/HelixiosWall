package com.helixios.helixioswall.networking;

import com.helixios.helixioswall.model.SearchPhotos;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {
//This interface declares method which will be built by retrofit to get required data through api calls.
    //https://www.flickr.com/services/rest/?method=flickr.groups.pools.getPhotos&api_key=e90d3ec2187a5e2ca38e9cde3914a600&group_id=14741456%40N25&extras=url_o&format=json&nojsoncallback=1
    @GET("services/rest/?method=flickr.photos.search&api_key=e90d3ec2187a5e2ca38e9cde3914a600&tags=helixios-wall%2Chelixios&tag_mode=any&per_page=499&extras=url_o%2Cowner_name&format=json&nojsoncallback=1")
    Call<SearchPhotos> getHomePhotos();

//    @GET("services/rest/?method=flickr.groups.pools.getPhotos&api_key=e90d3ec2187a5e2ca38e9cde3914a600&group_id=14741456%40N25&extras=url_o%2Coriginal_format%2Cowner_name&format=json&nojsoncallback=1")
//    Call<SearchPhotos> getHomePhotos();

    @GET("services/rest/?method=flickr.groups.pools.getPhotos&api_key=e90d3ec2187a5e2ca38e9cde3914a600&group_id=14741456%40N25&extras=owner_name%2Curl_o%2Coriginal_format&per_page=499&format=json&nojsoncallback=1")
    Call<SearchPhotos> getCategoryPhotos(@Query("tags") String tags, @Query("user_id") String user_id);
}
