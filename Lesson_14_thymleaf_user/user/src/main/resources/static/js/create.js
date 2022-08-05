

//truy cập
const addressEl = document.getElementById("address");
const btnSave = document.getElementById("btn-save");
const fullnameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const passwordEl = document.getElementById("password");

// Tao user
btnSave.addEventListener("click",async function () { 
    try {
        // 3. Gọi API với các thông tin user có được ở trên để tạo user
        let res = await axios.post("http://localhost:8080/api/v1/users", { 
             // 1. Lấy thông tin user cần tạo từ các ô input
            name: fullnameEl.value,
            email: emailEl.value,
            phone: phoneEl.value,
            password: passwordEl.value,
            address: addressEl.value


        })
        // 4. Nếu tạo thành công thì quay lại trang danh sách
        if (res.data) { 
            
            window.location.href = "/";
        }
    } catch (error) {
        console.log(error)
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
        html += `<option value=" ${province.name}">
        ${province.name}
        <option/>`
    });

    addressEl.innerHTML = html;
}
getProvince();