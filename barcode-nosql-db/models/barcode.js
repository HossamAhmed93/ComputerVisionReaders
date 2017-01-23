var mongoose = require('mongoose');
var joi = require('joi');

// Barcode Schema
var barcodeSchema = mongoose.Schema({
  code: {
    type: String,
    required: true
  },
  type: {
    type: String,
    enum: ['BARCODE', 'QRCODE'],
    required: true
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

// Validate barcode data
function validateBarcode(barcode) {
  var schema = joi.object().keys({
    code: joi.string().required(),
    type: joi.string().valid('BARCODE', 'QRCODE')
  });

  joi.validate(barcode, schema, function(err) {
    if (err) return false;
    else return true;
  });
}

// Add Barcode
module.exports.insertOne = function(barcode, callback) {
  // Barcode.create({
  //   code: barcode.code,
  //   type: barcode.type
  // }, callback);
  Barcode.create(barcode, callback);
}