const path = require('path');

const express = require('express');
const { body } = require('express-validator/check');

const adminController = require('../controllers/admin');
const isAuth = require('../middleware/is-auth');

const router = express.Router();

// /admin/add-product => GET
router.get('/add-product', isAuth, adminController.getAddProduct);

// /admin/products => GET
router.get('/products', isAuth, adminController.getProducts);
router.get('/dashboard', adminController.getAdminDashboard);

router.get('/my-services' , adminController.getAdminMyServices);
router.post('/my-services' , adminController.postAdminMyServices);


router.get('/my-cards', adminController.getAdminMyCards);
router.get('/my-transcations', adminController.getAdminMyTranscations);

router.get('/my-payments' , adminController.getAdminMyPayment);
router.post('/my-payments' , adminController.postAdminMyPayment);

module.exports = router;




