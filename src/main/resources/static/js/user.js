let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //function (){}으로 사용하지 않고, 화살표함수 ()=>{}를
										//사용한 이유는 함수를 줄이기 위해서가 아닌, this를 바인딩하기 위해서 사용
										//
			this.save();
		}); //누군가가 btn-save를 찾아서, click이 on(실행)되면, 함수 실행
		
		//전통적인 로그인방식
		//$("#btn-login").on("click", ()=>{
			//this.login();
		//}); //누군가가 btn-save를 찾아서, click이 on(실행)되면, 함수 실행
	},
	
	save: function(){
		//alert("user의 save함수 호출됨");
		let data = { //아래 3개 데이터를 id값으로(#~) 찾아서 해당 id가 들고있는 값을 data라는 변수에 넣음
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
		};
		
		//console.log(data); 데이터가 정상적으로 저장되는지 출력해보기. 웹의 F12 Console에서 확인
		$.ajax({ //ajax호출 시 기본 default가 비동기 호출이기 때문에 다른 코드도 병렬수행 가능
			//회원가입 수행을 요청
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), //http body데이터 ()자바스크립트 데이터를 json으로 변경)
			contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME) 윗줄과 세트
			//자동으로 해줌-> dataType:"json" //응답을 json으로 받음 (요청에 대한 응답이 왔을 때, 기본적으로 문자열인데 데이터타입이 json이라면 javascript오브젝트로 변경)
			
		}).done(function(resp){
			if(resp.status === 500){
				alert("아이디가 이미 존재합니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				location.href = "/"; //blog로 이동
			}
		}).fail(function(error){
			//응답의 결과가 실패면 실행
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
	},
}

index.init();