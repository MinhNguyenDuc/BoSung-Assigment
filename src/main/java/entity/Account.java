package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Serialize;

import java.lang.annotation.Annotation;

@Entity
public class Account implements Serialize {
    @Id
    private String email;
    private String password;
    private String fullName;
    private String avatarUrl;
    private String activeCode;
    private int status;

    public Account(String email, String password, String fullName, String avatarUrl, int status) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.status = status;
    }

    public Account() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = 1;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Account(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.status = 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", activeCode='" + activeCode + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public boolean zip() {
        return false;
    }

    @Override
    public int compressionLevel() {
        return 0;
    }
}
