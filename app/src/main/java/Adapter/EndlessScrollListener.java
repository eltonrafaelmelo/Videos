package Adapter;

import android.widget.AbsListView;

/**
 * Created by elton.melo on 03/05/2016.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 3;
    private int currentPage = 0;
    private int currentTotalItems = 0;
    private int firstItemPageIndex = 0;
    private boolean loading = false;

    private OnLoadMoreListener loadMoreListener;

    public EndlessScrollListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (totalItemCount < currentTotalItems) {
            this.currentPage = this.firstItemPageIndex;
            this.currentTotalItems = totalItemCount;
            if (totalItemCount == 0) { this.loading = true; }
        }

        if (loading && (totalItemCount > currentTotalItems)) {
            loading = false;
            currentTotalItems = totalItemCount;
            currentPage++;
        }

        if (!loading && (totalItemCount - visibleItemCount)<=(firstVisibleItem + visibleThreshold)) {
            try {
                loadMoreListener.onLoadMore(currentPage + 1, totalItemCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loading = true;
        }
    }

    public interface OnLoadMoreListener {
        public void onLoadMore(int page, int totalItemsCount) throws InterruptedException;
    }
}
