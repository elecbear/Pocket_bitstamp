package service;

import bitstamp_object.BitstampOrderBook;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by elecbear on 9/2/2017.
 */

public interface BitstampService {

    @GET("order_book/{currency_pair}/")
    Call<BitstampOrderBook> getOrderBook(@Path("currency_pair") String currency_pair);

}
