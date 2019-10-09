from lab_1.news import get_response, parse_page, find_articles, publish_report
import datetime
import json
import unittest


class StructureTests(unittest.TestCase):
    def test_file_structure(self):
        self.request = get_response("https://yandex.com/news/rubric/politics?from=index")
        self.page = parse_page(self.request)
        self.tittles = find_articles(self.page)
        publish_report("lab_1/tests/test_articles.json",  self.tittles)

        with open("lab_1/tests/test_articles.json", "r", encoding="utf-8") as read_file:
            data = json.load(read_file)

        self.assertEqual("https://yandex.com/news/rubric/politics?from=index", data['url'])  # есть url

        self.assertEqual(str(datetime.date.today()), data['creationDate'])

        self.assertNotEqual("", data['articles'][0]['tittle'])  # есть хотя бы один заголовок


if __name__ == '__main__':
    unittest.main()
