let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save: function() {
		//alert("user의 save함수 호출됨");
		let data = {
			username: $("#username").val(),
			password: $("password").val(),
			email: $("email").val()
		}
		
		//console.log(data);
		
		$.ajax().done().fail(); // 3개의 데이터를 json으로 변경하여 ajax 통신을 이용해서 insert 요청
		// =======================================
		// 회원가입 시 Ajax를 사용하는 이유
		// 1. 요청에 대한 응답을 html이 아닌 Data(json)를 받기 위해 
		//  - 서버 1개로 WEB/APP을 다 사용할 수 있다.
		// 2. 비동기 통신을 하기 위해
		// =======================================
	}
}

index.init();