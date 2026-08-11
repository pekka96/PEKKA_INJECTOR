package com.v2ray.ang.ui.main

import androidx.compose.foundation.pager.PagerState
import kotlin.math.abs

suspend fun PagerState.navigateToPageOptimized(
    targetPage: Int,
    animateAdjacentPage: Boolean = true
) {
    if (currentPage == targetPage) return

    val isAdjacent = abs(targetPage - currentPage) <= 1
    if (animateAdjacentPage || isAdjacent) {
        animateScrollToPage(targetPage)
    } else {
        scrollToPage(targetPage)
    }
}