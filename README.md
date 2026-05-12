# Test-Intern-Developer
### Công Nghệ Sử Dụng

🔙 Backend (BE)

• Ngôn ngữ: Java 17
• Framework: Spring Boot 3

• Kiến trúc:
  - Clean Architecture & DDD (Domain-Driven Design)
  - Chia tách rõ ràng các tầng Domain, Application, Infrastructure và Presentation giúp hệ thống dễ bảo trì, mở rộng và phát triển lâu dài.

• Cơ sở dữ liệu:

  - Spring Data JPA & MySQL dùng để quản lý thực thể và thao tác dữ liệu bền vững.

• Kiểm soát dữ liệu:

  - Jakarta Validation giúp đảm bảo tính toàn vẹn của dữ liệu đầu vào.

🎨 Frontend (FE)

• Thư viện UI: React JS

• Giao tiếp API: Axios hỗ trợ gửi request đến Backend.

• Giao diện:

  - Bootstrap 5 & Bootstrap Icons
  - Thiết kế Dashboard hiện đại, chuyên nghiệp và tương thích với nhiều thiết bị (Responsive).

### 🚀 Các Tính Năng Chính

📌 Quản lý Voucher

Hệ thống hỗ trợ đầy đủ các chức năng quản lý voucher:

 Thêm voucher mới

 Cập nhật voucher

 Xóa voucher

 Xem danh sách voucher

 Tìm kiếm voucher theo code

<img width="1917" height="742" alt="image" src="https://github.com/user-attachments/assets/71385d75-82fd-40ff-9556-39927dec20bd" />

👤 Quản lý User

Các chức năng quản lý người dùng:

Thêm user

Xem danh sách user

<img width="1917" height="743" alt="image" src="https://github.com/user-attachments/assets/9500c773-5af7-4aeb-b03f-2b4245984469" />

🎟️ Sử Dụng Voucher

Cho phép user sử dụng voucher trực tiếp trên hệ thống.

Khi voucher được sử dụng:

Quantity của voucher sẽ giảm đi 1

Lưu lịch sử sử dụng vào bảng voucher_usages

<img width="1918" height="821" alt="image" src="https://github.com/user-attachments/assets/eac2cf73-d977-40f8-b8d4-56c918454187" />

###  🏗️ Cấu Trúc Database

Hệ thống sử dụng 3 bảng chính với các ràng buộc dữ liệu chặt chẽ:

📄 Users
Lưu trữ thông tin khách hàng:

• Họ tên

• Email

• Số điện thoại

🎫 Vouchers
Quản lý thông tin mã giảm giá:

• Mã voucher

• Phần trăm giảm giá

• Số lượng

• Ngày hết hạn

📜 Voucher_Usages

• Lưu lịch sử khi khách hàng sử dụng voucher.

### 🚀 Hướng Dẫn Cài Đặt & Chạy Dự Án

1️⃣ Chuẩn Bị Backend (Spring Boot)

• Mở forder QL_voucher_be

⚙️ Cấu hình Database

• Mở MySQL thông qua XAMPP/WAMP

• Import database có tên test_1.sql

⚙️ Cấu hình kết nối

Mở file:
src/main/resources/application.properties

Cập nhật:

spring.datasource.username=

spring.datasource.password=

theo cấu hình MySQL trên máy của bạn.

▶️ Chạy dự án Backend

• Mở project bằng IntelliJ IDEA hoặc Eclipse

• Chạy file:
TemplateArchitectureApplication.java

Backend sẽ chạy tại:
http://localhost:8080

2️⃣ Chuẩn Bị Frontend (React)

• Mở forder QL_voucher_fe

📦 Cài đặt thư viện

Mở terminal tại thư mục frontend và chạy:
npm install

Cài đặt thêm thư viện:
npm install lucide-react

▶️ Chạy ứng dụng Frontend

Chạy lệnh:
npm run dev

Truy cập giao diện tại:
http://localhost:3000/vouchers
