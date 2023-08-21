# Import Library
import pandas as pd
import numpy as np
import csv

# Get Dataset
df = pd.read_csv('Indicator-BackendTest/Test_Tambahan_Backend/dataset/movies.csv', sep=',', encoding='utf-8')
print(df)