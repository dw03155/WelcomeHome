
function makeRow(obj) {
    let tr = document.createElement('tr');
    tr.addEventListener('mouseover', mover, true); //true : 상위요소에서 하위요소로 이벤트 전파
    tr.addEventListener('mouseout', mout, true); //false : 하위요소에서 상위요소로 이벤트 전파
    tr.addEventListener('click', trClick, true); //false 하게되면 stopPropagation에 의해서 상위요소인 alert 실행안됨
    for (let field in obj) {
        console.log(`필드:${field},필드값:${obj[field]}`)
        let td = document.createElement('td');
        let text = document.createTextNode(obj[field]);
        td.appendChild(text);
        tr.appendChild(td);
    }
    let td = document.createElement('td');
    let btn = document.createElement('input');
    btn.type = 'button';
    btn.value = '삭제';
    btn.addEventListener('click', deleteRow); //funtion() 붙이면 바로 실행하기 때문에 붙이지 않음 //콜백함수
    td.appendChild(btn);
    tr.appendChild(td);
    
    return tr;
}

let deleteRow = (arg) => { //function 선언
    //arrow function일 경우에는 this 키워드는 window object

    // console.log(arg.target);
    // console.log(arg.target.parentNode.parentNode.childNodes[2].innerText); //이름text
    // console.log(arg.target.parentNode.parentNode.childNodes[2].nextSibling); //메일tag
    // console.log(arg.target.parentNode.parentNode.childNodes[2].previousSibling); //성tag
    
    arg.stopPropagation(); //이벤트 전파 차단
    arg.target.parentElement.parentElement.remove();
}

let mover = function(arg) {
    // console.log(arg.target);
    this.style.backgroundColor = 'lavender';
}

let mout = function (arg) {
    //function일 경우 this 키워드는 event받는 대상
    this.style.backgroundColor = '';
}

let trClick = function(){
    window.alert(this.children[0].innerHTML);
}