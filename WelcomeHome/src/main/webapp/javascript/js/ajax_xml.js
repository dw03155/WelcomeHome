document.addEventListener('DOMContentLoaded', function () {
    ajax('dataset.xml', 1);
    //Ajax = Asynchronous Javascript and XML
    //동기적 방식 - 순서대로 처리 후 요청
    //비동기적(asynchronous) 방식 - 한꺼번에 요청 후 처리되는 순서대로 출력
    let btns = document.querySelectorAll('.pagination>button');
    for (let i = 0; i < btns.length; i++) {
        btns[i].addEventListener('click', function () {
            for (let i = 0; i < btns.length; i++) {
                btns[i].className = '';
            }
            let page = this.innerHTML;
            this.className = 'active';
            ajax('dataset.xml', page);
        })
    }
})

function ajax(url, page) {
    let xhtp = new XMLHttpRequest();

    // xhtp.open('get', 'MOCK_DATA.json');

    xhtp.open('get', url); //요청방식(get, post)
    xhtp.send(); //send 후 요청작업 시작
    xhtp.onreadystatechange = function () { //서버에 여러번 요청하고 요청상태를 바꿔줌
        if (xhtp.readyState == 4 && xhtp.status == 200) { //4,200일때 정상적 상태
            // console.log(JSON.parse(xhtp.response));
            // console.log(xhtp.response); //response 담은 값을 text타입으로 출력
            // console.log(xhtp.responseText); //text타입으로 보여줌
            console.log(xhtp.responseXML); //XML타입으로 보여줌

            makePage(xhtp.responseXML, page);

        }
    }
}

function makePage(data, page) {
    //기존 데이터를 화면에서 삭제함
    let divShow = document.querySelector('#show');
    let cnt = divShow.children.length;
    for (let i = 0; i < cnt; i++) {
        divShow.removeChild(divShow.children[0]);
    }
    // 페이지 수에 따라 화면 출력
    let records = data.getElementsByTagName('record');
    let startCnt = (page - 1) * 10; //0
    let endCnt = page * 10 - 1; //9
    endCnt = endCnt > records.length - 1 ? records.length - 1 : endCnt;
    //마지막 페이지 숫자가 endCnt보다 작을 때는 159라는 끝 숫자를 154로 바꾸어 154번째까지만 출력
    console.log(records);
    for (let i = startCnt; i <= endCnt; i++) {
        let div = document.createElement('div');
        div.className = 'row';
        let span = document.createElement('span');
        span.innerText = records[i].children[0].innerHTML;
        let strong = document.createElement('strong');
        strong.innerText = records[i].children[1].innerHTML;
        // div.appendChild(span);
        // div.appendChild(strong);
        div.append(span, strong);

        document.getElementById('show').appendChild(div);
    }
}