# Student Form — Android Jetpack Compose

A single-screen Android app built with Jetpack Compose that collects student info with a unique **brutalist / archival** aesthetic: monospace typography, raw ink-on-paper palette, rust and brass accents.

---

## Features

| Feature | Detail |
|---|---|
| Text fields | First Name, Last Name, Email (OutlinedTextField) |
| Date picker | Tap → DatePickerDialog → displays `DD/MM/YYYY` |
| Radio buttons | 5 options: Android, iOS, Web, Backend, ML/AI |
| Switch | "I agree to the terms and conditions" |
| Validation | Toast `"Fill in all fields!"` or `"Data sent!"` |

---

## Design Aesthetic

The UI intentionally avoids standard Material Design:

- **Palette:** Parchment paper (`#F5F0E8`), deep ink (`#0D0D0D`), rust red (`#B84C27`), brass gold (`#D4A24C`), sage green (`#5C7A5F`)
- **Typography:** Monospace / typewriter family throughout
- **Shapes:** Sharp 2dp corner radius (almost square) instead of rounded pills
- **Layout:** Section labels with numbered steps (`01 // 02 //`) and tricolor gradient rule below the header

---

## Setup

1. Clone the repo and open in **Android Studio Hedgehog** (2023.1.1) or newer.
2. Sync Gradle — no extra dependencies beyond the Compose BOM.
3. Run on an emulator (API 24+) or physical device.

```
compileSdk  34
minSdk      24
Kotlin      1.9.10
Compose BOM 2023.10.01
```

---

## Screen Recording

> **Add your screen recording (GIF or MP4) here.**
>
> The recording should show:
> 1. Entering your first and last name in the Name field.
> 2. Pressing Submit with empty fields → Error Toast.
> 3. Opening the calendar and selecting a date.
> 4. Filling in all fields and pressing Submit → Success Toast.

<!-- Replace this comment with your embedded GIF/MP4, e.g.:
![Demo](demo.gif)
-->

---

## Project Structure

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/studentform/
│   └── MainActivity.kt          ← entire UI + logic
└── res/values/
    └── themes.xml
```

All UI lives in `MainActivity.kt` using Composable functions:
- `StudentFormScreen` — root screen, all state variables
- `RawTextField` — labelled OutlinedTextField component
- `SectionLabel` — numbered section heading

---

## State Variables

| Variable | Type | Purpose |
|---|---|---|
| `firstNameState` | String | First name field |
| `lastNameState` | String | Last name field |
| `emailState` | String | Email field |
| `dateState` | String | Selected date (DD/MM/YYYY) |
| `selectedOption` | String | Chosen radio direction |
| `isAgreed` | Boolean | Switch state |

<img width="1268" height="957" alt="2" src="https://github.com/user-attachments/assets/0843be83-217e-48b4-9433-7127d2c60750" />
