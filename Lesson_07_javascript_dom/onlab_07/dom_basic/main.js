const heading = document.getElementById("heading");
console.log(heading);

const para2 = document.querySelector(".para");
console.log(para2);

// const para3 = document.querySelector(".para:nth-child(4)"); // (".para .para")
// console.log(para3);

const para3 = para2.nextElementSibling;
console.log(para3);

const ul1 = document.querySelector(".box ul");
console.log(ul1);

const ul2 = document.querySelector("body > ul");
console.log(ul2);

const paras = document.querySelectorAll("p");
console.log(paras);

//sử dung for qua Nodelist
// for (let i = 0; i < paras.length; i++) {
//   paras[i].style.color = "blue";
//   paras[i].style.backgroundColor = "black";
// }

Array.from(paras).map((e) => {
  e.style.color = "red";
  e.style.backgroundColor = "black";
});

// Lấy ra nội dung của phần tử
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML); // tra về thẻ và nôi dung của thẻ
console.log(ul1.innerText); // tra về những gì chúng ta thấy trên trình duyệt và giữ đúng định dạng
console.log(ul1.textContent); // dung khi muốn lấy ra cả những thẻ ẩn

heading.innerHTML = "xin chào";
heading.innerHTML = "<span>Xin chào các bạn </span>";
heading.innerText = "<span>Xin chào các bạn </span>";

//Tao ra phần tử DOM
const newPara = document.createElement("p");
newPara.innerText = "New Para";

console.log(newPara);

// chen vao vi tri dau của thẻ cha
// document.body.prepend(newPara);

//chen vao vi tri cuoi cua thẻ cha
// document.body.appendChild(newPara);

// document.body.insertBefore(newPara, para2);

const box = document.querySelector(".box");
// document.body.insertBefore(newPara, box);

// para3.insertAdjacentElement("afterend", newPara);

// box.insertAdjacentElement("beforebegin", newPara);
box.insertAdjacentHTML("beforebegin", "<p>the para moi</p>");

// xoa phan tu
// document.body.removeChild(heading); // truy cap truc tiep = ptu cha
heading.parentElement.removeChild(heading);

//Thay the
// vidu: thay th the para2 bang the a, link den trang google.com

const link = document.createElement("a");
link.innerText = "link toi trang google.com";
link.href = "https://google.com";

document.body.replaceChild(link, para2);

// sao chep the
const boxCopy1 = box.cloneNode(true);
const boxCopy2 = box.cloneNode(false);

console.log(boxCopy1);
console.log(boxCopy2);

//class list
console.log(box.classList);

//them class
box.classList.add("text-big", "text-bold");
box.classList.remove("text-big");

console.log(box.classList.contains("box"));
console.log(box.classList.contains("text-big"));

// setInterval : lap di lap lai
setInterval(function () {
  box.classList.toggle("text-big");
}, 1500);
