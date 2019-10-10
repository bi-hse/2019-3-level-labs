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
    names = "Хохлова и Мурадимова"

    json_articles = json.dumps({
        "url": url,
        "creationDate": today,
        "authors": names,
        "articles": articles
    }, ensure_ascii=False)

    with open(path, "w", encoding="utf-8") as file:
        file.write(json_articles)


app = Flask(__name__)


@app.route('/')
def get_news():
    response = get_response("https://yandex.com/news/rubric/politics?from=index")
    parsed_page = parse_page(response)
    articles = find_articles(parsed_page)
    publish_report("articles.json", articles)

    with open("articles.json", 'r', encoding='utf-8') as file:
        json_file = json.load(file)
        json_url = json_file['url']
        json_today = json_file['creationDate']
        json_articles = json_file['articles']
        json_names = json_file['authors']

    return render_template('news_page.html', url=json_url, date=json_today, articles=json_articles, names=json_names)


@app.route('/saved_news/')
def get_saved_news():
    return render_template('Yandex.News.html')


if __name__ == '__main__':
    app.run()

