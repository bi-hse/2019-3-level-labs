import news
import datetime
import json
import aiounittest


class StructureTests(aiounittest.AsyncTestCase):
  async def test_file_structure(self):
      page = await news.get_html_page("https://yandex.com/news/rubric/politics?from=index")
      tittles = await news.find_articles(page)
      await news.publish_report(tittles)

      with open("articles.json", "r", encoding="utf-8") as read_file:
          data = json.load(read_file)

      self.assertEqual("https://yandex.com/news/rubric/politics?from=index", data['url'])  # есть url

      self.assertEqual(str(datetime.date.today()), data['creationDate'])  # есть url

      self.assertNotEqual("", data['articles'][0]['tittle'])  # есть хотя бы один заголовок
