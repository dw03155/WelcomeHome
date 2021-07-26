const object = new Object();
object.name = 'Kim';
object.age = 30;

const obj = { //object 타입
    name: 'Hong',
    age: 20,
    hobby: ['sleeping', 'dancing', 'eating']
};
console.log('이름 : ' + obj.name);
console.log('나이 : ' + obj['age']);
console.log('취미 : ' + obj.hobby[0]);
console.log('취미 : ' + obj['hobby'][1]);
let hob = 'hobby';
console.log('취미 : ' + obj[hob][2]);

const mem1 = {
    id: 'user1',
    name: '홍길동',
    passwd: '1234'
}
const mem2 = {
    id: 'user2',
    name: '박민수',
    passwd: '1212'
}
const mem3 = {
    id: 'user3',
    name: '김효영',
    passwd: '4321'
}
for (field in mem3) { //for 확장
    console.log(field + ' : ' + mem3[field]);
}
const members = [mem1, mem2, mem3];
for (let i = 0; i < members.length; i++) {
    // console.log('아이디 : ' + members[i].id);
    // console.log('이름 : ' + members[i]['name']);
    // console.log('이름 : ' + members[i]['passwd']);
    for (field in members[i]) {
        console.log(field + ', ' + members[i][field])
    }
    console.log('-------------')
}