import csv


with open('./data/top10/top10stats.txt', 'r') as list_file:
    lines = list_file.read().split('\n\n')
    lines


with open('./cloud_results/out.csv', 'w') as csvfile:
    results_writer = csv.writer(csvfile, quoting=csv.QUOTE_MINIMAL)
    index = ['x', 'Minimized', 'ThompsonModifiedHeuristic', 'ThompsonModifiedHeuristic5', 'ThompsonModifiedHeuristic10']
    results_writer.writerow(index)    
    for row in data:
        results_writer.writerow(row)