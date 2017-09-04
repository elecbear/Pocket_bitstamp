package bitstamp_object;

import java.math.BigDecimal;

/**
 * Created by elecbear on 9/2/2017.
 */

public class BitstampOrder {

    private BigDecimal[] values;

    public BitstampOrder(BigDecimal[] values){
        this.values = values;
    }
/*
    private BigDecimal price;
    private BigDecimal amount;

    public BitstampOrder(BigDecimal price, BigDecimal amount){
        this.price = price;
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
*/

}
