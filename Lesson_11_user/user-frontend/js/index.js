const API_URL = "http://localhost:8080/api/v1"

// Khai báo mảng user ban đầu để hứng dữ liệu từ phía backend
let users = [];

const userListEl = document.querySelector("tbody")

// Định nghĩa API lấy danh sách user (bao gồm cả tìm kiếm user)
// Nếu term != "" --> tìm kiếm user
// Ngược lại --> lấy danh sách user
function getUsersAPI(term = "") {
    let url = `${API_URL}/users`;
    if (term) {
        url = `${API_URL}/users/search?name=${term}`;
    }

    return axios.get(url);
}

// Gọi API và hiển thị ra dữ liệu
async function getUsers(term = "") {
    try {
        // 1. Gọi API lấy danh sách users
        let res = await getUsersAPI(term);
        // 2. Lưu danh sách users từ API trả về vào biến users (để phục vụ chức năng thêm, xóa, hiển thị danh sách user)
        users = res.data;
       // 3. Render dữ liệu ra ngoài giao diện
        renderUser(users);
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        console.log(error);
    }
}

// Function dùng để render dữ liệu bảng
function renderUser(arr) {
    userListEl.innerText = "";

     if (arr.length == 0) {
        userListEl.innerHTML = "Không có user nào trong danh sách";
        return;
    }

     // Tạo chuỗi html
    let html = "";
    for (let i = 0; i < arr.length; i++) {
        const t = arr[i];
        html += `
         <tr>
            <td>${t.id}</td>
            <td>${t.name}</td>
            <td>${t.email}</td>
            <td>${t.phone}</td>
            <td>${t.address}</td>
            
            <td>
                <a href="./detail.html?id=${t.id}" class="btn btn-success">Xem chi tiết</a>
                <button class="btn btn-danger" onclick="deleteUser(${t.id})">Xóa</button>
            </td>
        </tr>
        `;
    }
        userListEl.innerHTML = html;
}

// xoa user
const deleteUser = async (id) => {
    try {
        // Xác nhận xem user có đồng ý xóa hay không (sử dụng confirm)
         let isConfirm = confirm("Bạn có muốn xóa không?");


        // Nếu người dùng đồng ý xóa -> Gọi API xóa
         if (isConfirm) {
            // Xóa trên server
            await axios.delete(`${API_URL}/users/${id}`);

            // Xóa trong mảng todos
            users.forEach((user, index) => {
                if (user.id == id) {
                    users.splice(index, 1);
                }
            })

            // Render lại giao diện
            renderUser(users);
        }
       
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        console.log(error);
        
    }
};

// Truy cập vào nút search
const searchEl = document.getElementById("search");

// Lắng nghe sự kiện trong ô input
searchEl.addEventListener("keydown", function (e) {
    // B1 : Kiểm tra nếu người dùng bấm vào phím Enter
    if (e.keyCode == 13) { 
         // B2 : Lấy dữ liệu trong ô input
        let value = searchEl.value;
        // B3 : Gọi API tương ứng ,Hiển thị kết quả tương ứng bằng cách gọi renderUser() và truyền kết quả từ API vào để hiển thị
        getUsers(value);
    }
});
getUsers();
