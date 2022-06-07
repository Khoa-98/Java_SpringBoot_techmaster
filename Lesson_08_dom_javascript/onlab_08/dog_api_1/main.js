const btn = document.querySelector(".btn");
const imageEl = document.querySelector(".image img");

// Lắng nghe sự kiện khi bấm vào nút "random"
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
