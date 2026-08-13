package com.v2ray.ang.handler

import android.content.Context
import com.v2ray.ang.AppConfig
import com.v2ray.ang.dto.UrlContentRequest
import com.v2ray.ang.util.HttpUtil
import com.v2ray.ang.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Fetches a small "what's new" broadcast message from a remote text file (e.g. a GitHub raw
 * URL), so the developer can push a new announcement to every installed user without needing
 * an app update. Uses the same download-with-proxy-fallback pattern as the update checker.
 */
object AnnouncementManager {

    // TODO: replace with your own raw text file URL, e.g.
    // https://raw.githubusercontent.com/<your-username>/<your-repo>/main/announcement.txt
    private const val ANNOUNCEMENT_URL =
        "https://raw.githubusercontent.com/pekka96/PEKKA_INJECTOR/master/announcement.txt"

    private const val PREF_LAST_SEEN_ANNOUNCEMENT = "pref_last_seen_announcement"

    /**
     * Downloads the announcement text file into cache and returns its trimmed content,
     * or null if it could not be fetched / is empty.
     */
    suspend fun fetchAnnouncement(context: Context): String? = withContext(Dispatchers.IO) {
        try {
            val file = File(context.cacheDir, "announcement.txt")

            var success = HttpUtil.downloadToFile(
                UrlContentRequest(url = ANNOUNCEMENT_URL, timeout = 15000),
                file
            )

            if (!success) {
                success = HttpUtil.downloadToFile(
                    UrlContentRequest(
                        url = ANNOUNCEMENT_URL,
                        timeout = 15000,
                        httpPort = SettingsManager.getHttpPort(),
                        proxyUsername = SettingsManager.getSocksUsername(),
                        proxyPassword = SettingsManager.getSocksPassword()
                    ),
                    file
                )
            }

            if (success && file.exists()) {
                file.readText(Charsets.UTF_8).trim().ifBlank { null }
            } else {
                null
            }
        } catch (e: Exception) {
            LogUtil.e(AppConfig.TAG, "Failed to fetch announcement: ${e.message}")
            null
        }
    }

    /** The last announcement text the user has already opened/dismissed. */
    fun getLastSeenAnnouncement(): String =
        MmkvManager.decodeSettingsString(PREF_LAST_SEEN_ANNOUNCEMENT, "") ?: ""

    /** Marks the given announcement text as read, so the badge won't show again for it. */
    fun setLastSeenAnnouncement(message: String) {
        MmkvManager.encodeSettings(PREF_LAST_SEEN_ANNOUNCEMENT, message)
    }
}