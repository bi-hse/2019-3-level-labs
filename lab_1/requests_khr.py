import requests
from bs4 import BeautifulSoup
import json
import datetime
from flask import Flask
from flask import render_template


def parse_page(url):
    yandex_politics_request = requests.get(url)

    if yandex_politics_request.status_code == 200:
        print('Yay! We performed a successfull request')
    else:
        print('Oops! Something went wrong...')

    return BeautifulSoup(yandex_politics_request.text, 'html.parser')


def get_tittles(parsed_page):
    articles = []
    for title_tag in parsed_page.find_all('h2'):
        articles.append({"tittle": title_tag.text})

    return articles


def record_json(url, today, articles):
    json_articles = json.dumps({
        "url": url,
        "creationDate": today,
        "articles": articles
    }, ensure_ascii=False)

    with open("articles.json", "w", encoding="utf-8") as file:
        file.write(json_articles)


app = Flask(__name__)


@app.route('/')
def get_news():
    url = "https://yandex.com/news/rubric/politics?from=index"
    today = str(datetime.date.today())

    page = parse_page(url)
    tittles = get_tittles(page)
    record_json(url, today, tittles)

    return render_template('news_page.html', url=url, date=today, articles=tittles)


if __name__ == '__main__':
    app.run()
