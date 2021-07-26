const students = [];
//학생이름, 국어, 수학, 영어
const student1 = {
    name: '오혜지',
    kor: 85,
    mat: 95,
    eng: 83
}
const student2 = {
    name: '권가영',
    kor: 93,
    mat: 87,
    eng: 90
}
const student3 = {
    name: '홍길동',
    kor: 32,
    mat: 55,
    eng: 38
}
const fields = {
    name: '학생이름',
    kor: '국어점수',
    math: '수학점수',
    eng: '영어점수'
}

students.push(student1);
students.push(student2);
students.push(student3);

document.write('<table border ="1">');
document.write('<thead>');
for (field in fields) {
    document.write('<th>' + fields[field] + '</th>')
}
// document.write('<th>학생이름</th>');
// document.write('<th>국어점수</th>');
// document.write('<th>수학점수</th>');
// document.write('<th>영어점수</th>');
document.write('</thead>');
document.write('<tbody>');
for (let i = 0; i < students.length; i++) {
    document.write('<tr>');
    for (field in students[i]) {
        document.write('<td align = center>' + students[i][field] + '</td>');
    }
    document.write('</tr>');
}
document.write('</tbody>');
document.write('</table>');