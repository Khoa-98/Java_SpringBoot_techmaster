let money = 40;

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

// async function buy({

// })

const buy = async () => {
  try {
    let res = await buyIphone();
    console.log(res);

    let res1 = await buyAirpod();
    console.log(res1);
  } catch (error) {
    console.log(error);
  }
  return "ve nha";
};
// console.log(buy());

buy()
  .then((res) => console.log(res))
  .catch((error) => console.log(error));
