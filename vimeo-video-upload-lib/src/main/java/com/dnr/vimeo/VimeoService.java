package com.dnr.vimeo;


import com.dnr.vimeo.model.VideoType;
import com.dnr.vimeo.model.VimeoTicket;
import com.dnr.vimeo.model.VimeoVideoMetaData;
import com.dnr.vimeo.model.VimeoVideoMetaRequest;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface VimeoService {


    @Multipart
    @PUT
    Call<String> videoUpload(
            @Url String url,
            @Part MultipartBody.Part file,
            @HeaderMap Map<String, String> headers
    );

    @POST("/me/videos")
    Call<VimeoTicket> getVimeoVideoUploadTicket(@Body VideoType videoType);

    @PATCH
    Call<VimeoVideoMetaData> patchVimeoVideoMetaData(@Url String url,
                                                     @Body VimeoVideoMetaRequest body);

    @DELETE
    Call<Void> vimeoCleanAfterUpload(@Url String url);



    /* ******************  Vimeo Upload Calls *********************************/




}
