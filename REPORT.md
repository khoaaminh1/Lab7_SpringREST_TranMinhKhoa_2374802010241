# Báo cáo Lab 7: Xây dựng RESTful API với Spring Boot

## Thông tin của bạn

- **Họ và tên:** Trần Quang Vinh
- **MSSV:** 2374802010564

## Link GitHub Repository

- **Link:** https://github.com/quangvinh3020/Lab7_AdvJava

## Tổng quan

Bài lab này tập trung vào việc xây dựng một ứng dụng RESTful API hoàn chỉnh bằng cách sử dụng Spring Boot. Mục tiêu là tạo ra một dịch vụ web cho phép thực hiện đầy đủ các thao tác CRUD (Create, Read, Update, Delete) trên một đối tượng dữ liệu (trong trường hợp này là `Book`).

Qua bài lab, các kiến thức và kỹ thuật sau đã được áp dụng:
- **Spring Boot:** Sử dụng để khởi tạo và cấu hình nhanh chóng một ứng dụng web.
- **Spring Web (MVC):** Xây dựng các endpoint RESTful với `@RestController` và các annotation như `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`.
- **Spring Data JPA:** Đơn giản hóa việc tương tác với cơ sở dữ liệu bằng cách sử dụng `JpaRepository`.
- **Hibernate:** Framework ORM (Object-Relational Mapping) được Spring Data JPA sử dụng ngầm.
- **H2 Database:** Sử dụng cơ sở dữ liệu trong bộ nhớ (in-memory) để dễ dàng phát triển và kiểm thử mà không cần cài đặt.
- **Kiến trúc 3 lớp:** Phân tách rõ ràng trách nhiệm giữa Controller (lớp giao tiếp), Service (lớp xử lý logic nghiệp vụ), và Repository (lớp truy cập dữ liệu).

## Các bước thực hiện

Dự án được cấu trúc theo kiến trúc 3 lớp để đảm bảo tính module hóa và dễ bảo trì.

1.  **Entity (`Book.java`):**
    -   Đây là một POJO (Plain Old Java Object) đại diện cho bảng `Book` trong cơ sở dữ liệu.
    -   `@Entity`: Đánh dấu lớp này là một thực thể JPA.
    -   `@Id`: Xác định khóa chính của bảng.
    -   `@GeneratedValue`: Cấu hình cách khóa chính được tự động tạo ra.

2.  **Repository (`BookRepository.java`):**
    -   Là một interface kế thừa từ `JpaRepository<Book, Long>`.
    -   `@Repository`: Đánh dấu đây là một Spring Bean chịu trách nhiệm truy cập dữ liệu.
    -   Spring Data JPA tự động cung cấp các phương thức CRUD cơ bản (như `findAll()`, `findById()`, `save()`, `deleteById()`) mà không cần viết mã SQL.

3.  **Service (`BookService.java`):**
    -   Chứa logic nghiệp vụ của ứng dụng.
    -   `@Service`: Đánh dấu đây là một Spring Bean chứa logic xử lý.
    -   `@Autowired`: Tự động tiêm `BookRepository` vào để sử dụng.
    -   Lớp này đóng vai trò trung gian giữa Controller và Repository.

4.  **Controller (`BookController.java`):**
    -   Xử lý các yêu cầu HTTP từ client.
    -   `@RestController`: Kết hợp giữa `@Controller` và `@ResponseBody`, cho biết các phương thức trong lớp này sẽ trả về dữ liệu (JSON) thay vì view.
    -   `@RequestMapping("/api/books")`: Định nghĩa tiền tố URL cho tất cả các endpoint trong controller này.
    -   Các annotation `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` được sử dụng để ánh xạ các yêu cầu HTTP tới các phương thức xử lý tương ứng.

## Danh sách Endpoints (curl)

Dưới đây là danh sách các lệnh `curl` để kiểm thử các endpoint của API. (Giả sử ứng dụng đang chạy trên `localhost:8080`).

### 1. CREATE - Thêm một cuốn sách mới (POST)

**Lệnh:**
```bash
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d '{"title": "Lão Hạc", "author": "Nam Cao"}'
```

**Response mong đợi (Ví dụ):**
```json
{"id":1,"title":"Lão Hạc","author":"Nam Cao"}
```

### 2. READ - Lấy tất cả sách (GET)

**Lệnh:**
```bash
curl http://localhost:8080/api/books
```

**Response mong đợi (Ví dụ):**
```json
[{"id":1,"title":"Lão Hạc","author":"Nam Cao"}]
```

### 3. READ - Lấy sách theo ID (GET)

**Lệnh (lấy sách có ID là 1):**
```bash
curl http://localhost:8080/api/books/1
```

**Response mong đợi (Ví dụ):**
```json
{"id":1,"title":"Lão Hạc","author":"Nam Cao"}
```

### 4. UPDATE - Cập nhật thông tin sách (PUT)

**Lệnh (cập nhật sách có ID là 1):**
```bash
curl -X PUT http://localhost:8080/api/books/1 -H "Content-Type: application/json" -d '{"title": "Chí Phèo", "author": "Nam Cao"}'
```

**Response mong đợi (Ví dụ):**
```json
{"id":1,"title":"Chí Phèo","author":"Nam Cao"}
```

### 5. DELETE - Xóa một cuốn sách (DELETE)

**Lệnh (xóa sách có ID là 1):**
```bash
curl -X DELETE http://localhost:8080/api/books/1
```

**Response mong đợi:**
- HTTP Status `204 No Content` và không có nội dung trả về.

