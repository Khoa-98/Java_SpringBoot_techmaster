const btnBmiGet = document.querySelector(".btn_bmi_get");
const inputHeight = document.querySelector(".inputHeight");
const inputWeight = document.querySelector(".inputWeight");

const btnBmiPost = document.querySelector(".btn_bmi_post");
const h1 = document.querySelector(".text");

btnBmiGet.addEventListener("click", async function () {
  try {
    let res = await axios.get(
      `http://localhost:8080/bmi-get?height=${inputHeight.value}&weight=${inputWeight.value}`
    );
    console.log(res);
    h1.textContent = res.data;
  } catch (error) {
    console.log(error.response);
  }
});

btnBmiPost.addEventListener("click", async function () {
  try {
    let res = await axios.post(`http://localhost:8080/bmi-post`, {
      height: inputHeight.value,
      weight: inputWeight.value,
    });
    console.log(res);
    h1.textContent = res.data;
  } catch (error) {
    console.log(error.response);
  }
});
