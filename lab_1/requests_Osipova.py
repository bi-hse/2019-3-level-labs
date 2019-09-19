import requests 
from bs4 import BeautifulSoup

page_url="https://lenta.ru/rubrics/travel/"

lenta_travel_requests = requests.get(page_url)
lenta_travel_content = lenta_travel_requests.text

if lenta_travel_requests.status_code == 200:
    print (lenta_travel_content)
    print ("We performed successful request")
else:
    print("Error")
parsed_page = BeautifulSoup(lenta_travel_content)
print(type(parsed_page))
print(parsed_page.title)