# DESCRIPTION FOR PROJECT

## **Câu 3**

http://localhost:8080/bmi tính chỉ số BMI theo 2 tham số: weight, height người dùng gửi lên bằng phương thức POST, trả về chỉ số BMI index dạng số.

```java
@PostMapping("/bmi")
    @ResponseBody
    public double caculatorbmi(@RequestParam double w, @RequestParam double h) {
        return (h/(w*w));
    }
```

**Kết quả**

![image](https://user-images.githubusercontent.com/95128548/168418866-fab5c163-c6ea-4503-a3cc-d6cdbd567b9e.png)

## **Câu 1**

http://localhost:8080/random trả về một chuỗi random string gồm 8 ký tự A-Z, a-z, 0-9

```JAVA
  @GetMapping("/random")
    public String getRandomStr() {
        String s = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 8; i++) {
            Random rd = new Random();
            int number = rd.nextInt(s.length());
            sb.append(s.charAt(number));
        }
        return sb.toString();
    }
```

**Kết quả**
![image](https://user-images.githubusercontent.com/95128548/168419253-8171a898-11d5-41c5-8cbf-9d3d816cd2b9.png)

## **Câu 2**

http://localhost:8080/quote trả về ngẫu nhiên một trong những câu tục ngữ sau đây:
Kiến tha lâu đầy tổ
Có công mài sắt, có ngày nên kim
Không thầy đố mày làm nên
Học thầy không tày học bạn

```java
@GetMapping("/quote")
    public String getRandomQuote() {
        List<String> list = new ArrayList<>() ;
        list.add("Kiến tha lâu đầy tổ");
        list.add("Có công mài sắt, có ngày nên kim");
        list.add("Không thầy đố mày làm nên");
        list.add("Học thầy không tày học bạn");
        Random rd = new Random();
        int index = rd.nextInt(4);
        return list.get(index);
    }
```

**Kết quả**
![image](https://user-images.githubusercontent.com/95128548/168419786-b6f31bed-672a-45c2-9c4a-fcb4337fe3df.png)
![image](https://user-images.githubusercontent.com/95128548/168419866-b0e10785-e29f-408a-bbf3-e6cca64e55d8.png)
![image](https://user-images.githubusercontent.com/95128548/168419916-e2f20d9d-bc38-4ea1-af4a-962e01a4143f.png)
![image](https://user-images.githubusercontent.com/95128548/168419943-b0157cc0-7074-4bfd-92df-55e9cfff7448.png)

## **Câu 4**

http://localhost:8080/student có 2 phương thức: GET, POST

- GET: trả về danh sách tất cả các student hiện có List<Student>
- POST: tạo mới một student thêm vào danh sách List<Student>

```java

```
