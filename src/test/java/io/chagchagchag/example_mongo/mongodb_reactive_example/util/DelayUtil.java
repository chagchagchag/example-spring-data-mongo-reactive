package io.chagchagchag.example_mongo.mongodb_reactive_example.util;

public class DelayUtil {
  public static void delayMs(long ms){
    try{
      Thread.sleep(ms);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}
