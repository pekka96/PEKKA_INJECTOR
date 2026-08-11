package com.v2ray.ang.ui

import android.os.Bundle
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.v2ray.ang.AppConfig
import com.v2ray.ang.BuildConfig
import com.v2ray.ang.R
import com.v2ray.ang.ui.base.BaseComponentActivity
import com.v2ray.ang.ui.compose.AppTopBar
import com.v2ray.ang.ui.compose.SettingsMenuItem
import com.v2ray.ang.ui.compose.VersionInfoBlock
import com.v2ray.ang.util.Utils

class AboutActivity : BaseComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    override fun ScreenContent() {
        AboutScreen(onBackClick = { finish() })
    }
}

@Composable
fun AboutScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    var showOssDialog by remember { mutableStateOf(false) }
    var showAboutUsDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }

    val versionText = "PEKKA INJECTOR v2.1 (SM ATHTHANAYAKA)"
    val appIdText = BuildConfig.APPLICATION_ID

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            AppTopBar(
                title = stringResource(R.string.title_about),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            SettingsMenuItem(
                icon = painterResource(R.drawable.license_24px),
                title = "PEKKA INJECTOR 2026",
                onClick = { showOssDialog = true }
            )
            SettingsMenuItem(
                icon = painterResource(R.drawable.ic_privacy_24dp),
                title = "About Us",
                onClick = { showAboutUsDialog = true }
            )
            SettingsMenuItem(
                icon = painterResource(R.drawable.ic_privacy_24dp),
                title = stringResource(R.string.title_privacy_policy),
                onClick = { showPrivacyDialog = true }
            )
            SettingsMenuItem(
                icon = painterResource(R.drawable.license_24px),
                title = "Terms & Conditions",
                onClick = { showTermsDialog = true }
            )
            VersionInfoBlock(
                versionText = versionText,
                appIdText = appIdText
            )
        }
    }

    if (showOssDialog) {
        AlertDialog(
            onDismissRequest = { showOssDialog = false },
            title = { Text("PEKKA INJECTOR 2026") },
            text = {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            loadUrl("file:///android_asset/open_source_licenses.html")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp)
                )
            },
            confirmButton = {
                TextButton(onClick = { showOssDialog = false }) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(bottom = 60.dp)
        )
    }

    if (showAboutUsDialog) {
        AlertDialog(
            onDismissRequest = { showAboutUsDialog = false },
            title = { Text("About Us") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 480.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.founder_photo),
                            contentDescription = "Founder",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                        )
                    }

                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 8.dp))

                    Text(
                        text = "SM Aththanayaka",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Text(
                        text = "Founder, PEKKA INJECTOR",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    LegalSectionText(
                        text = """
                            Welcome to PEKKA INJECTOR, a fast, secure, and reliable VPN application designed to provide users with a private and unrestricted internet experience.

                            Founded in 2026 by SM Aththanayaka, PEKKA INJECTOR was created with a simple mission: to make online privacy, security, and fast connectivity available to everyone. Whether you're browsing the web, streaming content, gaming, or using social media, our VPN is built to deliver a smooth, stable, and secure connection.

                            Our advanced VPN technology helps protect your online activity while providing high-speed servers and reliable performance. We are committed to delivering a simple and user-friendly experience for everyone.

                            Features:
                            • Fast & Stable VPN Servers
                            • Secure & Encrypted Connection
                            • Unlimited VPN Usage
                            • Easy One-Tap Connection
                            • User-Friendly Interface
                            • Reliable Performance
                            • Regular Updates & Improvements

                            Our Mission:
                            Our mission is to provide a secure, fast, and reliable VPN service that helps users enjoy a safer and more private internet experience without unnecessary complexity.

                            Our Vision:
                            We aim to become a trusted VPN platform recognized worldwide for speed, security, reliability, and user satisfaction.

                            Connect With Us:
                            Email: smaththanayaka08@gmail.com
                            LinkedIn: linkedin.com/in/madu-shanka-850496363
                            Facebook: facebook.com/share/19GUM4ib4x
                            Country: Sri Lanka

                            Thank you for choosing PEKKA INJECTOR. We appreciate your trust and will continue improving our services to provide the best VPN experience possible.
                        """.trimIndent()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showAboutUsDialog = false }) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(bottom = 60.dp)
        )
    }

    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = { Text("Privacy Policy") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 480.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    LegalSectionText(
                        text = """
                            Effective Date: August 7, 2026

                            Welcome to PEKKA INJECTOR. Your privacy is important to us. This Privacy Policy explains how we collect, use, and protect your information when you use our application.

                            1. Information We Collect
                            PEKKA INJECTOR is designed to protect your privacy. We may collect limited information necessary to provide and improve our services, including device information, app performance and crash reports, anonymous usage statistics, and network diagnostic information when required for troubleshooting.

                            We do not intentionally collect your personal files, photos, messages, contacts, or passwords.

                            2. VPN Usage
                            PEKKA INJECTOR is committed to protecting your privacy. We do not monitor, record, or sell your browsing activity. We do not intentionally store the websites you visit, your online activities, or the content of your internet traffic.

                            3. How We Use Information
                            Any information we collect may be used to provide and maintain the VPN service, improve app performance and stability, fix bugs and technical issues, enhance user experience, and prevent abuse and protect the security of our services.

                            4. Third-Party Services
                            Our application may use trusted third-party services such as analytics, crash reporting, or advertising providers. These services may collect information according to their own privacy policies.

                            5. Data Security
                            We use reasonable technical and organizational measures to help protect your information against unauthorized access, loss, misuse, or disclosure. However, no method of transmission or storage is completely secure.

                            6. Children's Privacy
                            PEKKA INJECTOR is not intended for children under the age of 13. We do not knowingly collect personal information from children.

                            7. Changes to This Privacy Policy
                            We may update this Privacy Policy from time to time. Any changes will be posted within the application or on our official website. Continued use of the app after updates constitutes acceptance of the revised Privacy Policy.

                            8. Contact Us
                            If you have any questions or concerns regarding this Privacy Policy, please contact us.

                            Application: PEKKA INJECTOR
                            Developer: SM Aththanayaka
                            Country: Sri Lanka
                            Email: smaththanayaka08@gmail.com

                            Thank you for using PEKKA INJECTOR. We are committed to protecting your privacy and providing a secure VPN experience.
                        """.trimIndent()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showPrivacyDialog = false }) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(bottom = 60.dp)
        )
    }

    if (showTermsDialog) {
        AlertDialog(
            onDismissRequest = { showTermsDialog = false },
            title = { Text("Terms & Conditions") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 480.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    LegalSectionText(
                        text = """
                            Effective Date: 2026

                            Welcome to PEKKA INJECTOR. By downloading or using our application, you agree to the following Terms & Conditions.

                            1. Acceptance of Terms
                            By using PEKKA INJECTOR, you agree to comply with these Terms & Conditions. If you do not agree, please discontinue use of the application.

                            2. Lawful Use
                            You agree to use the application only for lawful purposes. You are solely responsible for complying with the laws and regulations of your country.

                            3. User Responsibility
                            Users are responsible for how they use the VPN service. PEKKA INJECTOR is not responsible for any illegal activities performed through the application.

                            4. Service Availability
                            We strive to provide a reliable service but do not guarantee uninterrupted availability. Servers may be updated, changed, or temporarily unavailable without prior notice.

                            5. Intellectual Property
                            All trademarks, logos, branding, and application content belong to PEKKA INJECTOR unless otherwise stated. Unauthorized copying or redistribution is prohibited.

                            6. Privacy
                            Your use of the application is also governed by our Privacy Policy.

                            7. Changes
                            We reserve the right to modify these Terms & Conditions at any time. Continued use of the application after changes means you accept the updated terms.

                            8. Contact
                            For any questions regarding these Terms & Conditions, please contact:

                            Email: smaththanayaka08@gmail.com
                            Country: Sri Lanka
                            Developer: SM Aththanayaka
                        """.trimIndent()
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showTermsDialog = false }) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(bottom = 60.dp)
        )
    }
}

@Composable
private fun LegalSectionText(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        lineHeight = 19.sp,
        modifier = Modifier.fillMaxWidth()
    )
}