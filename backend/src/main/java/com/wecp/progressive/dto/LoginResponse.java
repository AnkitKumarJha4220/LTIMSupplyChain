package com.wecp.progressive.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
   private String token;
   private String roles;
   private Integer userId;

   @JsonCreator
   public LoginResponse(@JsonProperty("token") String token , String roles, Integer userId) {
      this.token = token;
      this.roles=roles;
      this.userId=userId;
   }


   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }
   public String getRoles()
   {
      return this.roles;
   }

   public void setRole(String roles)
   {
      this.roles = roles;
   }
   public void setUserId(Integer userId)
   {
      this.userId=userId;
   }

   public long getUserId()
   {
      return this.userId;
   }
}
