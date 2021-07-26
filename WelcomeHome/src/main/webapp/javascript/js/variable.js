let var1 = 'Hello'; //변수 선언
console.log(typeof var1); //값의 타입 출력
var1 = 'World'; //string 타입
console.log(typeof var1);
var1 = 100; //int 타입
console.log(typeof var1);
var1 = true; //boolean 타입
console.log(typeof var1);
console.log(var1); //출력

const con1 = 'Good'; //상수 선언(값이 변하지 않는 수)
// con1='Moning'; //변수와 다르게 값을 바꿀 수 없음.
console.log(con1);

console.log(1 + 1);

let num1 = 10;
let num2 = 10;
console.log(num1 * num2);

document.write('<h1>Hello</h1>');
document.write('<ul>');
document.write(' <li>Apple</li>');
document.write(' <li>Banana</li>');
document.write(' <li>Carrot</li>');
document.write('</ul>');

let str = '<ul>';
str += '      <li>Orange</li>';
str += '      <li>Mango</li>';
str += '  <ul>';
document.write(str);

let fruits = ['Watermelon', 'Strawberry', 'Peach']; // Object(객체 : 배열)
// fruits = new Array(); // 배열 이렇게 쓰지 않기 // Object(객체 : 배열)
fruits.push('Watermelon'); // 마지막 입력 1)
// fruits.push('Strawberry');
// fruits[2] = 'Peach';
fruits[fruits.length] = 'Corn'; // 마지막 입력 2)
fruits.pop(); // 마지막 삭제
fruits.unshift('Corn'); // 첫번째 추가
fruits.shift(); // 첫번째 삭제

console.log(typeof fruits);
document.write('<ul>')
for (let i = 0; i < fruits.length; i++) {
    document.write('<li>' + fruits[i] + '</li>');
}
document.write('</ul>')