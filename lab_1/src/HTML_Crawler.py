import requests
from bs4 import BeautifulSoup
from datetime import datetime, date
import moment
import json



def get_html_page(url):
    news_request = requests.get(url)
    if news_request.status_code == 200:
        print('Yay! We performed a successfull request')
    else:
        print('Oops! Something went wrong...')
    content = news_request.text
    return content


def find_articles(html_page):
    parsed_page = BeautifulSoup(html_page)
    articles_titles = []
    for data in parsed_page.findAll('a', {"class": "list-item__title color-font-hover-only"}):
        articles_titles.append(data.text)  # for getting text between the link
    return articles_titles


def publish_report(path, url, articles):
    date0 = str(date(2019, 9, 26))
    articles0 = [{'title': i} for i in articles]
    data = {
                "url": url,
                "creationDate": date0,
                "articles": articles0
            }
    with open(path, "w", encoding="utf-8") as file:
        json.dump(data, file)


url = 'https://ria.ru/world/'
html_page = get_html_page(url)
articles = find_articles(html_page)
print(articles)
path = './articles.json'
publish_report(path, url, articles)


