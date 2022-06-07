const btn = document.querySelector("#btn");
const imageEl = document.querySelector(".result img");
const selectEl = document.querySelector("#breed-list");

// API lấy giống loài chính
async function getBreedList() {
  try {
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");
    console.log(res);

    // hien thi danh sach giống loài chính
    displayBreedList(res.data.message);
  } catch (error) {
    console.log(error);
  }
}
// hien thi danh sach giống loài chính
function displayBreedList(obj) {
  // lấy ra mảng key của đối tượng
  let keys = Object.keys(obj);

  let html = "";
  for (let i = 0; i < keys.length; i++) {
    html += `<option value ="${keys[i]}">${keys[i]}</option>`;
  }
  // chèn lại nội dung cho phần tử
  selectEl.innerHTML = html;
}

window.onload = getBreedList;

btn.addEventListener("click", async function () {
  try {
    // b1: Goi API
    let res = await axios.get("https://dog.ceo/api/breeds/image/random");
    console.log(res);
    // B2: lay ket qua tra ve tu API -> hien thi
    imageEl.src = res.data.message;
  } catch (error) {
    console.log(error);
  }
});
