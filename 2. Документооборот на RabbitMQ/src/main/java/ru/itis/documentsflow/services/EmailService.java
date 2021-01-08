package ru.itis.documentsflow.services;

import java.util.Properties;

public interface EmailService {
    void sendInquiry(String filename, Properties properties);
}
