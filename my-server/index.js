let express = require('express')
let bodyParser = require('body-parser');
let app = express();
let cors = require('cors')

let messageRouter = require('./routes/message');

let http = require('http');
let server = http.Server(app);

let socketIO = require('socket.io');
let io = socketIO(server);

const port = process.env.PORT || 3000;

app.use(cors())
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use('/message', messageRouter);

app.get('/hola', function (req, res) {
  res.send('soy el endpoint de brazo robot');
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