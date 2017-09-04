package bitstamp_object;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by elecbear on 9/3/2017.
 */

public class BitstampOrderBook {

    private long timestamp;
    private List<List<BigDecimal>> bids;
    private List<List<BigDecimal>> asks;

    /*
    * Constructor
    *
    * @param timestamp
    * @param bids
    * @param asks
    *
    */


    public BitstampOrderBook(long timestamp, List<List<BigDecimal>> bids, List<List<BigDecimal>> asks){
        this.timestamp = timestamp;
        this.bids = bids;
        this.asks = asks;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<List<BigDecimal>> getBids() {
        return bids;
    }

    public void setBids(List<List<BigDecimal>> bids) {
        this.bids = bids;
    }

    public List<List<BigDecimal>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<BigDecimal>> asks) {
        this.asks = asks;
    }

}
