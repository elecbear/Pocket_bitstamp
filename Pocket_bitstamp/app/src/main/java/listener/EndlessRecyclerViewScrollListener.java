package listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by elecbear on 9/3/2017.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener  {

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager){
        mLayoutManager = layoutManager;
    }

    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager){
        mLayoutManager = layoutManager;
        visibleThreshold =  visibleThreshold * layoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions){
        int maxSize = 0;

        for(int i = 0; i < lastVisibleItemPositions.length; i++){
            if(i == 0){
                maxSize = lastVisibleItemPositions[i];
            }

            else if(lastVisibleItemPositions[i] > maxSize){
                maxSize = lastVisibleItemPositions[i];
            }
        }

        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy){
        int lastVisibleItemPosition = 0;

        int totalItemCOunt = mLayoutManager.getItemCount();

        if(mLayoutManager instanceof LinearLayoutManager){
            lastVisibleItemPosition = ((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();
        }

        if(totalItemCOunt < previousTotalItemCount){
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCOunt;
            if(totalItemCOunt == 0){
                this.loading = true;
            }
        }

        if(loading && (totalItemCOunt > previousTotalItemCount)){
            loading = false;
            previousTotalItemCount = totalItemCOunt;
        }

        if(!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCOunt){
            currentPage++;
            onLoadMore(currentPage, totalItemCOunt, view);
            loading = true;

        }
    }

    public void resetState(){
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
