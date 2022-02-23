Feature: Automate the following scenario using Java - Selenium web driver framework
  I want to search cars based on make and model and select warranty

@Assignment
  Scenario: Search cars based on make and model and select warranty
Given Navigate to "https://shop.canadadrives.ca/cars/bc"
When Select "Ontario" Province
And Filter "Ram 1500" vehicles using MakeModel filter
And Sort by Price "Low to High"
And Favourite 3 RAM 1500 vehicles
And Pick an available RAM 1500 vehicle
And Click on Get Started
And In Calculate delivery, Enter Toronto Address
And Select "48 Months" warranty