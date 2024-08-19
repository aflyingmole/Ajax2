<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .comm {
            width: 400px;
            height: 100px;
            border: 1px solid #aaa;
            margin-bottom: 5px
        }
    </style>
    <script type="text/javascript">
        function commentsList() {
            const xhr = new XMLHttpRequest();
            xhr.onload = function () {
                let result = xhr.responseText;
                let data = JSON.parse(result);
                // 기존의 댓글 지우기
                const commList = document.getElementById("commList");
                const c_len = commList.childElementCount;
                const childs = commList.childNodes;
                for (let i = c_len - 1; i >= 0; i--) {
                    let c = childs.item(i);
                    commList.removeChild(c)
                }
                // for (let i = 0; i < data.list.length; i++) {
                //     let num = data.list[i].num;
                //     let id = data.list[i].id;
                //     let comments = data.list[i].comments;
                //     const html = "<div class='comm'>" +
                //         ""
                //         + "번호 : " + num + "<br>"
                //         + "작성자 : " + id + "<br>"
                //         + "내용 : " + comments + "<br>"
                //         + "<a href='javascript:delComm(" + num + ")'>삭제</a>" + "<br>"
                //         + "<a href='javascript:updateComm(" + num + ")'>수정</a>";
                //     commList.innerHTML += html;
                // }
				data.list.forEach(comment => {
					const html = "<div class='comm'>" +
							""
							+ "번호 : " + comment.num + "<br>"
							+ "작성자 : " + comment.id + "<br>"
							+ "내용 : " + comment.comments + "<br>"
							+ "<a href='javascript:updateComm(" + comment.num + ")'>수정</a>" + "<br>"
							+ "<a href='javascript:delComm(" + comment.num + ")'>삭제</a>";
					commList.innerHTML += html;
				});
            }
            xhr.open('get', '/comm/list?mnum=${vo.mnum}', true);
            xhr.send();
        }

        function addComm() {
            const xhr = new XMLHttpRequest();
            xhr.onload = function () {
                const text = xhr.responseText;
                let data = JSON.parse(text);
                let list = data.list;
                if (list > 0) {
                    commentsList();
                } else {
                    alert('댓글 입력 실패');
                }
            };
            xhr.open('post', '/comm/insert', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            //post인 경우 콘텐츠 타입 지정해야 함
            const id = document.getElementById("id").value;
            const comments = document.getElementById("comments").value;
            const param = "id=" + id + "&comments=" + comments + "&mnum=${vo.mnum}";
            xhr.send(param);
        }

        function delComm(num) {
            const xhr = new XMLHttpRequest();
            xhr.onload = function () {
                const text = xhr.responseText;
                let data = JSON.parse(text);
                if (data.list > 0) {
                    commentsList();
                } else {
                    alert('댓글 삭제 실패');
                }
            }
            xhr.open('get', '/comm/delete?num=' + num, true)
            xhr.send();
        }

		function updateComm(){
			const xhr = new XMLHttpRequest();
			xhr.onload = function (){
				const text = xhr.responseText;
				let data = JSON.parse(text);
				if (data.list > 0) {
					commentsList();
				} else{
					alert('댓글 수정 실패')
				}
			}
			xhr.open('get', '/comm/update?num=' + num + '&comments=' + comments , true)
			xhr.send();
		}

        window.onload = function () {
            commentsList();
        };


    </script>
</head>
<body>
<div style="width: 400px; height: 200px; background-color: #ccc">
    <h1>${vo.title}</h1>
    <p>
        내용 : ${vo.content} <br>
        감독 : ${vo.director} <br>
    </p>

</div>
<div>
    <div id="commList"></div>
    <div id="commAdd">
        아이디 <br>
        <input type="text" id="id"> <br>
        영화평 <br>
        <textarea rows="3" cols="50" id="comments"></textarea> <br>
        <input type="button" value="등록" onclick="addComm()">

    </div>
</div>
</body>
</html>
