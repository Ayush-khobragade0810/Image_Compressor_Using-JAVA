# Image Compression Web Application

## Overview
The **Image Compression Web Application** is a Java-based platform designed to reduce the size of image files without compromising visual quality. This application supports multiple image formats and provides a simple, user-friendly interface to upload, compress, and download optimized images. It is developed using **Servlets**, and the **Image I/O API**.

## Features
- **Multi-Format Support**: Compresses images in formats like JPEG, PNG, BMP, and GIF.
- **Custom Quality Settings**: Allows users to select compression levels based on requirements.
- **Fast and Efficient Processing**: Server-side compression ensures smooth performance.
- **User-Friendly Interface**: Simplified design for easy navigation.
- **Secure Uploads**: Protects uploaded files with server-side validation.

## Technologies Used
- **Backend**: Java, JSP, Servlets
- **Frontend**: HTML, CSS, JavaScript
- **API**: Java Image I/O
- **Server**: Apache Tomcat

## Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/username/image-compression-app.git
   ```
2. **Open in IDE**:
   - Use IDEs like Eclipse or IntelliJ IDEA.
3. **Configure Server**:
   - Add Apache Tomcat Server to your IDE.
4. **Add Dependencies**:
   - Ensure Java JDK 19 and Servlet APIs are configured.
5. **Build and Deploy**:
   - Compile the code and deploy the WAR file to the server.

## Usage
1. **Upload an Image**: Choose an image file from your local storage.
2. **Set Compression Level**: Adjust the slider to define compression quality.
3. **Compress Image**: Click on the "Compress" button to process the file.
4. **Download Optimized Image**: Save the compressed image to your device.

## Screenshots
![Upload Page](screenshots/upload.png)
![Compression Result](screenshots/result.png)

## Future Enhancements
- Integration with **cloud storage** for remote access.
- Support for **batch compression** of multiple files.
- Implementation of **AI-based optimization** algorithms.
