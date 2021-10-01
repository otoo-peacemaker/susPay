const mongoose = require('mongoose');
const Product = require('../models/product');
const Deposit = require('../models/deposit');
const Withdraw = require('../models/withdraw');
const { validationResult } = require('express-validator/check');

exports.getAddProduct = (req, res, next) => {
  res.render('admin/edit-product', {
    pageTitle: 'Add Product',
    path: '/admin/add-product',
    editing: false,
    hasError: false,
    errorMessage: null,
    validationErrors: []
  });
};

exports.postAddProduct = (req, res, next) => {
  const title = req.body.title;
  const imageUrl = req.body.imageUrl;
  const price = req.body.price;
  const description = req.body.description;
  const errors = validationResult(req);

  if (!errors.isEmpty()) {
    console.log(errors.array());
    return res.status(422).render('admin/edit-product', {
      pageTitle: 'Add Product',
      path: '/admin/add-product',
      editing: false,
      hasError: true,
      product: {
        title: title,
        imageUrl: imageUrl,
        price: price,
        description: description
      },
      errorMessage: errors.array()[0].msg,
      validationErrors: errors.array()
    });
  }

  const product = new Product({
    _id: new mongoose.Types.ObjectId('5badf72403fd8b5be0366e81'),
    title: title,
    price: price,
    description: description,
    imageUrl: imageUrl,
    userId: req.user
  });
  product
    .save()
    .then(result => {
      // console.log(result);
      console.log('Created Product');
      res.redirect('/admin/products');
    })
    .catch(err => {
      res.redirect('/500');
    });
};

exports.getEditProduct = (req, res, next) => {
  const editMode = req.query.edit;
  if (!editMode) {
    return res.redirect('/');
  }
  const prodId = req.params.productId;
  Product.findById(prodId)
    .then(product => {
      if (!product) {
        return res.redirect('/');
      }
      res.render('admin/edit-product', {
        pageTitle: 'Edit Product',
        path: '/admin/edit-product',
        editing: editMode,
        product: product,
        hasError: false,
        errorMessage: null,
        validationErrors: []
      });
    })
    .catch(err => console.log(err));
};

exports.postEditProduct = (req, res, next) => {
  const prodId = req.body.productId;
  const updatedTitle = req.body.title;
  const updatedPrice = req.body.price;
  const updatedImageUrl = req.body.imageUrl;
  const updatedDesc = req.body.description;

  const errors = validationResult(req);

  if (!errors.isEmpty()) {
    return res.status(422).render('admin/edit-product', {
      pageTitle: 'Edit Product',
      path: '/admin/edit-product',
      editing: true,
      hasError: true,
      product: {
        title: updatedTitle,
        imageUrl: updatedImageUrl,
        price: updatedPrice,
        description: updatedDesc,
        _id: prodId
      },
      errorMessage: errors.array()[0].msg,
      validationErrors: errors.array()
    });
  }

  Product.findById(prodId)
    .then(product => {
      if (product.userId.toString() !== req.user._id.toString()) {
        return res.redirect('/');
      }
      product.title = updatedTitle;
      product.price = updatedPrice;
      product.description = updatedDesc;
      product.imageUrl = updatedImageUrl;
      return product.save().then(result => {
        console.log('UPDATED PRODUCT!');
        res.redirect('/admin/products');
      });
    })
    .catch(err => console.log(err));
};

exports.getProducts = (req, res, next) => {
  Product.find({ userId: req.user._id })
    // .select('title price -_id')
    // .populate('userId', 'name')
    .then(products => {
      console.log(products);
      res.render('admin/products', {
        prods: products,
        pageTitle: 'Admin Products',
        path: '/admin/products'
      });
    })
    .catch(err => console.log(err));
};

exports.postDeleteProduct = (req, res, next) => {
  const prodId = req.body.productId;
  Product.deleteOne({ _id: prodId, userId: req.user._id })
    .then(() => {
      console.log('DESTROYED PRODUCT');
      res.redirect('/admin/products');
    })
    .catch(err => console.log(err));
};



// @desc          Delete a single Company
// @route         www.workport.com/index
// @access        Private
exports.getAdminDashboard = async (req, res, next) => {
  const deposites = await Deposit.find({})
  await res.status(200).render("admin/dashboard", {
    pageTitle: "Dashboard",
    path: "/admin/dashboard",
    deposites
  });
};

// @desc          Delete a single Company
// @route         www.workport.com/index
// @access        Private
exports.getAdminMyCards = async (req, res, next) => {
  await res.status(200).render("admin/my-cards", {
    pageTitle: "Cards",
    path: "/admin/my-cards",
  });
};


exports.getAdminMyPayment =  async (req, res, next) =>{
  await res.status(200).render("admin/my-payments", {
    pageTitle: "Payment",
    path: "/admin/my-payment",
  });
}


// @desc          Delete a single Company
// @route         www.workport.com/index
// @access        Private
exports.postAdminMyPayment = async (req, res, next) => {
  const depositeVendor = req.body.depositeVendor
  const accountNumber = req.body.accountNumber
  const username = req.body.username
  const amountToWithdraw = req.body.amountToWithdraw
  const accountType = req.body.accountType

  const deposite = new Deposit({
    depositeVendor: depositeVendor ,
    accountNumber: accountNumber,
    username: username,
    amountToWithdraw: amountToWithdraw,
    accountType: accountType

    });

    deposite.save().then((result) => {
      res.redirect('/admin/my-transcations')
    })
    .catch((err) => {
      console.log(err)
    })
};



// @desc          Delete a single Company
// @route         www.workport.com/index
// @access        Private
exports.getAdminMyServices = async (req, res, next) => {
 const deposites = await Deposit.find({})
  await res.status(200).render("admin/my-services", {
    pageTitle: "Services",
    path: "/admin/my-services",
    deposites
  });
};


exports.postAdminMyServices = async (req , res , next) => {
  const depositeVendor = req.body.depositeVendor
  const accountNumber = req.body.accountNumber
  const username = req.body.username
  const amountToWithdraw = req.body.amountToWithdraw
  const accountType = req.body.accountType

   const withdraw = new Withdraw({
    depositeVendor: depositeVendor,
    accountNumber: accountNumber,
    username: username,
    amountToWithdraw: amountToWithdraw,
    accountType: accountType

    });

    withdraw.save().then((result) => {
      res.redirect('/admin/my-transcations')
    })
    .catch((err) => {
      console.log(err)
    })

}




// @desc          Delete a single Company
// @route         www.workport.com/index
// @access        Private 
exports.getAdminMyCards = async (req, res, next) => {
  await res.status(200).render("admin/my-cards", {
    pageTitle: "Account Service",
    path: "/admin/my-cards"
  });
};



// @desc          Delete a single Company
// @route         www.workport.com/index
// @access        Private
exports.getAdminMyTranscations = async (req, res, next) => {
  const deposites = await Deposit.find({});

  await res.status(200).render("admin/my-transcations", {
    pageTitle: "Transcations",
    path: "/admin/my-transcations",
    deposites
  });
};

