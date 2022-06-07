// Promise : Lời hứa

// HỨA : Nếu có trên 30 triệu, sẽ mua iphone 13 pro max

// let promise = new Promise(function (resolve, reject) {
//   //   resolve("thuc hien thanh cong ");
//   reject("thuc hien that bai");
// });

// console.log(promise);

let money = 37;

const buyIphone = () => {
  return new Promise(function (resolve, reject) {
    if (money > 33) {
      resolve("Mua Iphone thanh cong");
    } else {
      reject("khong du tien mua iphone, cay tiep di");
    }
  });
};

const buyAirpod = () => {
  return new Promise(function (resolve, reject) {
    if (money > 33 + 5) {
      resolve("Mua them airpod");
    } else {
      reject("khong du tien mua airpod");
    }
  });
};
// console.log(buyIphone());

// thuc hien loi hua
buyIphone()
  .then((res) => {
    // res là nội dung trong resolve
    console.log(res);
    return buyAirpod();
  })
  .then((res) => {
    console.log(res);
  })
  .catch((error) => {
    // error là nội dung trong reject
    console.log(error);
  })
  .finally(() => {
    // Luôn được thực hiện kể cả thành công hay thất bại
    console.log("ve nha");
  });


