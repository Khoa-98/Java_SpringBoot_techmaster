const heading = document.getElementById("heading");
// heading.innerHTML = " <b>Bai tap thuc hanh DOM</b>";
heading.style.color = "red";
heading.style.fontWeight = "bolder";

const para = document.querySelectorAll(".para");
Array.from(para).map((e) => {
  e.style.color = "blue";
  e.style.fontSize = "20px";
});

const para3 = document.querySelector(".para-3");
const link = document.createElement("a");
link.innerText = "Link Facebook";
link.href = "https://www.facebook.com/";

para3.insertAdjacentElement("afterend", link);

const title = document.getElementById("title");
title.innerText = "đây là thẻ tiêu đề";

const description = document.querySelector(".description");
description.classList.add("text-bold");

//6.Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const button = document.createElement("button");
button.innerText = "Click me";

para3.parentNode.replaceChild(button, para3);
