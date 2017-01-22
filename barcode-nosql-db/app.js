var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

app.use(bodyParser.json());

Barcode = require('./models/barcode');

// Connect to Mongoose
var mongoUri = 'mongodb://localhost:27017/barcode';
var mongoDb = mongoose.connect(mongoUri).connection;
mongoDb.on('error', function(err) {
  console.log(err.message);
});

mongoDb.on('open', function() {
  console.log('mongodb connection open');
});

app.get('/', function(req, res) {
  console.log('Please use /api/barcode');
  res.send('Please use /api/barcode');
});

app.get('/api/static', function(req, res) {
  res.send("1");
});

app.get('/api/barcode', function(req, res) {
  console.log('GET: /api/barcode');
  Barcode.findAll(function(err, barcodes) {
    if (err) throw err;
    console.log(barcodes);
    res.json(barcodes);
  });
});

app.post('/api/barcode', function(req, res) {
  console.log('POST: /api/barcode');
  var barcode = req.body;
  Barcode.insertOne(barcode, function(err, barcodes) {
    if (err) throw err;
    res.json(barcodes);
  });
});

app.get('/api/barcode/:code', function(req, res) {
  console.log('GET: /api/barcode/:code');
  Barcode.findOne(req.params.code, function(err, barcode) {
    if (err) throw err;
    console.log(barcode);
    res.json(barcode);
  });
  // res.send("!");
});

app.put('/api/barcode/:id', function(req, res) {
  console.log('PUT: /api/barcode/:id');
  res.send('Hello World');
});

app.delete('/api/barcode/:id', function(req, res) {
  console.log('DELETE: /api/barcode/:id');
  res.send('Hello World');
});

app.listen(3000);
console.log('Running on port 3000...');