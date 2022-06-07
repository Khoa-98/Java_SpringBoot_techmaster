const text = document.getElementById("text");
console.log(text);
const btn1 = document.getElementById("btn-1");
console.log(btn1);
const btn2 = document.getElementById("btn-2");

btn1.addEventListener("click", function () {
  //B1: random quote
  let quotes = ["quote 1", "quote 2", "quote 3", "quote 4"];

  let randomIndex = Math.floor(Math.random() * quotes.length);
  let randomQuote = quotes[randomIndex];

  // B2: chen lai noi dung cho the p
  text.innerText = randomQuote;
});

btn2.addEventListener("click", function () {
  //B1: Random color
  let hexColor = randomHexColor();

  //B2: thay doi mau cua the p
  text.style.color = "hexColor";
});

function randomHexColor() {
  // Tao mang bao gom 16 ki tu 0 -> 9, a -> f
  let kitu = [
    "0",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "a",
    "b",
    "c",
    "d",
    "f",
  ];

  //Su dung vong lap 6 lan , moi vong lap random ra 1 ki tu (cong chuoi)
  for (let i = 0; i < 6; i++) {
    let hexIndex = Math.floor(Math.random() * kitu.length);
    let randomHex = kitu[hexIndex];
    randomHex = randomHex + randomHex;
    console.log(randomHex);
  }
  // tra ve ma HEX + dau # ở đầu
  return randomHex;
}
