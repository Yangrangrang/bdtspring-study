alert("안녕!");
// 타이틀 h1 에 마우스를 올리면 색이 바뀌는 이벤트를 정의

document.querySelector("h1").addEventListener("mouseover",e){
    e.target.style.color = "blue";
};
// h1을 Selector로 선택한 이유는 암묵적으로 h1은 하나만 쓰게 되어있다.
// 의미론적 태그요소로 도큐먼트 전체의 제목이다 (검색엔진이 그런 의미를 갖는다)


