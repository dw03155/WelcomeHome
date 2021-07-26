const numbers = [23, 44, 18, 35, 50];
numbers.push(42); // 기존 배열에 추가
let sum = 0;
// for (let i = 0; i < numbers.length; i++) {
//     console.log(numbers[i]);
//     sum += numbers[i];
// }
// console.log(sum);

document.write('<table>')
document.write('<tbody>')
document.write('<tr>')
document.write('<th></th>');
document.write('<th>' + numbers[0] + '</th>');
document.write('</tr>')
for (let i = 1; i < numbers.length; i++) {
    document.write('<tr>')
    document.write('<th> + </th>');
    document.write('<th>' + numbers[i] + '</th>');
    document.write('</tr>')
}
document.write('</tr><tr>')
for (let i = 0; i < numbers.length; i++) {
    sum += numbers[i];
}
document.write('<th>합계</th>');
document.write('<th>' + sum + '</th></tr>')
document.write('</tbody>')
document.write('</table>')