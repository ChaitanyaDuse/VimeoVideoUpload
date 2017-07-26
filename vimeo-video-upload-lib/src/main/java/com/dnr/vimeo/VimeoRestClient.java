package com.dnr.vimeo;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class VimeoRestClient {
    private static VimeoRestClient ourInstance = null;
    private final String VIMEO_BASE_URL = "https://api.vimeo.com";
    private VimeoService vimeoService;


    public static VimeoRestClient getInstance(String apiKey) {
        if (ourInstance == null) {

            ourInstance = new VimeoRestClient(apiKey);
        }
        return ourInstance;
    }

    private VimeoRestClient(String apiKey) {
        buildRetrofit(apiKey);
    }

    private void buildRetrofit(final String apiKey) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Accept", "application/vnd.vimeo.*+json;version=3.2")
                                .addHeader("Authorization", "Bearer " + apiKey).build();

                        return chain.proceed(request);
                    }
                })
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VIMEO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .client(client)
                .build();
        this.vimeoService = retrofit.create(VimeoService.class);
    }

    public VimeoService getVimeoService() {
        return this.vimeoService;
    }
}
