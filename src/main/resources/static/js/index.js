// 팝업레이어

const loginOpen = document.querySelector('.login_open a');
const loginLayer = document.querySelector('.login-layer');
const loginForm = document.querySelector('.login_form');
const spanClose = document.querySelector('span.close');

loginOpen.addEventListener('click',(e)=>{
    // a, submit -> 기본이벤트 적용하지 않을 시
    // 반드시 기본이벤트를 중지
    e.preventDefault();

    // 최상위 부모 요소를 보이게
    loginLayer.style.display="flex";

})


spanClose.addEventListener('click', function(){
    loginLayer.style.display="none";
})


// 자동갤러리

const galleryLi = document.querySelectorAll('.gallery-con>ul>li');
const itemLi = document.querySelectorAll('.item-con>ul>li');

let i = -1;

function autoGallery(){
  if(i>=galleryLi.length-1){
    i=-1;
  }
  i++;

  console.log(`i=>${i}`);

  galleryLi.forEach((el,idx)=>{
    if(i==idx){
      el.classList.add('fadeLi');
    } else {
      el.classList.remove('fadeLi');
    }
  })

  itemLi.forEach((el,idx)=>{
    if(i==idx){
      el.classList.add('on');
    } else {
      el.classList.remove('on');
    }
  })

}

let setIn = setInterval(autoGallery,3000);

(function(){
  autoGallery();
})()

const itemUl = document.querySelector('.item-con>ul');

itemUl.addEventListener('mouseover',itemUlfn);
itemUl.addEventListener('mouseout',itemUlfn);

function itemUlfn(e){
  itemLi.forEach((el,idx)=>{
    if(e.target==el){
      if(e.type=='mouseover'){
        console.log('e.type : '+e.type);
        clearInterval(setIn);
      } else {
        console.log('e.type : '+e.type);
        setIn = setInterval(autoGallery,3000);
      }
    }
  })
}

itemUl.addEventListener('click',galleryFn);

function galleryFn(e){
  itemLi.forEach((el,idx)=>{
    if(e.target==el){
      console.log('e.type : '+e.type);
      el.classList.add('on');

      galleryLi.forEach((el2,idx2)=>{
        if(idx==idx2){
          el2.classList.add('fadeLi');
        } else {
          el2.classList.remove('fadeLi');
        }
      })
    } else {
      el.classList.remove('on');
    } 
    i = idx;
  })
}

const arrow = document.querySelectorAll('span.arrow');

arrow[0].addEventListener('click',(e)=>{
  if(i<=0){
    i=galleryLi.length;
  }

  i--;

  console.log(i)

  galleryLi.forEach((el,idx)=>{
      if(i==idx){
        // '.fadeLi' 클래스 추가
        el.classList.add('fadeLi')
      } else {
        el.classList.remove('fadeLi')
      }
    })

    itemLi.forEach((el,idx)=>{
      if(i==idx){
        el.classList.add('on')
      } else {
        el.classList.remove('on')
      }
    })

  })

  arrow[1].addEventListener('click',(e)=>{
    if(i>=galleryLi.length-1){
      i=-1;
    }
    
    i++;

    console.log(i)

    galleryLi.forEach((el,idx)=>{
      if(i==idx){
        el.classList.add('fadeLi')
      } else {
        el.classList.remove('fadeLi')
      }
    })

    itemLi.forEach((el,idx)=>{
      if(i==idx) {
        el.classList.add('on')
      } else {
        el.classList.remove('on')
      }
    })
  })
