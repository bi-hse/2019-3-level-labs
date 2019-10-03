import requests
from bs4 import BeautifulSoup
import json
import codecs
import datetime
import http.server
import socketserver

class Finder(object):

    def __init__(self,url):
        self.url = url
    def url_stuff(self):
        resp = requests.get(self.url)
        return resp
    def parcer(self, resp):
        topics = {}
        now = datetime.datetime.now()
        topics["url"] = [self.url]
        topics["creationDate"] = [str(now)]
        topics["articles"] = []
        resp = requests.get(self.url)
        if resp.status_code == 200:
            soup3 = BeautifulSoup(resp.text, 'html.parser')
            l3 = soup3.find("div", {"class": "lent-left"})
            for i in l3.findAll("div", "title lent-title"):
                topics["title"].append({"Title": i.text})
        else:
            print("All for now")
        return topics
    def write_json(self,topics):
        with codecs.open("StopGame.json", "w", encoding="utf-8") as outfile:
            json.dump(topics, outfile, indent=4, ensure_ascii=False, separators=(',', ': '))
        outfile.close()
def main():
    PORT = 8080
    Handler = http.server.SimpleHTTPRequestHandler
    with socketserver.TCPServer(("", PORT), Handler) as httpd:
        Parcer = Finder("https://stopgame.ru/news")
        resp3 = Parcer.url_stuff()
        top = Parcer.parcer(resp3)
        Parcer.write_json(top)
        httpd.serve_forever()
if __name__=="__main__":
    main()
