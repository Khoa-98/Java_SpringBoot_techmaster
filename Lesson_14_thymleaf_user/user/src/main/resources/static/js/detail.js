
//truy cập
const addressEl = document.getElementById("address");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const passwordEl = document.getElementById("password");
const avatarEL = document.querySelector(".image-container")


// luu lai id cua user
let id = user.id;

// truy cap và xử lý lấy danh sach ảnh 
const btnSelectFile = document.getElementById("btn-modal-image");
btnSelectFile.addEventListener("click", async function getListFiles() {
    try {
        // B1: goi API
        let res = await axios.get("/api/v1/users/" + id + "/files") 
       
        // B2: hiển thị len giao diện
        
        renderFile(res.data);

    } catch (error) {
        console.log(error);
    }
 })
// render anh 
const renderFile = arr => { 
    let html = "";
    arr.forEach(file => {
        html += ` <div class="image-item">
                                <img src="http://localhost:8080/${file}" alt="ảnh">
                    </div>`
    });

    avatarEL.innerHTML = html;
}
// Xử lý chọn ảnh:
// truy cập:


// Xử lý cập nhật thông tin user
const btnSave = document.getElementById("btn-save");
btnSave.addEventListener("click", async function () {
    try {
         // 2. Gọi API để cập nhật thông tin user
        
         let res = await axios.put(`/api/v1/users/${id}`, { 
            // 1. Lấy dữ liệu name, phone, address trong ô input
            name: nameEl.value,
            phone: phoneEl.value,
            address: addressEl.value
         })
         // 3. Thông báo nếu cập nhật thông tin thành công
        if (res.data) { 
            alert("Cập nhật thành công");
            window.location.href = "/";
        }   
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        console.log(error);
    }
});

// Đổi mật khẩu
const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePassword = document.getElementById("btn-change-password");

const myModal = new bootstrap.Modal(document.getElementById('modal-change-password'), {
            keyboard: false
            })
// Thay đổi mật khẩu
btnChangePassword.addEventListener("click", async function () {
    try {
        // 1. lay gia tri trong cac o input
        let valueOld = oldPasswordEl.value;
        let valueNew = newPasswordEl.value
         // 2. Kiểm tra giá trị có rỗng hay không
        if (valueOld == "" || valueNew == ""){ 
            alert("Mật khẩu không được để trống");
        return;
        }
          // 3. Gửi API để cập nhật lại password
        let res =  await axios.put(`/api/v1/users/${id}/update-password`, { 
            // Lấy 2 giá trị oldPassword, newPassword
           oldPassword : valueOld,
            newPassword : valueNew
            
        })

         // 4. Đóng modal và clear lại giá trị trong các ô input
         
        if (res) {
             myModal.hide();  
            alert("Đổi mật khẩu thành công");
        }
        // reset lai ô input
        oldPasswordEl.value = "";
       newPasswordEl.value = "";
        
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        console.log(error);
    }
});
// truy cập vào quên mật khẩu
const btnForgotPassword = document.getElementById("btn-forgot-password");
// Xử lý event quên mật khẩu:
btnForgotPassword.addEventListener("click", async function() {
    try {
        // 1. Gọi API đến server
        let res = await axios.post(`/api/v1/users/${id}/forgot-password`)          
        // 2. Thông báo password mới (sử dụng alert)
        alert("Mật khẩu mới là: " + res.data);
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        console.log(error);
    }
})

// API: https://provinces.open-api.vn/api/p/
const getProvince = async () => { 
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(res)

        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}
const renderProvince = arr => { 
    let html = "";
    arr.forEach(province => {
        html += `<option">
        ${province.name}
        <option/>`
    });

    addressEl.innerHTML = html;
}

// chạy lấy tỉnh thành phố trước sau đó mới lấy thông tin từ user
const init = async () => {
    await getProvince();
    addressEl.value = user.address;
}

init();