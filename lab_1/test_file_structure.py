import news
import datetime
import json
import unittest


class StructureTests(unittest.TestCase):
    def test_file_structure(self):
        self.request = news.get_html_page("https://yandex.com/news/rubric/politics?from=index")
        self.page = news.parse_page(self.request)
        self.tittles = news.find_articles(self.page)
        news.publish_report("test_articles.json",  self.tittles)

        with open("test_articles.json", "r", encoding="utf-8") as read_file:
            data = json.load(read_file)

        self.assertEqual("https://yandex.com/news/rubric/politics?from=index", data['url'])  # есть url

        self.assertEqual(str(datetime.date.today()), data['creationDate'])  # есть url

        self.assertNotEqual("", data['articles'][0]['tittle'])  # есть хотя бы один заголовок


if __name__ == '__main__':
    unittest.main()
