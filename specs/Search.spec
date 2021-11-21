Search Feature
==========

Search with exact match product name
---------------------------
* Given I am on Home Page
* When I search with keyword "Double A Copier Paper A4 80 gsm. 5 Reams/Pack"
* Then I should see correct search result


Search with brand name
---------------------------
* Given I am on Home Page
* When I search with keyword "Double A"
* Then I should see correct search result


Search with partial match
---------------------------
* Given I am on Home Page
* When I search with keyword "Paper"
* Then I should see correct search result
