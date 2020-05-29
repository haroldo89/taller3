var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/sendMessage', function (req, res, next) {
  var io = req.app.get('socketIo');
  io.emit('new-message', req.query.format);
  res.send('[GET] mensaje desde el GET:' + req.query.format);
});


module.exports = router;
