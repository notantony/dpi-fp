## 1
import pandas as pd

# df = pd.read_csv('./data/refined/top10.csv')
df = pd.read_csv('./data/refined/top10recursive.csv')
df = df.iloc[range(10),:]
df['Best'] = df[['Recursive', 'Recursive2', 'HeuristicThenRecursive', 'MinRootDist', 'MaxRootDist']].min(axis=1)
print(df)
print('Total rules: {}'.format(df['size'].sum()))
print('Sum of Minimized: {}'.format(df['Minimized'].sum()))
print('Sum of ThompsonModified: {}'.format(df['ThompsonModified'].sum()))
print('Sum of Heuristic: {}'.format(df['Heuristic'].sum()))
print('Sum of HeuristicThenRecursive: {}'.format(df['HeuristicThenRecursive'].sum()))
print('Sum of Recursive: {}'.format(df['Recursive'].sum()))
print('Sum of Sum-of-single: {}'.format(df['Sum_of_single'].sum()))
print('Sum of Best: {}'.format(df['Best'].sum()))

print(df[['Heuristic', 'ChromaticNumberSimple']])

## ds