import 'package:flutter/material.dart';

import 'login_screen.dart';

void main() => runApp(SusuPay());

class SusuPay extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'SusuPay',
      debugShowCheckedModeBanner: false,
      home: loginscreen(),
    );
  }
}
