var mongoose = require('mongoose');

// Barcode Schema
var barcodeSchema = mongoose.Schema({
  code: {
    type: String,
    required: true
  },
  name: {
    type: String,
    required: false
  },
  created_date: {
    type: Date,
    default: Date.now
  }
});

var Barcode = module.exports = mongoose.model('barcode', barcodeSchema);

// Get Barcodes
module.exports.findAll = function(callback, limit) {
  Barcode.find(callback).limit(limit);
}

// Get Barcode
module.exports.findOne = function(code, callback) {
  console.log(code);
  Barcode.find({code: code}, callback);
}

// Add Barcode
module.exports.insertOne = function(barcode, callback) {
  Barcode.create(barcode, callback);
}