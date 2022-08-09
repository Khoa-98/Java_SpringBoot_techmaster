
**_Chức Năng_**

1.Admin

Xem danh sách khóa học

Tạo khóa học

Cập nhật thông tin khóa học (bao gồm cả upload file)

Xóa khóa học

2.User

Xem danh sách tất cả khóa học

Xem danh sách khóa học online

Xem danh sách khóa học onlab

Lọc khóa học (tiêu đề, chủ đề)

Xem thông tin của 1 khóa học cụ thể.  


**Website**    
-> AWS :  http://34.219.73.111:8080/  
admin: http://34.219.73.111:8080/admin
web: http://34.219.73.111:8080/api/v1/course/list

1.Danh sách tất cả các khoa hoc:  
http://localhost:8080/api/v1/course/list 

2.Danh sách các khoá học online
http://localhost:8080/api/v1/course/online_list

3. Danh sach các khoá học onlab
   http://localhost:8080/api/v1/course/onlab_list  
   
4.Lọc theo chủ đề
http://localhost:8080/api/v1/course/list/topic/{topicId}

5. Trang chi tiết:  
   http://localhost:8080/api/v1/course/detail/{id}/{slug}
   
6. Loc theo ten khoa học  
   http://localhost:8080/api/v1/course/list?keyword=
   
**Admin course**  
1.trang danh sach tat ca các khoá học  
http://localhost:8080/admin  

2.trang chi tiet khoá học  
http://localhost:8080/admin/edit/{id}/{slug}  

3.trang tao khoa hoc moi  
http://localhost:8080/admin/create  

4.cap nhat khoa hoc  
http://localhost:8080/admin/api/edit/{id}  

5.xoa khoa hoc  
http://localhost:8080/admin/delete/{id}  

6.upload file image khoa hoc
http://localhost:8080/admin/api/courses/{id}/upload-file  

7. read file  
   http://localhost:8080/api/files/{fileId}
   




