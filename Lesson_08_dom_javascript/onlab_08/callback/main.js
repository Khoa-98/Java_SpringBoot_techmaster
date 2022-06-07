// 1 số cồng việc thực hiện
// đi làm: 4s
// nghỉ trưa : 2s
// đi nhậu : 3s

function doTask1(taskName, callback) {
  console.log("bat dau cong viec hang ngay");
  console.log(`thuc hien cong viec ${taskName}`);

  setTimeout(function () {
    console.log(`thuc hien xong coong viec ${taskName}}`);
    callback();
  }, 4000);
}

function doTask2(taskName, callback) {
  console.log(`thuc hien cong viec ${taskName}`);

  setTimeout(function () {
    console.log(`thuc hien xong coong viec ${taskName}}`);
    callback();
  }, 2000);
}

function doTask3(taskName, callback) {
  console.log(`thuc hien cong viec ${taskName}`);

  setTimeout(function () {
    console.log(`thuc hien xong coong viec ${taskName}}`);
    callback();
  }, 3000);
}

function finish() {
  console.log("ket thuc cong viec");
}

// thuc hien noi tiep: khi 3 cv thuc hien lien quan toi nhau
// doTask1("Đi làm", function () { 
//     doTask2("Nghi trua", function () { 
//         doTask3("di nhau", finish)
//     })
// });

//thuc hien song song: khi 3 function nay khonng lien quan gi toi nhau
doTask1("di lam", finish);
doTask2("nghi trua", finish);
doTask3("di nhau", finish);

// Gọi API lấy danh sach

// Promise : Lời hứa

// HỨA : Nếu có trên 30 triệu, sẽ mua iphone 13 pro max