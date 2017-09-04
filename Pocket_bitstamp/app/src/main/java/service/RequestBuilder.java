package service;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

/**
 * Created by elecbear on 8/30/2017.
 */

public class RequestBuilder {
    public static RequestBody LoginBody(String username, String password, String token) {
        return new FormBody.Builder()
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }

    public static HttpUrl buildURL(){
        return new HttpUrl.Builder()
                .scheme("https")
                .host("www.somehostname.com")
                .addPathSegment("pathSegment")
                .addQueryParameter("param1","value1")
                .addEncodedQueryParameter("encodedName","encodedValue")
                .build();
    }

}
