package com.example.assertion.service;

public class FooService {

  public void doStuff(Boolean flag) {
    try{
      if(flag){
        // do stuff
      }
    }catch (Exception e){
      throw new RuntimeException("Unexpected error occurred", e);
    }
  }

}
