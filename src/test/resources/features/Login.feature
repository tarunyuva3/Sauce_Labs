Feature: Validation of Login Page and sorting products and verify order

  Scenario: Login Validations(Positive and Negative)
    Given Open the Website and website and you can see the username,password and login button
    When click the login button without entering any username or password
    And Type "standard_user" in the username field then type wrong password "password123" click login you should see a error message
    And try "locked_out_user" for locked out user and correct password "secret_sauce" then it will show as locked user
    Then enter correct "standard_user" and "secret_sauce" then login

    Scenario: Sort Products & Verify Order
      Given To Navigate to Sauce Demo Username "standard_user" and Password "secret_sauce" and click on Name(A-Z) dropdown for sorting options
      When Name(A-Z) is clicked select price (Low to High) and click that so items will be rearranged
      And collect all the price mentioned into a list
      And Check for the prices in the list whether they are in ascending order lower then then the next one otherwise the test is failed
      Then click sorting filter Name(Z to A ) and verify they are in reverse alphabetical order

    Scenario: verification of cart badge
      Given login with correct credentials username"standard_user" and password"secret_sauce"
      When login successfully add 3 items to cart
      And check if the number is 3 on cart image
      And remove one item from the cart
      And check that the badge number shows 2 now
      Then click on cart page and assert the items you added

    Scenario: End to End flow
      Given login with correct credentials username"standard_user" and password"secret_sauce"
      When logged in successfully find Sauce Labs Backpack and add it to cart
      And click on cart button and click checkout
      And Fill the form with First Name "John" Last Name "Doe" and a Zip Code "12345".Then click continue
      Then check for the total amount and capture it
      Then click finish assert it shows "Thank you for your order!"

    Scenario: Product detail page verification
      Given login with correct credentials username"standard_user" and password"secret_sauce"
      When you click on a product name "Bolt T-Shirt" it should take you to its page
      And check whether all details name price and description are visible and also check if its the same product
      Then click add to cart
      Then check the cart badge shows number 1
      Then click Back to products will be redirected to homepage  Assert that the URL and the page heading are correct

      Scenario: Cart Persistence & Remove Items
        Given login with correct credentials username"standard_user" and password"secret_sauce"
        Then Click Add to cart for any 2 different products
        Then Click on one of the products to go to its detail page,then click Back to products to return
        Then Assert that the cart badge still shows 2
        Then go to cart page and click remove for any one of the item
        Then assert the remaining item the one that you did not remove
        And verify if the details like name and price are displayed

      Scenario: Logout & Session Validation
        Given login with correct credentials username"standard_user" and password"secret_sauce"
        Then click on three horizintal lines left side and click logout
        Then assert that you are back on homepage by checking url and Title
        Then in the address bar manually type "https://www.saucedemo.com/inventory.html" and click enter
        Then assert that app automatically redirects you to login page instead of products page


      Scenario: Compare User Load Times
        Given record start time
        When login as "performance_glitch_user" with "secret_sauce"
        Then wait for products page and calculate load time
        And verify load time is under 10 seconds
        Then logout and login as "standard_user" and "secret_sauce"
        And compare both load times for performance difference

      Scenario: Visual Validation and Image Consistency
        Given login as "standard_user" with "secret_sauce"
        Then find all product images and verify they are not broken
        And check that image alt text matches product name
        When click on the image of "Sauce Labs Backpack"
        Then verify detail page title matches "Sauce Labs Backpack"
        And print current page URL and title

#      Scenario: Cross-User Cart Isolation
#        Given login as "standard_user" with "secret_sauce"
#        And add 2 items to the cart
#        When logout from the application
#        Then login as "problem_user" with "secret_sauce"
#        And verify cart is empty
#        When add 1 item to cart and logout
#        Then login as "standard_user" with "secret_sauce"
#        And verify cart is empty



