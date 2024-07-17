package com.example.example_flutter_blue

import io.flutter.embedding.engine.FlutterEngine;

class CustomPluginRegistrant {
    companion object {
        @JvmStatic
        fun registerWith(flutterEngine:FlutterEngine) {
            val pluginRegistry = flutterEngine.getPlugins()
            if(!pluginRegistry.has(com.pauldemarco.flutter_blue.FlutterBluePlugin::class.java))
                pluginRegistry.add(com.pauldemarco.flutter_blue.FlutterBluePlugin());
            if(!pluginRegistry.has(com.rmawatson.flutterisolate.FlutterIsolatePlugin::class.java))
                pluginRegistry.add(com.rmawatson.flutterisolate.FlutterIsolatePlugin());
        }

    }
}