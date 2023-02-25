package com.example.SpringBootApi.CONTROLLER;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Response {
     String message;
     public Response(String message){
          this.message=message;
     }
     public Response(){

     }

     public String getMessage() {
          return message;
     }

     public void setMessage(String message) {
          this.message = message;
     }
}
