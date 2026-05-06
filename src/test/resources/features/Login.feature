Feature: Validation of Login Page and sorting products and verify order
  Scenario 1: Login Validations(Positive and Negative)
    Given Open the Website and website and you can see the username,password and login button
    When click the login button without entering any username or password
    And Type "standard_user" in the username field then type wrong password "password123" click login you should see a error message
    And try "locked_out_user" for locked out user and correct password "secret_sauce" then it will show as locked user
    Then enter correct "standard_user" and "secret_sauce" then login

    Scenario 2: Sort Products & Verify Order
      Given To Navigate to Sauce Demo Username "standard_user" and Password "secret_sauce" and click on Name(A-Z) dropdown for sorting options
      When Name(A-Z) is clicked select price (Low to High) and click that so items will be rearranged
      And collect all the price mentioned into a list
      And Check for the prices in the list whether they are in ascending order lower then then the next one otherwise the test is failed
      Then click sorting filter Name(Z to A ) and verify they are in reverse alphabetical order




