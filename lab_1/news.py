import requests
from bs4 import BeautifulSoup
import json
import datetime
from flask import Flask
from flask import render_template


async def get_html_page(url):
    yandex_politics_request = requests.get(url)

    return BeautifulSoup(yandex_politics_request.text, 'html.parser')


async def find_articles(html_page):
    articles = []
    for title_tag in html_page.find_all('h2'):
        articles.append({"tittle": title_tag.text})

    return articles


async def publish_report(articles):
    today = str(datetime.date.today())
    url = "https://yandex.com/news/rubric/politics?from=index"

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
    today = str(datetime.date.today())
    url = "https://yandex.com/news/rubric/politics?from=index"

    html_page = get_html_page(url)
    articles = find_articles(html_page)
    publish_report(articles)

    return render_template('news_page.html', url=url, date=today, articles=articles)


if __name__ == '__main__':
    app.run()
