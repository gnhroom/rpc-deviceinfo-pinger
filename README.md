# RPC Network Management System

RPC Network Management System is a Java-based remote procedure call (RPC) application for managing network devices remotely. It provides functionalities such as retrieving device information, performing actions on devices, and displaying network diagnostics.

## Features

- Retrieve detailed information about a network device, including hostname and manufacturer.
- Perform ping, nslookup, and tracert on a specified IP address.
- Execute custom actions on network devices.

## Prerequisites

- Java Development Kit (JDK)
- Apache Commons Lang 3 library (for system information retrieval)
- oshi-core library (for hardware information retrieval)

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/rpc-network-management.git
2. Compile the server and client:
   ```bash
   javac *.java

3. Run the server:
   
    ```bash
    java NetworkManagementImpl
    
4. Run the client:
   
    ```bash
    java NetworkManagementClientGUI
    
5. Enter the IP address of the target device and click "Get Device Info" to retrieve information.

# Additional Notes
This project uses Java RMI (Remote Method Invocation) for communication between the server and client.
Ensure that the necessary libraries (Apache Commons Lang 3, oshi-core) are included in your classpath.


# Notes
Anda dapat menyesuaikan dan menambahkan informasi tambahan sesuai kebutuhan proyek Anda. Pastikan untuk menyertakan panduan instalasi, konfigurasi, dan penggunaan yang cukup jelas agar pengguna lain dapat dengan mudah memahami cara menggunakan proyek ini.




