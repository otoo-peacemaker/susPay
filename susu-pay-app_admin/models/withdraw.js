const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const withDrawSchema = new Schema({
    depositeVendor : {
    type: String,
    required: true
  },

  accountNumber: {
    type: Number,
    required: true
  },
  username: {
    type: String,
    required: true
  },
  amountToWithdraw: {
    type: String,
    required: true
  },
  accountType: {
    type: String,
    required: true
  }
});

module.exports = mongoose.model('withdrawal', withDrawSchema);
