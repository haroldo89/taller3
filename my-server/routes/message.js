var express = require('express');
var router = express.Router();

router.post('/sendMessage', function (req, res, next) {
  var io = req.app.get('socketIo');
  io.emit('new-message', req.body.format);
  console.log(req.body.format);
  res.status(200).send(req.body);
});

module.exports = router;
