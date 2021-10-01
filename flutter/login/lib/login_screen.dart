import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// ignore: camel_case_types
class loginscreen extends StatefulWidget {
  loginscreen({Key? key}) : super(key: key);

  @override
  _nameState createState() => _nameState();
}

// ignore: camel_case_types
class _nameState extends State<loginscreen> {
  Color light_green = const Color(0xFF19B832);
  Color orange_yellow = const Color(0xFFFFB731);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[100],
      body: SingleChildScrollView(
        child: Column(
          children: [
            Center(
              child: Container(
                height: 500,
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage('assets/images/logo.png'),
                  ),
                ),
              ),
            ),
            SizedBox(
              height: 1,
            ),
            Container(
              child: Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: buildTextField('Enter your number', false, false),
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: buildTextField('Enter your password', false, false),
                  ),
                  SizedBox(
                    height: 0,
                  ),
                  Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(top: 0, left: 10),
                        child: TextButton(
                          onPressed: () {},
                          child: Text(
                            'Forgot password?',
                            style: TextStyle(
                              fontSize: 15,
                              color: orange_yellow,
                            ),
                          ),
                        ),
                      ),
                      SizedBox(
                        height: 20,
                      ),
                      buildTextButton('Log in', light_green),
                    ],
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                      top: 25,
                      left: 100,
                    ),
                    child: Row(
                      children: [
                        Text(
                          'Don\'t have account?',
                          style: TextStyle(
                            fontSize: 15,
                            color: Colors.black.withOpacity(0.3),
                          ),
                        ),
                        TextButton(
                          onPressed: () {},
                          child: Text(
                            'Register',
                            style: TextStyle(
                              fontSize: 17,
                              color: orange_yellow,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

Widget buildTextField(
  String hintText,
  bool isPassword,
  bool isEmail,
) {
  return Padding(
    padding: const EdgeInsets.only(bottom: 5.0),
    child: TextField(
      obscureText: isPassword,
      keyboardType: isEmail ? TextInputType.emailAddress : TextInputType.text,
      decoration: InputDecoration(
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(
            color: Colors.grey,
          ),
          borderRadius: BorderRadius.all(
            Radius.circular(20.0),
          ),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(
            color: Colors.grey,
          ),
          borderRadius: BorderRadius.all(
            Radius.circular(20.0),
          ),
        ),
        hintText: hintText,
        hintStyle: TextStyle(
          fontSize: 14,
          color: Colors.grey,
        ),
      ),
    ),
  );
}

TextButton buildTextButton(String title, Color color) {
  return TextButton(
    onPressed: () {},
    style: TextButton.styleFrom(
      minimumSize: Size(145, 40),
      side: BorderSide(width: 1, color: Colors.grey),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      backgroundColor: color,
      primary: Colors.white,
    ),
    child: Row(
      children: [
        SizedBox(
          width: 5,
        ),
        Text(
          title,
        )
      ],
    ),
  );
}
