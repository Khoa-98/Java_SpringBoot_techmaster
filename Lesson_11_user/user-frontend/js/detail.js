// Root url
const URL_API = "http://localhost:8080/api/v1";

// Lây thông tin id trên url
let params = new URLSearchParams(window.location.search);
let id = params.get("id");
console.log(id);

let images = [];
let selectedFileId;
let user;
//truy cập
const addressEl = document.getElementById("address");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const passwordEl = document.getElementById("password");


const btnSave = document.getElementById("btn-save");

const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePassword = document.getElementById("btn-change-password");
const btnForgotPassword = document.getElementById("btn-forgot-password");

const myModal = new bootstrap.Modal(document.getElementById('modal-change-password'), {
    keyboard: false
});

const avatarEL = document.querySelector(".image-container")
const btnShowImagesElement = document.getElementById("btn-modal-image");
const btnUploadImageElement = document.getElementById("avatar");
const btnDeleteImageElement = document.getElementById("btn-delete-image")
const btnUpdateImageElement = document.getElementById("btn-chose-image");
const avatarPreviewElement = document.getElementById("avatar-preview");
const myModalImage = new bootstrap.Modal(document.getElementById('modal-image'), {
    keyboard: false
});


// Lấy thông tin user
const getUser = async (id) => {
    try {
       // B1: goi API
    let res = await axios.get(`${URL_API}/users/${id}`);
    user = res.data;
    renderUser();
  } catch (error) {
    console.log(error);
  }
};

// Hiển thị thông tin user
const renderUser = () => {
      // hiển thị len giao diện
  nameEl.value = user.name;
  emailEl.value = user.email;
  phoneEl.value = user.phone;
  addressEl.value = user.address;
  if (user.avatar == null || user.avatar == "") {
    avatarPreviewElement.src = "https://via.placeholder.com/200";
  } else {
    avatarPreviewElement.src = `${URL_API}/users/${id}/files/${user.avatar}`;
  }
};


// xử lý lấy danh sach ảnh 
const getListImages = async function () { 
 try {
        // B1: goi API
        let res = await axios.get("http://localhost:8080/api/v1/users/" + id + "/files") 
       
        // B2: hiển thị len giao diện
        images = res.data;
        renderImages(images);

    } catch (error) {
        console.log(error);
    }
}

btnShowImagesElement.addEventListener("click", getListImages())
// render anh 
const renderImages = arr => { 
    let html = "";
  for (let image of arr) {
    let fileId = getFileId(image);
    html += `
      <div class="image-item">
        <img
          src="http://localhost:8080/${image}"
          alt="ảnh"
          onclick=selectImage(${fileId})
          class="${
            fileId == selectedFileId ? "border border-primary shadow" : ""
          }"
        />
      </div>
    `;
  }
    avatarEL.innerHTML = html;
}

// Upload image
btnUploadImageElement.addEventListener("change", async () => {
  try {
    let formData = new FormData();
    formData.append("file", btnUploadImageElement.files[0]);
    // Upload lên server
    let res = await axios.post(`${URL_API}/users/${id}/upload-file`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    // Thêm vào mảng images
    images.unshift(res.data); // add item to front of array

    // Render lại
    renderImages(images);
  } catch (error) {
    console.log(error);
  }
});

// Reset selected image
btnShowImagesElement.addEventListener("click", () => {
  resetSelectImage();
  renderImages(images);
});

// Update avatar
btnUpdateImageElement.addEventListener("click", function () {
 updateAvatar(selectedFileId),
    myModalImage.hide();
 });

async function updateAvatar(fileId) {
  try {
    let res = await axios.put(`${URL_API}/users/${id}/update-avatar`, null, {
      params: {
        fileId,
      },
    });
    resetSelectImage();
    user = res.data;
    renderUser();
  } catch (error) {
    console.log(error);
  }
}

// Delete image
btnDeleteImageElement.addEventListener("click", async () => {
  // Xoá trên server
  try {
    await axios.delete(`${URL_API}/users/${id}/files/${selectedFileId}`);
  } catch (error) {
    console.log(error);
  }

  // Nếu ảnh bị xoá là avatar -> xoá avatar
  if (selectedFileId == user.avatar) {
    updateAvatar("");
  }

  // Xoá trong mảng images
  const index = images.indexOf(imageFromFileId(selectedFileId));
  if (index > -1) {
    images.splice(index, 1);
  }

  // Render lại
  resetSelectImage();
  renderImages(images);
});


// Xử lý cập nhật thông tin user
btnSave.addEventListener("click", async function () {
    try {
         // 2. Gọi API để cập nhật thông tin user
        
         let res = await axios.put(`http://localhost:8080/api/v1/users/${id}`, { 
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
        let res =  await axios.put(`http://localhost:8080/api/v1/users/${id}/update-password`, { 
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


// Xử lý event quên mật khẩu:
btnForgotPassword.addEventListener("click", async function() {
    try {
        // 1. Gọi API đến server
        let res = await axios.post(`http://localhost:8080/api/v1/users/${id}/forgot-password`)          
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
        html += `<option ">
        ${province.name}
        <option/>`
    });

    addressEl.innerHTML = html;
}
const getFileId = (image) => {
  let slashIndex = image.lastIndexOf("/");
  return image.substring(slashIndex + 1);
};

const imageFromFileId = (fileId) => {
  return `api/v1/users/${id}/files/${fileId}`;
};

const selectImage = (fileId) => {
  selectedFileId = fileId;
  renderImages(images);
  btnUpdateImageElement.disabled = false;
  btnDeleteImageElement.disabled = false;
};

const resetSelectImage = () => {
  selectedFileId = null;
  btnUpdateImageElement.disabled = true;
  btnDeleteImageElement.disabled = true;
};


const init = async () => {
    await getProvince();
    await getUser(id);
     await getListImages(id);
}

init();