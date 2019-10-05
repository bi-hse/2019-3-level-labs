#1 Practice 1

import requests
from bs4 import BeautifulSoup

url = 'https://tproger.ru'

tproger_request = requests.get(url)
# print(tproger_request.text)

tproger_content = tproger_request.text
parsed_page = BeautifulSoup(tproger_content, features="html.parser")
print(parsed_page.find_all('h2'))
