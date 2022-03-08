package lesson10;

import pageobject.*;
import baseobjects.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Lesson10 extends BaseTest {

    @Test(dataProvider = "credentialsData")
    public void negativeLoginWithDifferentAccounts(String username, String password) {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login(username, password)
                .assertLoginError();
    }

    @Test
    public void positiveLogin() {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login("standard_user", "secret_sauce");

        pageInit(HomePage.class).assertHeaderExist();
    }

    @Test(invocationCount = 2)
    public void addingAndDeletingProductFromCart() {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login("standard_user", "secret_sauce");

        //Adding, deleting product from cart
        pageInit(HomePage.class).addProductToCart("Sauce Labs Backpack")
                .assertProductAddedToCart("Sauce Labs Backpack")
                .deleteProductFromCart("Sauce Labs Backpack")
                .assertProductDeletedFromCart("Sauce Labs Backpack");

    }

    @Test(dataProvider = "productsData")
    public void addingAndDeletingProductFromCartWithDifferentData(String productName) {

        pageInit(LoginPage.class).open("https://www.saucedemo.com/")
                .login("standard_user", "secret_sauce");

        //Adding, deleting product from cart
        pageInit(HomePage.class).addProductToCart(productName)
                .assertProductAddedToCart(productName)
                .deleteProductFromCart(productName)
                .assertProductDeletedFromCart(productName);

    }




    @DataProvider
    private Object[][] credentialsData() {
        return new Object[][]{
                {"standard_user", "secret_sauce123"},
                {"lockuser", "secret_sauce"},
                {"invalid_user", "secret_sauce"}
        };
    }

    @DataProvider
    private Object[][] productsData() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"}
        };
    }
}
