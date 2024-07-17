package com.example.engine_group_isolate_test

import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.engine.FlutterEngineCache
import android.content.Intent
import io.flutter.embedding.engine.FlutterEngine
import android.content.Context
import android.os.Bundle


class MainActivity: FlutterActivity(), EngineBindingsDelegate {
    
    private val engineBindings: EngineBindings by lazy {
        EngineBindings(activity = this, delegate = this, entrypoint = "main")
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        engineBindings.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        engineBindings.detach()
    }

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return engineBindings.engine1
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        android.util.Log.e("FlutterIsolate", "CONFIGURING FLUTTER ENGINE");

    }

}
