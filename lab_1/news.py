import requests
from bs4 import BeautifulSoup
import json
import datetime
from flask import Flask
from flask import render_template


def get_response(url):
    return requests.get(url)  # возвращаем запроc { "statusCode": int, "text": html-code, ... }


def parse_page(request):
    return BeautifulSoup(request.text, 'html.parser')  # возвращаем отпарсированную страницу


def find_articles(html_page):
    articles = []
    for title_tag in html_page.find_all('h2'):
        articles.append({"tittle": title_tag.text})

    return articles


def publish_report(path, articles):
    today = str(datetime.date.today())
    url = "https://yandex.com/news/rubric/politics?from=index"

    json_articles = json.dumps({
        "url": url,
        "creationDate": today,
        "articles": articles
    }, ensure_ascii=False)

    with open(path, "w", encoding="utf-8") as file:
        file.write(json_articles)


app = Flask(__name__)


@app.route('/')
def get_news():
    today = str(datetime.date.today())
    url = "https://yandex.com/news/rubric/politics?from=index"

    response = get_response(url)
    parsed_page = parse_page(response)
    articles = find_articles(parsed_page)
    publish_report("articles.json", articles)

    return render_template('news_page.html', url=url, date=today, articles=articles)


@app.route('/saved_news/')
def get_saved_news():
    return render_template('Yandex.News.html')


if __name__ == '__main__':
    app.run()

