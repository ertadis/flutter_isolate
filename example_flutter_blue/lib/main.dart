import 'package:flutter/material.dart';
import 'package:flutter_blue/flutter_blue.dart';
import 'package:flutter_isolate/flutter_isolate.dart';

void main() {
  runApp(MyApp());
}

void scan() async {

}

void start_flutter_blue(Map<String,dynamic> args) {
  FlutterBlue flutterBlue = FlutterBlue.instance;
  // Start scanning
  flutterBlue.startScan(timeout: Duration(seconds: 4));

  // Listen to scan results
  var subscription = flutterBlue.scanResults.listen((results) {
      if(results.length == 0) {
        print("No results found");
      }
      for (ScanResult r in results) {
          print('${r.device.name} found! rssi: ${r.rssi}');
      }
  });
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              child:Text('PRESS ME'),
              onPressed: () {
                FlutterIsolate.spawn(start_flutter_blue, <String,dynamic>{});
              },
            ),
          ],
        ),
      ),
    );
  }
}
