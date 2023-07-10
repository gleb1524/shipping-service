# shipping-service
### To start the database in the resource folder, run the docker-compose up 
### When the application is launched, the database is warmed up with data from the file src/main/resources/xlsx/file.xlsx
### To get the sales facts for the promo get a request for the ship/analysis endpoint with the JSON request body "chainName" : "name","materialNo": "number","start":"start date","end":"end date"
### To get the facts of sales by days, get a request for the ship/analysis/days endpoint with the JSON request body "priceIds":{"Chain":number}
### For CRUD with a ship/finance endpoint price with a JSON request body "chainName":"Chain""materialNo":"product code number""PricePerUnit":"unit price"
