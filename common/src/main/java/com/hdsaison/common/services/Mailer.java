package com.hdsaison.common.services;

public interface Mailer {
    public void send(String to, String subject, String body);
}
