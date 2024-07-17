

package com.example.example_flutter_blue

import io.flutter.embedding.android.FlutterActivity
import io.flutter.app.FlutterApplication;

import com.rmawatson.flutterisolate.FlutterIsolatePlugin;

class MainApplication : FlutterApplication() {

    init { 
        FlutterIsolatePlugin.setCustomIsolateRegistrant(CustomPluginRegistrant::class.java);
    }
}
