package com.cse.warana.dto;

import java.io.Serializable;

/**
 * Created by Sashika
 * on Jan 23 0023, 2015.
 */
public class ChangePasswordDTO implements Serializable {

    private static final long serialVersionUID = -3946541239985997668L;

    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
