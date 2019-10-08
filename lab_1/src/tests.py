import unittest
import json
import requests
import HTML_Crawler as crawler


class CrawlerTestCase(unittest.TestCase):
    def test_checkJson(self):
        url = 'https://ria.ru/world/'
        html_page = crawler.get_html_page(url)
        articles = crawler.find_articles(html_page)
        print(articles)
        path = './articles.json'
        crawler.publish_report(path, url, articles)
        fh = open('articles.json', 'r')
        data = json.load(fh)
        fh.close()
        self.assertEqual(data['url'] == '', False)
        self.assertEqual(data['articles'] == '', False)
        self.assertEqual(data['articles'][0]['title'] == '', False)

    def test_checkPage(self):
        url = 'https://ria.ru/world/'
        html_page = crawler.get_html_page(url)
        articles = crawler.find_articles(html_page)
        self.assertEqual(articles["articles"][0]['title'] == '', False)

    def test_checkUrl(self):
        url = 'https://ria.ru/world/'
        news_request = requests.get(url)
        self.assertEqual(news_request.status_code == 200, True)


def suite():
    suite = unittest.TestSuite()
    suite.addTest(CrawlerTestCase('test_checkJson'))
    suite.addTest(CrawlerTestCase('test_checkPage'))
    suite.addTest(CrawlerTestCase('test_checkUrl'))
    return suite


if __name__ == '__main__':
    runner = unittest.TextTestRunner()
    runner.run(suite())
