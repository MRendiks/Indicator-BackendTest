import requests
from bs4 import BeautifulSoup

class _IMDbScraper:
    def __init__(self):
        self._url = "https://www.imdb.com/chart/top"
        self._response = None
        self._soup = None

    def _fetch_page(self):
        self._response = requests.get(self._url)
        if self._response.status_code == 200:
            self._soup = BeautifulSoup(self._response.text, "html.parser")

    def _extract_movie_elements(self):
        return self._soup.find("tbody", class_="lister-list").find_all("tr")

    def _get_movie_details(self, movie_element):
        title = movie_element.find("td", class_="titleColumn").find("a").text
        year = movie_element.find("span", class_="secondaryInfo").text.strip("()")
        rating = movie_element.find("td", class_="ratingColumn").find("strong").text
        return {"Title": title, "Year": year, "Rating": rating}

    def get_top_movies(self):
        self._fetch_page()
        movie_elements = self._extract_movie_elements()
        movies = [self._get_movie_details(movie) for movie in movie_elements]
        return movies


def main():
    scraper = _IMDbScraper()
    top_movies = scraper.get_top_movies()
    for movie in top_movies:
        print("Title:", movie["Title"])
        print("Year:", movie["Year"])
        print("Rating:", movie["Rating"])
        print()


if __name__ == "__main__":
    main()
