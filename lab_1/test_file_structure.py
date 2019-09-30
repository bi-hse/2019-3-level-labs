import news
import datetime
import json
import unittest


class StructureTests(unittest.TestCase):
    def test_file_structure(self):
        self.page = news.get_html_page("https://yandex.com/news/rubric/politics?from=index")
        self.tittles = news.find_articles(self.page)
        news.publish_report(self.tittles)

        with open("articles.json", "r", encoding="utf-8") as read_file:
            data = json.load(read_file)

        self.assertEqual("https://yandex.com/news/rubric/politics?from=index", data['url'])  # есть url

        self.assertEqual(str(datetime.date.today()), data['creationDate'])  # есть url

        self.assertNotEqual("", data['articles'][0]['tittle'])  # есть хотя бы один заголовок


if __name__ == '__main__':
    unittest.main()