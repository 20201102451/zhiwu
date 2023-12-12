const http = require('http');
const WebSocket = require('ws');
const server = http.createServer();
const wss = new WebSocket.Server({server});
wss.on('connection', (socket) => {
    console.log("连接已打开");
    socket.send('Hello');
    socket.on('message', (message) => {
        console.log('收到消息：' + message);
        socket.send('Hello FireuG');
    });
    socket.on('close', () => {
        console.log('连接已关闭');
    });

});
server.on('request', (request, response) => {
    response.writeHead(200, {'Content-Type': 'text/plain'});
    response.end('He11o, World!');
});
server.listen(8181, () => {
    console.log('服务器已启动，端口号为 8181');
});
