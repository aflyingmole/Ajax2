<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>movieFetch.jsp</title>
    <style>
        .comm {
            width: 400px;
            height: 100px;
            border: 1px solid #aaa;
            margin-bottom: 5px
        }
    </style>
    <script type="text/javascript">
        function commList() {
            fetch('comm/list?mnum=${vo.mnum}', {method: 'GET'})
                .then(response => response.json())
                .then(data => {
                    const commList = document.getElementById("commList")
                    const c_len = commList.childElementCount;
                    const childs = commList.childNodes;
                    for (let i = c_len - 1; i >= 0; i--) {
                        let c = childs.item(i);
                        commList.removeChild(c)
                    }
                    data.list.forEach(comment => {
                        const html = "<div class='comm'>"
                            + "번호 : " + comment.num + "<br>"
                            + "작성자 : " + comment.id + "<br>"
                            + "내용 : " + comment.comments + "<br>"
                            + "<a href='javascript:delComm(" + comment.num + ")'>삭제</a>";
                        commList.innerHTML += html;
                    });
                });
        }

        function delComm(num) {
            fetch('comm/delete?num=' + num)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    if (data.list > 0) {
                        alert('삭제성공');
                        commList();
                    } else {
                        alert('삭제 실패');
                    }
                });
        }

            function addComm() {
                const id = document.getElementById("id").value;
                const comments = document.getElementById("comments").value;
                const param = "id=" + id + "&comments=" + comments + "&mnum=${vo.mnum}";
                fetch('/comm/insert', {
                    method: 'post',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    body: param
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.list > 0) {
                            commList();
                        } else {
                            alert( '댓글 등록 실패');
                        }
                    });
            }


        window.onload = () => commList();
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
