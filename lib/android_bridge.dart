import 'package:flutter/services.dart';

class AndroidBridge {
  
  static const MethodChannel _channel = MethodChannel('com.example.smart_commute_fix/route');

  static Future<String> computeRoute(
      String source, String destination) async {
    try {
      final String rawResult = await _channel.invokeMethod('computeRoute', {
        'source': source,
        'destination': destination,
      });

      return rawResult;
    } on PlatformException catch (e) {
      print("Failed to compute route from native code: '${e.message}'.");
      return "Error: ${e.message}";
    } catch (e) {
      print("An unexpected error occurred: $e");
      return "An unexpected error occurred.";
    }
  }
}
