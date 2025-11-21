package com.ed.springboot.cricketteam.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LogDirectoryInitializer {

    @Value("${app.log.path}")
    private String logPath;

    @EventListener(ApplicationReadyEvent.class)
    public void createLogDirectory() {
        File logDir = new File(logPath);
        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (created) {
                System.out.println("Log directory created at: " + logPath);
            } else {
                System.err.println("Failed to create log directory at: " + logPath);
            }
        } else {
            System.out.println("Log directory already exists at: " + logPath);
        }
    }
}
