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
      When logged in scuccesfully find Sauce Labs Backpack and add it to cart
      And click on cart button and click checkout
      And Fill the form with First Name "John" Last Name "Doe" and a Zip Code "12345".Then click continue
      Then check for the total amount and capture it
      Then click finish assert it shows "Thank you for your order!"



