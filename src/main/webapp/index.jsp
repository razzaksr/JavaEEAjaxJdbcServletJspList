<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script>
        $(document).ready(()=>{
            $("table").mouseenter(()=>{
                $.get("list",(data)=>{
                    var vary=JSON.parse(data);
                    vary.map((v)=>{
                        $("#result").append(
                            "<tr><td>"+v.id+"</td><td>"+v.name+"</td><td>"+v.price+"</td></tr>");
                    })
                })
            })
            $("table").mouseleave(()=>{
                $("#result").html("");
            })
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>
<table>
    <thead>
        <tr>
            <th>Id</th><th>Name</th><th>Price</th>
        </tr>
    </thead>
    <tbody id="result">

    </tbody>
</table>
</body>
</html>
