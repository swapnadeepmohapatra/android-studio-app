import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return new Material(
      color: Colors.blue,
      child: new InkWell(
          child: new Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          new Text(
            "Hello World",
            style: new TextStyle(color: Colors.pink, fontSize: 30.0),
          ),
          new Text(
              "Hello",
              style: new TextStyle(color: Colors.white, fontSize: 20.0),),
        ],
      )),
    );
  }
}
