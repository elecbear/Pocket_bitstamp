package service;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by elecbear on 8/30/2017.
 */

public class ApiCall {

    private final OkHttpClient client = new OkHttpClient();

    public void SyncGet() throws Exception{
        Request request = new Request.Builder()
                .url("http://someurl.com")
                .build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()) throw new IOException("Unexpected code" + response);

        Headers responseHeaders = response.headers();
        for(int i = 0; i < responseHeaders.size(); i++){
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        System.out.println(response.body().string());
    }

    public void AsyncGet() throws Exception{
        Request request = new Request.Builder()
                .url("http://someurl.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for(int i = 0, size = responseHeaders.size(); i < size; i++){
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }

    public static String GET(OkHttpClient client, HttpUrl url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public static String POST(OkHttpClient client, HttpUrl url, RequestBody body) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
