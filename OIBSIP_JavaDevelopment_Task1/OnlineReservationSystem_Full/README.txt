Online Reservation System - Modern UI (Frontend only)


# Online Reservation System

A modern, professional Java Swing-based desktop application for managing online reservations with a beautiful teal-themed UI.

![Java](https://img.shields.io/badge/Java-SE_8+-blue.svg)
![Swing](https://img.shields.io/badge/Swing-GUI-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## Features

### User Management
-  **User Registration** - Create new accounts with name, mobile, and password
-  **User Login** - Secure authentication with mobile number and password
-  **Session Management** - Persistent user sessions throughout the application

### Reservation System
-  **Ticket Booking** - Book tickets with passenger details, stations, date, and coach type
-  **Multiple Coach Types** - Choose from Sleeper, Normal, AC Coach, or First Class
-  **Unique Ticket IDs** - Auto-generated unique ticket identifiers
- **Ticket Cancellation** - Cancel existing bookings with confirmation dialog

### Modern UI/UX
-  **Professional Design** - Clean, modern interface with teal color scheme
-  **Responsive Layout** - 900x700 window with centered 650px cards
-  **Focus Effects** - Input fields highlight with teal borders when clicked
-  **Hover Effects** - All buttons respond with color changes on hover
-  **Custom Navigation** - Top navigation bar with Home, Reservation, Cancel, and Logout
-  **Custom Airplane Icon** - Hand-drawn vector airplane logo
-  **System Native Look** - Adapts to your operating system's appearance

## Screenshots

### Welcome Screen
- Airplane logo with "Welcome to Online Reservation"
- Sign In and Create Account buttons
- Subtitle: "Fast • Simple • Elegant"

### Login Page
- Phone Number and Password fields
- Teal focus effects on inputs
- Sign In button with hover effect

### Signup Page
- Full Name, Mobile Number, Password, Confirm Password fields
- Form validation for password matching
- Create Account button

### Reservation Page
- Passenger Name, From Station, To Station fields
- Travel Date and Coach Type selection
- Book Ticket button (generates unique ticket ID)
- Cancel Existing Ticket button (redirects to cancellation)

### Cancellation Page
- Warning icon (⚠)
- Ticket ID input field
- Confirmation dialog before cancellation
- Back to Reservation button

##  Design System

### Color Palette
| Color | Hex | Usage |
|-------|-----|-------|
| **Teal** | #009688 | Primary buttons, focus states, branding |
| **Dark Teal** | #00796B | Button hover states |
| **Light Blue** | #F0F8FF | Background color |
| **White** | #FFFFFF | Card backgrounds |
| **Dark Gray** | #212529 | Headings and titles |
| **Gray** | #6C757D | Subtitles and secondary text |
| **Red** | #DC3545 | Cancel/danger buttons |
| **Soft Blue** | #C8DCF0 | Card borders |

### Typography
| Element | Font | Size | Weight |
|---------|------|------|--------|
| **Titles** | Segoe UI | 24-26px | Bold (700) |
| **Subtitles** | Segoe UI | 13-15px | Regular (400) |
| **Labels** | Segoe UI | 13px | Bold (700) |
| **Inputs** | Segoe UI | 14px | Regular (400) |
| **Buttons** | Segoe UI | 15-16px | Bold (700) |

### Component Sizes
| Component | Width | Height |
|-----------|-------|--------|
| **Window** | 900px | 700px |
| **Cards** | 650px | 380-580px (varies) |
| **Input Fields** | 350-380px | 42px |
| **Primary Buttons** | 230-250px | 44-48px |
| **Secondary Buttons** | 230-280px | 36-38px |

## Installation & Setup

### Prerequisites
- **Java SE 8 or higher** installed on your system
- Basic knowledge of command line operations

### Quick Start

1. **Download/Clone the Project**


How to run:
1. Open terminal and navigate to src folder
   cd OnlineReservationSystem_Full/src
2. Compile:
   javac *.java
3. Run:
   java Main


If using Git
git clone <repository-url>
cd online-reservation-system

Or download and extract the ZIP file


2. **Verify All Files**
Ensure you have these 8 Java files:
- `Main.java`
- `User.java`
- `WelcomePanel.java`
- `LoginPanel.java`
- `SignupPanel.java`
- `ReservationPanel.java`
- `CancellationPanel.java`
- `TopNavPanel.java`

3. **Compile**

4. **Run**
