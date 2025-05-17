package com.vantarides.dao;

import com.vantarides.model.EmailRequest;

public interface EmailDao {
    void saveMessage(EmailRequest request);
}
