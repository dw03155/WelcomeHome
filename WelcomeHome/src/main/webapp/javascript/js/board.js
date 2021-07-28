function createTr(num, title, writer, regDate) {

    let trTag = document.createElement('tr');
    trTag.setAttribute('id', num)
    for (let i = 0; i < arguments.length; i++) {
        let tdTag = document.createElement('td');
        tdTag.setAttribute('class', 'td' + (i + 2));
        let text = document.createTextNode(arguments[i]);
        
        tdTag.appendChild(text);
        trTag.appendChild(tdTag);
    }

    //등록할 때 checkbox 추가하기
    let tdTag = document.createElement('td');
    let inputTag = document.createElement('input');
    inputTag.setAttribute('type', 'checkbox');
    tdTag.appendChild(inputTag);
    trTag.appendChild(tdTag);

    return trTag;
}


function submitBoard(ev) {
    ev.preventDefault();
    let list = document.querySelector('#list > tbody');
    let num = document.bfm.num.value;
    let title = document.bfm.title.value;
    let writer = document.bfm.writer.value;
    let regDate = document.bfm.regDate.value;

    if (document.bfm.num.value == '') {
        alert('번호를 입력하세요.')
        document.bfm.num.focus();
        return;
    }
    if (document.bfm.title.value == '') {
        alert('제목을 입력하세요.')
        document.bfm.title.focus();
        return;
    }
    if (document.bfm.writer.value == '') {
        alert('이름을 입력하세요.')
        document.bfm.writer.focus();
        return;
    }
    if (document.bfm.regDate.value == '') {
        alert('날짜를 입력하세요.')
        document.bfm.regDate.focus();
        return;
    }

    let childCnt = list.children.length + 1;
    if (childCnt % 2 == 0) {
        let liTag = createTr(num, title, writer, regDate);
        liTag.setAttribute('class', 'altRow');
        //liTag.className='altRow'
        list.appendChild(liTag);
    } else {
        list.appendChild(createTr(num, title, writer, regDate))
    }
    document.bfm.num.value = childCnt + 1;

    addTrEvent();

}

function addTrEvent() {
    let trs = document.querySelectorAll('#list > tbody > tr');
    console.log(trs);
    for (let i = 0; i < trs.length; i++) {
        // trs[i].onclick;
        trs[i].addEventListener('click', function () {
            // this.children[0].innerHTML; //첫번째 값, 번호 가져오기
            // this.children[1].innerHTML; //두번째 값, 내용 가져오기
            // this.children[2].innerHTML; //세번째 값, 이름 가져오기
            // this.children[3].innerHTML; //네번째 값, 날짜 가져오기
            document.getElementById('num').value = this.children[0].innerHTML;
            document.getElementById('title').value = this.children[1].innerHTML;
            document.getElementById('writer').value = this.children[2].innerHTML;
            document.getElementById('regDate').value = this.children[3].innerHTML;
        });
    }
}

//수정버튼을 클릭하면 실행될 eventHandler(코드)
let updateBtn = document.querySelector('#inputForm > form > input[type="button"]'); //id가 inputForm인 
console.log(updateBtn);
updateBtn.addEventListener('click', function () {
    //폼태그 안에 사용자가 수정한 값을 테이블에서 찾아와서(tr태그 id기준)
    let numInput = document.getElementById('num');
    let titInput = document.getElementById('title');
    let wriInput = document.getElementById('writer').value;
    let regInput = document.getElementById('regDate');

    console.log(numInput.value);
    let searchId = numInput.value;
    let findTr = document.getElementById(searchId);
    console.log(findTr);
    // 제목 수정
    findTr.children[1].innerHTML = titInput.value;
    // 이름 수정
    findTr.children[2].innerHTML = wriInput;
    // 날짜 수정
    findTr.children[3].innerHTML = regInput.value;
});


//선택 삭제 버튼 클릭하면 선택값만 삭제처리
document.getElementById('delBtn').addEventListener('click', function () {
    // 선택된 모든 요소 가져오기
    let checkedBox = document.querySelectorAll('#list > tbody > tr > td > input[type="checkbox"]:checked');
    console.log(checkedBox);
    for (let i = 0; i < checkedBox.length; i++) {
        checkedBox[i].parentNode.parentNode.remove();
    }
    //삭제 후에 색깔 바꿔주기
    let remainTr = document.querySelectorAll('#list > tbody > tr');
    for (let i = 0; i < remainTr.length; i++) {
        if (i % 2 == 1) {
            remainTr[i].className = 'altRow';
        } else {
            remainTr[i].className = '';
        }
    }
});