package com.mahmoud.moviecatalog.extensions

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.isInitializing(): Boolean {
    return (this.refresh is LoadState.NotLoading && !refresh.endOfPaginationReached &&
            this.prepend is LoadState.NotLoading && !prepend.endOfPaginationReached &&
            this.append is LoadState.NotLoading && !append.endOfPaginationReached)
}

fun CombinedLoadStates.isLoadingRefresh(): Boolean {
    return (this.refresh is LoadState.Loading && !refresh.endOfPaginationReached &&
            this.prepend is LoadState.NotLoading && !prepend.endOfPaginationReached &&
            this.append is LoadState.NotLoading && !append.endOfPaginationReached)
}

fun CombinedLoadStates.isDataLoaded(): Boolean {
    return (this.refresh is LoadState.NotLoading && !refresh.endOfPaginationReached &&
            this.prepend is LoadState.NotLoading && prepend.endOfPaginationReached &&
            this.append is LoadState.NotLoading && !append.endOfPaginationReached)
}

fun CombinedLoadStates.isError(): Boolean {
    return this.prepend is LoadState.Error
            || this.append is LoadState.Error
            || this.refresh is LoadState.Error
}

fun CombinedLoadStates.isEmptyState(itemCount: Int): Boolean {
    return isNoMoreData() && itemCount == 0
}

fun CombinedLoadStates.isNoMoreData(itemCount: Int): Boolean {
    return isNoMoreData() && itemCount > 0
}

private fun CombinedLoadStates.isNoMoreData(): Boolean {
    return (this.refresh is LoadState.NotLoading && !refresh.endOfPaginationReached &&
            this.prepend is LoadState.NotLoading && prepend.endOfPaginationReached &&
            this.append is LoadState.NotLoading && append.endOfPaginationReached)
}
