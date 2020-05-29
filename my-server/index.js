let express = require('express')
let app = express();

let messageRouter = require('./routes/message');

let http = require('http');
let server = http.Server(app);

let socketIO = require('socket.io');
let io = socketIO(server);

const port = process.env.PORT || 3000;

app.use('/message', messageRouter);

app.get('/hola', function (req, res) {
  res.send('[GET]Saludos desde express');
});

app.set('socketIo', io);

io.on('connection', (socket) => {
    console.log('user connected');
    socket.on('new-message', (message) => {
		console.log('mensaje enviado directo del socket:' + message);
		io.emit('new-message', message);
    });
});

server.listen(port, () => {
    console.log(`started on port: ${port}`);
});