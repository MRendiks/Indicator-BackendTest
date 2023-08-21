# Import Library
import requests
from requests import get
from bs4 import BeautifulSoup
import pandas as pd
import numpy as np
import os

URL_FILE = "Indicator-BackendTest/Scrapping_web/File/"
loop = 1

class _IMDB100Scrapper:
    def __init__(self, url):
        self._imdb_url = url
        self._headers = {"Accept-Language": "en-US, en;q=0.5"}
        self._response = None
        self._movie_soup = None
        self._movie_div = None
        self._movies = None
        self._movie_name = []
        self._movie_years = []
        self._movie_runtime = []
        self._ratings = []
        self._metascores = []
        self._number_votes = []
        self._us_gross = []
    
    def _increment_loop(self):
        global loop
        loop += 1
    
    def _fetch_page(self):
        self._response = requests.get(self._imdb_url, headers=self._headers)
        if self._response.status_code == 200:
            self._movie_soup = BeautifulSoup(self._response.text, "html.parser")
    
    def _extract_movie_details(self):
        self._movie_div = self._movie_soup.find_all('div', class_='lister-item mode-advanced')
        
        for container in self._movie_div:
            # Name
            name = container.h3.a.text
            self._movie_name.append(name)
            # Year
            year = container.h3.find('span', class_='lister-item-year').text
            self._movie_years.append(year)
            # Runtime
            runtime = container.p.find('span', class_='runtime').text if container.p.find('span', class_='runtime').text else '-'
            self._movie_runtime.append(runtime)
            # Rating
            imdb = float(container.strong.text)
            self._ratings.append(imdb)
            # MetaScore
            m_score = container.find('span', class_='metascore').text if container.find('span', class_='metascore') else '-'
            self._metascores.append(m_score)
            # NumberVotes
            nv = container.find_all('span', attrs={'name': 'nv'})
            # filter nv for votes
            vote = nv[0].text
            self._number_votes.append(vote)
            # filter nv for gross
            grosses = nv[1].text if len(nv) > 1 else '-'
            self._us_gross.append(grosses)
    
    def _generate_dataframe(self):
        self._movies = pd.DataFrame({
            'movie_name': self._movie_name,
            'movie_year': self._movie_years,
            'movie_runtime': self._movie_runtime,
            'imdb_ratings': self._ratings,
            'metascore': self._metascores,
            'number_votes': self._number_votes,
            'us_gross_millions': self._us_gross,
            })
        return self._movies
    
    def get_top_movies_csv(self):
        self._fetch_page()
        self._extract_movie_details()
        generate_pdf = self._generate_dataframe()
        
        int_type_extract = ['movie_year', 'movie_runtime']
        int_type_replace = ['number_votes']
        int_type_gross   = ['us_gross_millions']
        for titles in generate_pdf:
            if titles in int_type_extract:
                self._movies[f'{titles}'] = self._movies[f'{titles}'].str.extract('(\d+)').astype(int)
            elif titles in int_type_replace:
                self._movies[f'{titles}'] = self._movies[f'{titles}'].str.replace(',', '').astype(int)
            elif titles in int_type_gross:
                self._movies[f'{titles}'] = self._movies[f'{titles}'].map(lambda x: x.lstrip('$').rstrip('M'))
                self._movies[f'{titles}'] = pd.to_numeric(self._movies[f'{titles}'], errors='coerce')
            
        # generate top 100 movies
        try:
            global loop
            print(loop)
            if os.path.exists(URL_FILE + "top_100_movies.csv"):
                self._movies.to_csv(f'{URL_FILE}top_100_movies{loop}.csv', index=False)
                self._increment_loop()
                return print("CSV file saved successfully.")
            else:
                self._movies.to_csv(f'{URL_FILE}top_100_movies.csv', index=False)
                self._increment_loop()
                return print("CSV file saved successfully.")
        except Exception as e:
            return print("An error occurred while saving the CSV file:", e)

def main():
    urls = [
        "https://www.imdb.com/search/title/?groups=top_100&ref_=adv_prv",
        "https://www.imdb.com/search/title/?groups=top_100&start=51&ref_=adv_nxt"
    ]
    
    for url in urls:    
        scrapper = _IMDB100Scrapper(url)
        scrapper.get_top_movies_csv()

if __name__ == "__main__":
    main()