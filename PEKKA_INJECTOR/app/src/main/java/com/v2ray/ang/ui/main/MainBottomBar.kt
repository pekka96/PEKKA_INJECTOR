package com.v2ray.ang.ui.main

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.v2ray.ang.R

// PEKKA Gradient Theme Colors
private val pekkaPurpleDark = Color(0xFF1A0033)
private val pekkaPurpleMid = Color(0xFF3D0A66)
private val pekkaPurpleLight = Color(0xFF9C27B0)
private val pekkaViolet = Color(0xFF7B1FA2)
private val pekkaGlow = Color(0xFFB388FF)
private val pekkaCyan = Color(0xFF4FC3F7)
private val pekkaGreen = Color(0xFF69F0AE)
private val pekkaInactive = Color(0xFF3A3A3A)

@Composable
fun MainBottomBar(
    displayText: String,
    isRunning: Boolean,
    isDarkTheme: Boolean,
    onAction: (MainAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(pekkaPurpleDark) // 👈 FIX: bottom black area eka mekin fix wenawa
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // Outer glow ring behind the pill
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .clip(RoundedCornerShape(36.dp))
                .border(
                    width = 1.5.dp,
                    brush = Brush.horizontalGradient(
                        listOf(pekkaGlow.copy(alpha = 0.9f), pekkaViolet.copy(alpha = 0.4f))
                    ),
                    shape = RoundedCornerShape(36.dp)
                )
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(pekkaPurpleDark, pekkaPurpleMid)
                    )
                )
                .clickable(onClick = { onAction(MainAction.TestCurrentServer) })
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Shield icon circle
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(pekkaPurpleDark)
                        .border(
                            width = 1.5.dp,
                            color = if (isRunning) pekkaGlow else pekkaInactive,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_privacy_24dp),
                        contentDescription = null,
                        tint = if (isRunning) pekkaGlow else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isRunning) "Connected" else "Not connected",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = displayText,
                        color = Color.White.copy(alpha = 0.65f),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                }

                // Animated pulse line (only while running)
                if (isRunning) {
                    val infiniteTransition = rememberInfiniteTransition(label = "lineFlow")
                    val flowAlpha by infiniteTransition.animateFloat(
                        initialValue = 0.3f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(900, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "flowAlpha"
                    )
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(2.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        pekkaCyan.copy(alpha = flowAlpha),
                                        pekkaGreen.copy(alpha = flowAlpha)
                                    )
                                )
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Spacer(modifier = Modifier.width(4.dp))

                // Pulse glow ring behind play button
                val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                val pulseScale by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.5f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = "pulseScale"
                )
                val pulseAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.55f,
                    targetValue = 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ),
                    label = "pulseAlpha"
                )

                Box(contentAlignment = Alignment.Center) {
                    if (isRunning) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .scale(pulseScale)
                                .clip(CircleShape)
                                .background(pekkaGlow.copy(alpha = pulseAlpha))
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .zIndex(10f)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                brush = Brush.linearGradient(
                                    listOf(pekkaGlow, pekkaViolet)
                                ),
                                shape = CircleShape
                            )
                            .background(
                                brush = if (isRunning) {
                                    Brush.linearGradient(listOf(pekkaPurpleLight, pekkaViolet))
                                } else {
                                    Brush.linearGradient(listOf(pekkaInactive, pekkaInactive))
                                }
                            )
                            .clickable(onClick = { onAction(MainAction.ToggleService) }),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = if (isRunning) painterResource(R.drawable.ic_stop_24dp)
                            else painterResource(R.drawable.ic_play_24dp),
                            contentDescription = if (isRunning) "Stop" else "Start",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}