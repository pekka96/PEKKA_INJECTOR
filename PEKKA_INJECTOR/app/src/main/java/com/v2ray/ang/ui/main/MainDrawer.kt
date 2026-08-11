package com.v2ray.ang.ui.main

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.v2ray.ang.R
import com.v2ray.ang.ui.compose.AppDivider
import com.v2ray.ang.ui.compose.verticalScrollbar

@Composable
fun MainDrawerContent(onNavigate: (String) -> Unit) {
    val drawerScrollState = rememberScrollState()

    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .navigationBarsPadding(),
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(drawerScrollState)
                .verticalScrollbar(drawerScrollState)
                .padding(bottom = 80.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    PekkaInjectorGlowTitle()
                }
            }
            DrawerMenuGroup(
                items = listOf(
                    DrawerMenuItemData(R.drawable.ic_subscriptions_24dp, R.string.title_sub_setting, "sub_setting"),
                    DrawerMenuItemData(R.drawable.ic_per_apps_24dp, R.string.per_app_proxy_settings, "per_app_proxy"),
                    DrawerMenuItemData(R.drawable.ic_routing_24dp, R.string.routing_settings_title, "routing_setting"),
                    DrawerMenuItemData(R.drawable.ic_file_24dp, R.string.title_user_asset_setting, "user_asset"),
                    DrawerMenuItemData(R.drawable.ic_settings_24dp, R.string.title_settings, "settings")
                ),
                onNavigate = onNavigate
            )
            AppDivider()
            DrawerMenuGroup(
                items = listOf(
                    DrawerMenuItemData(R.drawable.ic_promotion_24dp, R.string.title_pref_promotion, "promotion"),
                    DrawerMenuItemData(R.drawable.ic_logcat_24dp, R.string.title_logcat, "logcat"),
                    DrawerMenuItemData(R.drawable.ic_check_update_24dp, R.string.update_check_for_update, "check_update"),
                    DrawerMenuItemData(R.drawable.ic_restore_24dp, R.string.title_configuration_backup_restore, "backup_restore"),
                    DrawerMenuItemData(R.drawable.ic_about_24dp, R.string.title_about, "about")
                ),
                onNavigate = onNavigate
            )
        }
    }
}

/**
 * "PEKKA INJECTOR" title with bold font + animated pulsing purple glow.
 */
@Composable
private fun PekkaInjectorGlowTitle() {
    val infiniteTransition = rememberInfiniteTransition(label = "pekka_glow_transition")

    // Pulsing alpha for the glow layer (0.35 -> 1f -> 0.35 loop)
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pekka_glow_alpha"
    )

    // Pulsing blur radius for extra "breathing" glow feel
    val glowRadius by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pekka_glow_radius"
    )

    val glowColor = Color(0xFFB388FF) // purple glow — match this to your bottom-bar glow tone

    Box(contentAlignment = Alignment.Center) {
        // Back layer: blurred, pulsing glow
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = FontFamily(Font(R.font.montserrat_thin)),
                fontWeight = FontWeight.Bold
            ),
            color = glowColor.copy(alpha = glowAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.blur(glowRadius.dp)
        )
        // Front layer: sharp bold text
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = FontFamily(Font(R.font.montserrat_thin)),
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

data class DrawerMenuItemData(
    val iconRes: Int,
    val labelRes: Int,
    val route: String
)

@Composable
private fun DrawerMenuGroup(
    items: List<DrawerMenuItemData>,
    onNavigate: (String) -> Unit
) {
    items.forEach { item ->
        DrawerMenuItem(
            icon = painterResource(item.iconRes),
            label = stringResource(item.labelRes),
            onClick = { onNavigate(item.route) }
        )
    }
}

@Composable
fun DrawerMenuItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(onClick = onClick)
            .background(
                if (selected) MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                else Color.Transparent
            )
            .padding(horizontal = 16.dp, vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}