const numbers = [23, 43, 77, 88, 65];
for (let i = 0; i < numbers.length; i++) {
    console.log(numbers[i]);
}
for (let num of numbers) {
    console.log(num);
}
let sum = 0;
// numbers.forEach(function (val, idx) { //값
//     console.log(val, idx);
//     sum += val;
// });

let fData = numbers.filter(function (val, idx) { // 조건 만족하는 변수만 배열로
    return val > 40;
    // if (idx < 3) {
    //     return val;
    // }
});
console.log(`fData: ${fData}`);

mData = fData.map(function (val, idx) { // 데이터 추출
    // if (val < 50) {
    //     sum += val;
    // }
    return val * 2;
});
console.log(`mData: ${mData}`);

mData.forEach(sumCallBack); // 데이터 연산

function sumCallBack(val, idx) {
    // if (val < 50) {
    //     sum += val;
    // }
    sum += val;
}
console.log(`합계 : ${sum}`);
console.log('--------------------');

numbers.filter(function (val, idx) {
    return val > 40;
}).map(function (val, idx) {
    return val * 2;
}).forEach(function (val, idx) {
    console.log(`val: ${val}, idx: ${idx}`);
});