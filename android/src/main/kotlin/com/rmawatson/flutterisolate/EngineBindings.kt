// package com.example.engine_group_isolate_test

// import android.app.Activity
// import io.flutter.FlutterInjector
// import io.flutter.embedding.engine.FlutterEngine
// import io.flutter.embedding.engine.dart.DartExecutor

// import io.flutter.plugin.common.EventChannel;
// import io.flutter.plugin.common.EventChannel.StreamHandler;
// import io.flutter.plugin.common.MethodChannel;
// import io.flutter.plugin.common.MethodCall;
// import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

// /**
//  * This interface represents the notifications an EngineBindings may be receiving from the Flutter
//  * instance.
//  *
//  * What methods this interface has depends on the messages that are sent over the EngineBinding's
//  * channel in `main.dart`.  Messages that interact with the DataModel are handled automatically
//  * by the EngineBindings.
//  *
//  * @see main.dart for what messages are getting sent from Flutter.
//  */
// interface EngineBindingsDelegate {
    
// }

// /**
//  * This binds a FlutterEngine instance with the DataModel and a channel for communicating with that
//  * engine.
//  *
//  * Messages involving the DataModel are handled by the EngineBindings, other messages are forwarded
//  * to the EngineBindingsDelegate.
//  *
//  * @see main.dart for what messages are getting sent from Flutter.
//  */
// class EngineBindings(activity: Activity, delegate: EngineBindingsDelegate, entrypoint: String) : MethodCallHandler, StreamHandler {
//     val channel: MethodChannel
//     val engine1: FlutterEngine
//     val delegate: EngineBindingsDelegate
//     val app : App
//     val activity : Activity

//     init {
//         this.activity = activity
//         app = activity.applicationContext as App
//         // This has to be lazy to avoid creation before the FlutterEngineGroup.
//         val dartEntrypoint =
//             DartExecutor.DartEntrypoint(
//                 FlutterInjector.instance().flutterLoader().findAppBundlePath(), entrypoint
//             )
//         engine1 = app.engines.createAndRunEngine(activity, dartEntrypoint)
//         this.delegate = delegate
//         channel = MethodChannel(engine1.dartExecutor.binaryMessenger, "multiple-flutters")
//     }

//     /**
//      * This setups the messaging connections on the platform channel and the DataModel.
//      */
//     fun attach() {
//         channel.setMethodCallHandler { call, result ->
//             when (call.method) {
//                 "spawn" -> {                   
//                     val dartEntrypoint2 = DartExecutor.DartEntrypoint(
//                         FlutterInjector.instance().flutterLoader().findAppBundlePath(), "do_some_stuff"
//                     )
//                     val start = System.currentTimeMillis();
//                     val engine2 = app.engines.createAndRunEngine(activity, dartEntrypoint2)
//                     val end = System.currentTimeMillis();
//                     val controlChannel = MethodChannel(engine2.getDartExecutor().getBinaryMessenger(), "foo" + "/control");
//                     val startupChannel = EventChannel(engine2.getDartExecutor().getBinaryMessenger(), "foo" + "/event");
//                     startupChannel.setStreamHandler(this);
//                     controlChannel.setMethodCallHandler(this);
//                     result.success(null)
//                 }
//                 else -> {
//                     result.notImplemented()
//                 }
//             }
//         }
//     }

//     override fun onListen(o:Any, sink:EventChannel.EventSink) {
//         android.util.Log.e("FlutterIsolate", "LISTEN!!!");
//         // if (queuedIsolates.size() == 0)
//         //     return;

//         // IsolateHolder isolate = queuedIsolates.remove();
//         // sink.success(isolate.isolateId);
//         // sink.endOfStream();
//         // activeIsolates.put(isolate.isolateId, isolate);

//         // isolate.result.success(null);
//         // isolate.startupChannel = null;
//         // isolate.result = null;

//         // if (queuedIsolates.size() != 0)
//         //     startNextIsolate();
//     }

//     override fun onMethodCall(call:MethodCall, result:MethodChannel.Result) {
//             android.util.Log.e("FlutterIsolate", "MEHOD CALL");
//     }

//     override fun onCancel(o:Any) {
//         android.util.Log.e("FlutterIsolate", "CANCEL");

//     }

//     /**
//      * This tears down the messaging connections on the platform channel and the DataModel.
//      */
//     fun detach() {
//         // engine.destroy();
//     }

// }