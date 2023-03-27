
Feature: Postage Price Calculator Tests 
  
  Scenario Outline: Verify Retail Ground Postage Prices for various package sizes 
  Given I navigate to the Postage Price calculator page
  When I fill in the shipping details on the shipping form
  And I fill in the shipping weight of 25 on the weight form
  And I fill in the shipping length of <length>, height of <height> & width of <width> on the package properties form
  Then I verify the price of shipping with <size> boxes is less than 80 on the mail services page  
  
      Examples: 
    | size | length | height | width | 
    | Small | 16 | 12 | 12 | 
    | Medium | 22 | 16 | 15 | 
    | Large | 28 | 15 | 16 | 

