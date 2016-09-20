var app = require("express")();
var http = require("http").Server(app);
var io = require('socket.io')(http);
app.get('/',function(req,res){
    res.sendFile(__dirname+'/index.html');
})
io.on('connection',function(socket){
    console.log('one user connected '+socket.id);
    socket.on("newMessage",function(data){
        console.log(data);
        socket.emit("newMessage",{message:data});
    })

})
http.listen(3000,function(){
    console.log('server listening on port 3000');
})