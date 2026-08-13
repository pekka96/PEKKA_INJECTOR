# PEKKA Injector

A fast, lightweight VPN/proxy client for Android, supporting VLESS, VMess, Shadowsocks, SOCKS, HTTP, Trojan, WireGuard and Hysteria2.

[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)]()
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.0-blue.svg)]()

> Built on top of the open-source [v2rayNG](https://github.com/2dust/v2rayNG) project (GPL-3.0 License). See `LICENSE` for full terms.

**Developed by SM ATHTHANAYAKA**

---

## Download

Download the latest release here:

**[Releases →](https://github.com/pekka96/PEKKA_INJECTOR/releases)**

---

## Features

- **Multi-protocol support** — VLESS, VMess, Shadowsocks, SOCKS, HTTP, Trojan, WireGuard, Hysteria2
- **Auto-update system** — checks for new app versions and installs updates directly, no browser needed
- **In-app announcements** — a floating message icon shows important updates and news from the developer
- **Per-app proxy** — choose which apps route through the VPN and which don't
- **Subscription support** — import multiple server configs at once from a subscription link
- **QR code & clipboard import** — add servers by scanning a QR code or pasting a config link
- **Dark mode** — comfortable UI for day and night use
- **Routing rules** — customize how traffic is routed based on domain or IP rules
- **Real ping test** — check latency to your servers before connecting

---

## How to Use

1. **Import a server** — tap the **+** button and choose one of:
   - **Scan QR code** — point your camera at a config QR code
   - **Import from clipboard** — copy a config link (`vless://`, `vmess://`, etc.) then tap this option
   - **Manual entry** — fill in server details by hand
2. **Grant permissions** — on first connect, allow the VPN permission prompt (and notification permission if asked)
3. **Select a server** — tap a server card from your list to select it
4. **Connect** — tap the **Play** button at the bottom to start the VPN
5. **Disconnect** — tap the same button again to stop

---

## Credits

This project is a fork of [v2rayNG](https://github.com/2dust/v2rayNG) by 2dust and contributors, licensed under GPL-3.0. Core proxy/tunnel functionality (Xray core, v2fly core) originates from the upstream project. Customizations in this fork — including the auto-update system, in-app announcements, and UI changes — were developed by **SM ATHTHANAYAKA**.

Full license text: [`LICENSE`](./LICENSE)

---

## Disclaimer

This app is provided as-is for personal privacy and educational purposes. Users are solely responsible for ensuring their use of this app complies with their local laws and the terms of service of their network/service provider. The developer assumes no liability for misuse.

---
