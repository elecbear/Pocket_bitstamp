package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.im.elecbear.myapplication.R;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by elecbear on 9/4/2017.
 */

public class BitstampOrderBookAdapter extends RecyclerView.Adapter<BitstampOrderBookAdapter.OrderBookBidViewHolder> {

    private List<List<BigDecimal>> bidList;
    private List<List<BigDecimal>> askList;

    public class OrderBookBidViewHolder extends RecyclerView.ViewHolder{
        public TextView bid_price, bid_amount, ask_price, ask_amount;

        public OrderBookBidViewHolder(View view){
            super(view);
            bid_price = (TextView)view.findViewById(R.id.bid_price);
            bid_amount = (TextView)view.findViewById(R.id.bid_amount);

            ask_price = (TextView)view.findViewById(R.id.ask_price);
            ask_amount = (TextView)view.findViewById(R.id.ask_amount);
        }
    }

    public BitstampOrderBookAdapter(List<List<BigDecimal>> bidList, List<List<BigDecimal>> askList){
        this.bidList = bidList;
        this.askList = askList;
    }

    @Override
    public OrderBookBidViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bitstamp_bid_ask_row, parent, false);

        return new OrderBookBidViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderBookBidViewHolder holder, int position){
        holder.bid_price.setText(bidList.get(position).get(0).toString());
        holder.bid_amount.setText(bidList.get(position).get(1).toString());

        holder.ask_price.setText(askList.get(position).get(0).toString());
        holder.ask_amount.setText(askList.get(position).get(1).toString());
    }
    @Override
    public int getItemCount(){
        return Math.max(bidList.size(),askList.size());
    }
}
