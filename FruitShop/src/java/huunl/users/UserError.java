/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.users;

/**
 *
 * @author Admin
 */
public class UserError {
    private String userIDError;
    private String fullNameError;
    private String roleIDError;
    private String passwordError;
    private String confirmError;
    private String addressError;
    private String birthdayError;
    private String phoneError;
    private String gmailError;
    private String messageError;

    public UserError() {
        this.userIDError = "";
        this.fullNameError = "";
        this.roleIDError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.addressError = "";
        this.birthdayError = "";
        this.phoneError = "";
        this.gmailError = "";
        this.messageError = "";
    }

    public UserError(String userIDError, String fullNameError, String roleIDError, String passwordError, String confirmError, String addressError, String birthdayError, String phoneError, String gmailError, String messageError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.roleIDError = roleIDError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.addressError = addressError;
        this.birthdayError = birthdayError;
        this.phoneError = phoneError;
        this.gmailError = gmailError;
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(String birthdayError) {
        this.birthdayError = birthdayError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getGmailError() {
        return gmailError;
    }

    public void setGmailError(String gmailError) {
        this.gmailError = gmailError;
    }

    

    
    
}
