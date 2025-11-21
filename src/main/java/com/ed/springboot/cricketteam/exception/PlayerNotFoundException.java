package com.ed.springboot.cricketteam.exception;

//custom exception class
public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String message){
        super(message);
    }
}
