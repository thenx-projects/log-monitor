package com.tgpms.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    public String sha;

    private String subject;

    private long expires;

    public String getSha() {
        return sha;
    }

    public void setSha(String tokenSha) {
        this.sha = tokenSha;
    }


    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}