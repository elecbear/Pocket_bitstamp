package service;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elecbear on 9/2/2017.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://www.bitstamp.net/api/v2/";

    private static OkHttpClient httpClient =
            new OkHttpClient.Builder().build();

    private static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

}
