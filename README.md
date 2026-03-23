# 🔐 Secure Message & File Transfer System

## 📌 Overview

This project is a secure communication system that enables users to send messages and files safely. The system ensures data confidentiality by encrypting all messages and files using AES before storing them in the database. Authentication is implemented using JWT to restrict access to authorized users only.

---

## 🚀 Features

* 🔐 User Authentication using JWT
* 🔑 Password encryption using BCrypt
* ✉️ Secure message transfer (AES encryption)
* 📁 Secure file upload and download (AES encryption)
* 🛡️ Access control (only receiver can access data)
* 📏 File validation (size limit and allowed formats)
* 🗂️ Sorted inbox (latest messages/files first)
* 🔒 Sensitive data hidden from API responses

---

## 🛠️ Tech Stack

**Backend:**

* Java
* Spring Boot
* Spring Security
* Hibernate / JPA

**Database:**

* MySQL

**Security:**

* JWT Authentication
* AES Encryption

---

## 🔐 Security Design

* Messages and files are encrypted using AES before storing in the database
* Each message/file uses a unique encryption key
* Only authenticated users can access the system
* Access is restricted to intended recipients
* Sensitive data (AES keys, encrypted content) is not exposed via APIs

---

## 📡 API Endpoints

### 🔑 Authentication

* `POST /auth/register`
* `POST /auth/login`

### ✉️ Messages

* `POST /messages/send`
* `GET /messages/inbox`
* `GET /messages/{id}`

### 📁 Files

* `POST /files/upload`
* `GET /files/inbox`
* `GET /files/download/{id}`

---

## ⚙️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/bhavitha092005/secure-transfer-system.git
   ```

2. Navigate to project folder:

   ```bash
   cd secure-transfer-system
   ```

3. Configure database in `application.properties`

4. Run the Spring Boot application

5. Use Postman to test APIs

---

## 📁 Project Structure

secure-transfer-system/
│
├── src/main/java/com/secure/transfer/
│   ├── controller/       # Handles API requests
│   ├── service/          # Business logic (encryption, processing)
│   ├── repository/       # Database interactions
│   ├── entity/           # JPA entities (User, Message, File)
│   ├── security/         # JWT, filters, security configuration
│   ├── util/             # Utility classes (AES encryption)
│   └── dto/              # Request/response objects
│
├── src/main/resources/
│   ├── application.properties   # Configuration (DB, server, etc.)
│
├── pom.xml               # Project dependencies
├── mvnw / mvnw.cmd      # Maven wrapper
└── README.md            # Project documentation

## 📸 Demo (Optional)

You can demonstrate:

* User login
* Message encryption & retrieval
* File upload & download



## ⚠️ Limitations

* Encryption keys are stored server-side for simplicity
* Not a full end-to-end encryption system

---

## 🔮 Future Improvements

* End-to-end encryption
* Secure key exchange
* Frontend UI (React)
* File preview support

---

## 👨‍💻 Author

**Bhavitha Pala**
Aspiring Full Stack Developer
