package lesson9;

import pageobject.*;
import baseobjects.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Lesson9 extends BaseTest {


    @Test(dataProvider = "credentialsData")
    public void loginWithDifferentAccounts(String username, String password) {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login(username, password);
    }

    @Test
    public void addingAndDeletingProductFromCart() {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login("standard_user", "secret_sauce");

        //Adding, deleting product from cart
        pageInit(HomePage.class).addProductToCart("Sauce Labs Backpack")
                .assertProductAddedToCart("Sauce Labs Backpack")
                .deleteProductFromCart("Sauce Labs Backpack")
                .assertProductDeletedFromCart("Sauce Labs Backpack");

    }

    @Test
    public void productPurchaseVerification() {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login("standard_user", "secret_sauce");

        //Adding, deleting product from cart
        pageInit(HomePage.class).addProductToCart("Sauce Labs Backpack")
                .assertProductAddedToCart("Sauce Labs Backpack")
                .openCart();

        //Got to checkout and fill the form
        pageInit(CartPage.class).goToCheckout();
        pageInit(CheckoutPage.class).fillCheckoutFormAndContinue("test", "test", "test");
        pageInit(CheckoutSecondStepPage.class).assertExistenceOfAProduct("Sauce Labs Backpack")
                .finishPurchase();
        pageInit(CheckoutCompletePage.class).assertTheEndOfPurchase();

    }


    @DataProvider
    private Object[][] credentialsData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"}
        };
    }
}
