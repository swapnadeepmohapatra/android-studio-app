import 'package:flutter/material.dart';

String text = "";

void main() {
  runApp(new MyApp(
      title: new Text("My Application"), someText: new Text("Some Text")));
}

class MyApp extends StatefulWidget {
  MyApp({this.title, this.someText});

  final Widget title, someText;

  @override
  MyAppState createState() => new MyAppState();
}

class MyAppState extends State<MyApp> {
  bool showDialog = false;
  TextEditingController eCtrl = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          actions: <Widget>[
            new IconButton(
                icon: new Icon(Icons.add_alert),
                onPressed: () {
                  setState(() {
                    showDialog = true;
                  });
                }),
          ],
          title: widget.title,
        ),
        body: new Column(
          children: <Widget>[
            widget.someText,
            showDialog == true
                ? new AlertDialog(
                    title: new Text("Alert"),
              content: new TextField(
                decoration: new InputDecoration.collapsed(hintText: "Add Text"),
                maxLines: 3,
                controller: eCtrl,
                onSubmitted: (String text){

                },
              ),
              actions: <Widget>[
                new FlatButton(onPressed: (){
                  setState(() {
                    showDialog = false;
                    eCtrl.clear();
                  });
                }, child: new Text("ok"))
              ],
                  )
                : new Text(""),
          ],
        ),
      ),
    );
  }
}
