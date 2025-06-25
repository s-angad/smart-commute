package com.example.smart_commute_fix; 

import androidx.annotation.NonNull; 
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {

    private static final String CHANNEL = "com.example.smart_commute_fix/route";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) { 
        super.configureFlutterEngine(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler((call, result) -> {
                    if (call.method.equals("computeRoute")) {
                        String source = call.argument("source");
                        String destination = call.argument("destination");

                        if (source == null || destination == null) {
                            result.error("INVALID_ARGUMENTS", "Source or destination cannot be null", null);
                            return;
                        }
                        try {
                            String route = RouteLoader.computeRoute(source, destination);
                            result.success(route);
                        } catch (Exception e) {
                            result.error("ROUTE_COMPUTATION_FAILED", e.getMessage(), e.toString());
                        }

                    } else {
                        result.notImplemented();
                    }
                });
    }
}
