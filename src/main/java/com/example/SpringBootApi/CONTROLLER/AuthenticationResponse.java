package com.example.SpringBootApi.CONTROLLER;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthenticationResponse extends Response{
     String token;
     public AuthenticationResponse(String message,String token){
          super(message);
          this.token=token;
     }
     public AuthenticationResponse(){

     }

     public String getToken() {
          return token;
     }

     public void setToken(String token) {
          this.token = token;
     }
}

