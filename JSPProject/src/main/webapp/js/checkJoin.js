let checked = false;
let pwChecked = false;

function confirmId() {
    const username = document.querySelector("#id-text").value;
    $.ajax({
        type: "post",
        url: "/member/checkusername",
        data: { username: username },
        dataType: "json",
        success: function (data) {
            if (username == "" || username == null) {
                alert("아이디는 빈 값일 수 없습니다.");
                checked = false;
                return;
            } else if (data.result == '1') {
                alert("이미 존재하는 아이디입니다.");
                checked = false;
                return;
            } else if (data.result == '2') {
                alert("아이디 형식이 잘못되었습니다.");
                checked = false;
                return;
            } else if (data.result == '0') {
                alert("사용가능한 아이디입니다.");
                checked = true;
                const submitBtn = document.querySelector("#submit-Button");
                submitBtn.style.cursor = "pointer";
                submitBtn.disabled = false;
                return;
            } else {
                return;
            }
        }
    });
}

function confirmPw() {
    const password = document.querySelectorAll('input[type="password"]')[0].value;
    const passwordConfirm = document.querySelectorAll('input[type="password"]')[1].value;
    $.ajax({
        type: "post",
        url: "/member/checkpassword",
        data: { password: password,
        		passwordConfirm: passwordConfirm},
        dataType: "json",
        success: function (data) {
			if(data.result == '0') {
				pwChecked = false;
			} else if(data.result == '1'){
				pwChecked = true;
			}
        }
    });
}

function resetCheckedId() {
    checked = false;

    const submitBtn = document.querySelector("#submit-Button");
    console.log(document.querySelector("#id-text").value);
    submitBtn.style.cursor = "default";
    submitBtn.disabled = true;
}

window.onload = function () {
    resetCheckedId();
}