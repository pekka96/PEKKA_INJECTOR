package com.v2ray.ang.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.background
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.v2ray.ang.handler.AnnouncementManager

@Composable
fun MainAnnouncementFab() {
    val context = LocalContext.current
    var announcement by remember { mutableStateOf<String?>(null) }
    var hasUnread by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val message = AnnouncementManager.fetchAnnouncement(context)
        if (!message.isNullOrBlank()) {
            announcement = message
            hasUnread = message != AnnouncementManager.getLastSeenAnnouncement()
        }
    }

    if (announcement != null) {
        Box {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(imageVector = Icons.Filled.Chat, contentDescription = "Announcement")
            }
            if (hasUnread) {
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(10.dp)
                        .align(Alignment.TopEnd)
                        .background(color = MaterialTheme.colorScheme.error, shape = CircleShape)
                )
            }
        }
    }

    if (showDialog && announcement != null) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                announcement?.let { AnnouncementManager.setLastSeenAnnouncement(it) }
                hasUnread = false
            },
            title = { Text("Announcement") },
            text = { Text(announcement.orEmpty()) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    announcement?.let { AnnouncementManager.setLastSeenAnnouncement(it) }
                    hasUnread = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}