import requests
from bs4 import BeautifulSoup
import json
import datetime
from flask import Flask
from flask import render_template
import unittest

app = Flask(__name__)


@app.route('/')
class TestStringMethods(unittest.TestCase):

    def get_news(self):
        url = "https://yandex.com/news/rubric/politics?from=index"
        today = str(datetime.date.today())

        yandex_politics_request = requests.get(url)

        if yandex_politics_request.status_code == 200:
            print('Yay! We performed a successfull request')
        else:
            print('Oops! Something went wrong...')

        parsed_page = BeautifulSoup(yandex_politics_request.text, 'html.parser')

        articles = []
        for title_tag in parsed_page.find_all('h2'):
            articles.append({"tittle": title_tag.text})

        json_articles = json.dumps({
            "url": "https://yandex.com/news/rubric/politics?from=index",
            "creationDate": today,
            "articles": articles
        }, ensure_ascii=False)

        with open("articles.json", "w", encoding="utf-8") as file:
            file.write(json_articles)

        return render_template('news_page.html', url=url, date=today, articles=articles)


if __name__ == '__main__':
    app.run()
    unittest.main()
