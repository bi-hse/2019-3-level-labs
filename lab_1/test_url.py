import news
import unittest


class UrlTests(unittest.TestCase):
    def test_url(self):
        request = news.get_html_page("https://yandex.com/news/rubric/politics?from=index")
        self.assertEqual(request.status_code, 200)


if __name__ == "__main__":
    unittest.main()
