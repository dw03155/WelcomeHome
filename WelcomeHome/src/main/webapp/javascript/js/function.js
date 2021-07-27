function cal() {
    let num1 = document.getElementById('num1');
    let num2 = document.getElementById('num2');
    console.log(Number(num1.value) + Number(num2.value));
    let result = document.getElementById('result');

    result.value = sum(Number(num1.value), Number(num2.value));
}

let a = 10;
console.log(a);

console.log('function 정의 전 : '+ sum(10,20)) // hoisting : javascript에서는 function 구문 먼저 정의(자동적으로)
// console.log('function 정의 전 : '+ fnc(10,20)) // 변수에 function 정의되었을 경우 hosting 불가

let fnc = function (a, b) {
    return a + b;
}
console.log(fnc);
console.log(fnc(10, 20));

function sum(num1, num2) {
    return num1 + num2;
}
let result = sum(10, 20);
console.log(result);
result = sum('Hello', "World");
console.log(result);