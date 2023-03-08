// 자동 하이픈
const autoHyphen = (target) => {
    target.value = target.value
    .replace(/[^0-9]/g, '')
    .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3")
    .replace(/(\-{1,2})$/g, "");
}

const userName = document.querySelector('#userName');
const userPhone = document.querySelector('#userPhone');
const updateForm = document.querySelector('.updateForm');
const s3Sub = document.querySelector('.s3Sub');
const userPw = document.querySelector('#userPw');

/* 유효성 검사 */

s3Sub.addEventListener('click', function(event){

    event.preventDefault();

    if(userName.value == null || userName.value.length<1){
        alert("닉네임을 입력해주세요");
        userName.focus();
        return false;
    }

    if(userPhone.value == null || userPhone.value.length<1){
        alert("핸드폰 번호를 입력해주세요");
        userPhone.focus();
        return false;
    }

    if(userPw.value == null || userPw.value.length<1){
        alert("비밀번호를 입력해주세요");
        userPw.focus();
        return false;
    }

    updateForm.submit();
});