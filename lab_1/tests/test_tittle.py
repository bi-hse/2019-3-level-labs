from lab_1.news import app, get_response, find_articles
import unittest
import datetime
from bs4 import BeautifulSoup


class TittleTest(unittest.TestCase):
    def setUp(self):
        self.app = app.test_client()

    def test_tittles(self):
        request = get_response("https://yandex.com/news/rubric/politics?from=index")
        self.assertEqual(request.status_code, 200)
        response = self.app.get('/', follow_redirects=True)
        articles = find_articles(BeautifulSoup(response.data, 'html.parser'))
        self.assertNotEqual("", ['articles'][0]['tittle'])
    if __name__ == "__main__":
        unittest.main()
