function ajax(url, page) {
    //기존 데이터를 화면에서 삭제함
    let divShow = document.querySelector('#show');
    let cnt = divShow.children.length;
    for (let i = 0; i < cnt; i++) {
        divShow.removeChild(divShow.children[0]);
    }
    let startCnt = (page - 1) * 10; // 0번째 데이터 부터
    let endCnt = page * 10 - 1; // 9번째 데이터 까지

    let xhtp = new XMLHttpRequest();
    xhtp.open('get', url); //요청방식(get, post)
    xhtp.send(); //send 후 요청작업 시작
    xhtp.onload = function () {
        let showDiv = document.getElementById('show');
        let data = JSON.parse(xhtp.responseText);

        let fData = data.filter(function (val, idx) {
            if (startCnt <= idx && idx <= endCnt) {
                return val;
            }
        }).forEach(function (val, idx) { //줄만들기
            let span = document.createElement('span');
            span.innerHTML = val.id;
            let strong1 = document.createElement('strong');
            strong1.innerHTML = val.first_name;
            let strong2 = document.createElement('strong');
            strong2.innerHTML = val.last_name;
            let strong3 = document.createElement('strong');
            strong3.innerHTML = val.email;
            let div = document.createElement('div')
            div.setAttribute('class', 'row');
            div.append(span, strong1, strong2, strong3);
            showDiv.appendChild(div);
        });
    }
}
ajax('MOCK_DATA.json', 1); //첫화면 1페이지
let btns = document.querySelectorAll('.pagination > button');
for (let i = 0; i < btns.length; i++) {
    btns[i].addEventListener('click', function () {
        //클릭 이벤트 시 button의 클래스를 초기화.
        for (let i = 0; i < btns.length; i++) {
            btns[i].className = '';
        }
        let page = this.innerHTML;
        this.className = 'active';
        ajax('MOCK_DATA.json', page); //button의 텍스트를 페이지 대신 씀
    });
}