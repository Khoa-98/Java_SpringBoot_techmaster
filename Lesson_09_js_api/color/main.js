const btnRansomColorName = document.querySelector(".btn_name_color");
const backgroundColor = document.querySelector("body");

btnRansomColorName.addEventListener("click", async function () {
  try {
    let res = await axios.get("http://localhost:8080/random-color?type=1");
    console.log(res);

    backgroundColor.style.backgroundColor = res.data;
  } catch (error) {
    console.log(error.response);
  }
});
