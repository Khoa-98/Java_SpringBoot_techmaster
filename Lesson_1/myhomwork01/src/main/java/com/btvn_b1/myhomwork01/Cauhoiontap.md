# **Câu hỏi ôn tập**

## **_Câu 1:_**

```java
Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì?
```

### _Lời giải:_

- groupID: thường đặt theo tên của tổ chức/công ty/nhóm tạo ra dự án
- artifactID: Tên của Package dự án, thường lấy theo tên viết tắt của dự án.

## **_Câu 2:_**

```java
Tại sao phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId>?
```

### _Lời giải:_

- Vì groupId phải tuân theo các quy tắc đặt tên gói của java (https://docs.oracle.com/javase/specs/jls/se6/html/packages.html#7.7 ).
  Maven không bắt buộc quy định này.1 số dự án không tuân theo cách này mà mà sử dụng các groupId là từ đơn. tuy nhiên sẽ rất khó để có được 1 groupId từ đơn mới được chấp thuận để đưa vào kho lưu trữ trung tâm của Maven.

## **_Câu 3:_**

```java
SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?
```

### _Lời giải:_

- Maven và Gradle

## **_Câu 4:_**

```java
File pom.xml có tác dụng gì?
```

### _Lời giải:_

- POM là từ viết tắt của Project Object Model. Tệp pom.xml chứa thông tin về dự án và thông tin cấu hình để maven xây dựng dự án, chẳng hạn như: dependencies, build directory, source directory, test source directory, plugin, goals etc  
  Maven đọc tệp pom.xml, sau đó thực hiện mục tiêu.

## **_Câu 5:_**

Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì?

### _Lời giải:_

- Trong file pom.xml chúng ta sẽ khai báo các thư viện con bên trong cặp thẻ dependency với các thông tin bao gồm tên thư viện và version của thư viện.

## **_Câu 6:_**

```java
Ý nghĩa của @Controller là gì?
```

### _Lời giải:_

- @Controller là một annotation ở class
- @Controller thường hay được sử dụng cho Spring Controller truyền thống hay được sử dụng trong các phiên bản Spring từ 4.0 trở xuống.
- @Controller sẽ được sử dụng với annotation @RequestMapping trên các phương thức để xử lý các request

```java
@Controller
@RequestMapping("books")
public class SimpleBookController {

    @GetMapping("/{id}", produces = "application/json")
    public @ResponseBody Book getBook(@PathVariable int id) {
        return findBookById(id);
    }

    private Book findBookById(int id) {
        // ...
    }
}
```

- Như các bạn thấy ở trên để xây dựng được RESTful API chúng ta cân phải sử dụng thêm annotation @ResponseBody để đánh dấu Phương thức xử lý các request. Annotation này sẽ cho phép tự động trả về đối tượng vào HttpResponse.

## **_Câu 7:_**

```java
Ý nghĩa của @RequestMapping là gì? Nó có những tham số gì ngoài value?
```

### _Lời giải:_

- @RequestMapping: nó được sử dụng để map (ánh xạ) các request.
- Nó có nhiều phần tử tùy chọn như consumes, header, method, name, params, path, produces, và value.  
  Chúng ta sử dụng nó với lớp cũng như phương thức.

## **_Câu 8:_**

```java
Ý nghĩa của @RequestBody khi đặt trong hàm hứng request để làm gì?

```

### _Lời giải:_

- @RequestBody: Nó được sử dụng để liên kết yêu cầu HTTP với một đối tượng trong một tham số của phương thức. Bên trong nó sử dụng HTTP MessageConverters để chuyển đổi phần thân của yêu cầu. Khi chú thích một tham số của phương thức với @RequestBody, Spring sẽ liên kết phần body yêu cầu HTTP đến với tham số đó.

## **_Câu 9:_**

```java
Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam
```

### _Lời giải:_

- @RequestParam được dùng khi bạn muốn trích xuất dữ liệu từ request query
- @PathVariable thì được dùng khi bạn muốn trích xuất dữ liệu từ URL path.

Để phân biệt giữa request query và url path thì cùng tìm hiểu qua ví dụ sau đây:

```java
@RestController
public class UserController {

    @Autowire
    private UserRepository. userRepo;

    @PostMapping("/user/{id}")
    public User getUserById (@PathVariable String id)) {
        return userRepo.findById(id);
    }
}
```

Với Endpoint trên, chúng ta đã đặt nó theo mẫu của URL path, chuỗi {id} chỉ ra rằng giá trị đằng sau “/user” là mã định danh ID của user. Vì vậy chúng ta hoàn toàn có thể sử dụng @PathVariable để trích xuất ID từ URL path.

Chúng ta có thể sử dụng mẫu HTTP request dưới đây để kiểm tra.

```java
http://localhost:8080/user/1
```

Chúng ta sẽ làm một ví dụ tương tự như tính năng ở trên nhưng dùng @RequestParam.

```java
@RestController
public class UserController {

    @Autowire
    private UserRepository. userRepo;

    @PostMapping("/user")
    public User getUserById (@RequestParam String id)) {
        return userRepo.findById(id);
    }
}
```

Trong đó id là một thuộc tính trong request query với đầu vào là 1. Chúng ta có thể API trên bằng HTTP request sau:

```java
http://localhost:8080/user?id=1
```

## **_Câu 10:_**

```java
Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không?
```

### _Lời giải:_

- không thể

## **_Câu 11:_**

```java
@GetMapping khác gì so với @PostMapping?
```

### _Lời giải:_

- @GetMapping: ó nhiệm vụ đánh dấu hàm xử lý Get request trong Controller, mục đích là để lấy thông tin dữ liệu từ server bởi 1 URI đã cung cấp. Các yêu cầu sử dụng GET chỉ nhận dữ liệu và không có ảnh hưởng gì tới dữ liệu.
- @PostMapping: có nhiệm vụ đánh dấu hàm xử lý POST request trong Controller.Post mục đích là để gửi dữ liệu tới server giúp bạn thêm mới dữ liệu vào database ví dụ, thông tin khách hàng, file tải lên,...

## **_Câu 12:_**

```java
Trong các annotation @RequestMapping, @GetMapping, @PostMapping… có tham số produces = MediaType.XXXX ý nghĩa tham số này là gì?
```

### _Lời giải:_

- Tham số này có định dang kiểu dữ liệu trả về (content-type)
- vd: bạn muốn dữ liệu trả về ở định dạng JSON thì các bạn có thể thêm thuộc tính produces trong annotation @RequestMapping với giá trị là “application/json” như sau:

```java
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello() {
		return "Hello World!";
	}
}
```

Khi đó, chúng ta sẽ nhận kết quả với header như sau:  
![image](https://user-images.githubusercontent.com/95128548/168408397-3ee03138-ab79-46c6-98a3-39967e999528.png)  
Như các bạn thấy, content-type trả về lúc này là application/json

## **_Câu 13:_**

Giải thích ý nghĩa của @RequestBody trong đoạn code dưới đây:

```java
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
    return message;
}
```

### _Lời giải:_

- @RequestBody sẽ yc Spring convert json data thành đối tượng message

## **_Câu 14:_**

```java
Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định

```

### _Lời giải:_

- Mặc định, server sẽ khởi chạy trên port 8080, chúng ta có thể thay đổi điều này bắt cách điều chỉnh giá trị server.port trong application.properties

```java
server.port=8081
```
