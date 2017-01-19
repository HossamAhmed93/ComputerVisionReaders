var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

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
  res.send('Please use /api/barcode');
});

app.get('/barcode', function(req, res) {
  res.send('Hello World');
});

app.post('/barcode', function(req, res) {
  res.send('Hello World');
});

app.get('/barcode/:id', function(req, res) {
  res.send('Hello World');
});

app.put('/barcode/:id', function(req, res) {
  res.send('Hello World');
});

app.delete('/barcode/:id', function(req, res) {
  res.send('Hello World');
});

app.listen(3000);
console.log('Running on port 3000...');