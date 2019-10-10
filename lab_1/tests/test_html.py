from lab_1.news import app
import unittest
from bs4 import BeautifulSoup


class HtmlTest(unittest.TestCase):
    def setUp(self):
        self.app = app.test_client()

    def test_tittles(self):
        response = self.app.get('/', follow_redirects=True)
        html_page=(BeautifulSoup(response.data, 'html.parser'))
        articles = []
        for title_tag in html_page.find_all('li'):
            articles.append(title_tag.text)
        self.assertNotEqual(0, len(articles))


if __name__ == "__main__":
    unittest.main()
