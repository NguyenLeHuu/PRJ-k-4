/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.product;

/**
 *
 * @author Admin
 */
public class FruitError {
    private String productIDError;
    private String productNameError;
    private String priceError;
    private String quantityError;
    private String categoryIDError;
    private String importDateError;
    private String usingDateError;
    private String messageError;

    public FruitError() {
        this.productIDError = "";
        this.productNameError = "";
        this.priceError = "";
        this.quantityError = "";
        this.categoryIDError = "";
        this.importDateError = "";
        this.usingDateError = "";
        this.messageError = "";
    }

    public FruitError(String productIDError, String productNameError, String priceError, String quantityError, String categoryIDError, String importDateError, String usingDateError,String messageError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.categoryIDError = categoryIDError;
        this.importDateError = importDateError;
        this.usingDateError = usingDateError;
        this.messageError = messageError;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public String getUsingDateError() {
        return usingDateError;
    }

    public void setUsingDateError(String usingDateError) {
        this.usingDateError = usingDateError;
    }
    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.usingDateError = messageError;
    }
    
    
}
