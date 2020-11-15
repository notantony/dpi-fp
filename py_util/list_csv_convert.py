import csv
import re


# with open('./data/top10/top10stats.txt', 'r') as list_file:
with open('./data/top10/top10recursive.txt', 'r') as list_file:
    segments = list_file.read().rstrip('\n').split('\n\n')
    

parsed_segments = []
all_names = {'size'}
for segment in segments:
    segment = segment.split('\n')
    segment_dict = {}
    if re.search(r"\d+", segment[0]) is None:
        print("\n===\n".join(segments))
    segment_dict['size'] = re.search(r"\d+", segment[0]).group()
    # print(re.search(r"\d+", segment[0]).group())
    for row in segment[1:]:
        entry = re.search(r".*: ", row)
        name = entry.group()[:-2].replace('-', '_')
        value = row[entry.end():]
        # print(entry.group())
        # print(value)
        # print(row)
        # print('===')
        segment_dict[name] = value
        all_names.add(name)
    parsed_segments.append(segment_dict)


# with open('./data/refined/top10.csv', 'w') as csvfile:
with open('./data/refined/top10recursive.csv', 'w') as csvfile:
    results_writer = csv.writer(csvfile, quoting=csv.QUOTE_MINIMAL)
    index = list(all_names)
    results_writer.writerow(index)    
    for segment_dict in parsed_segments:
        row = [segment_dict[name] for name in index]
        # print(row)
        results_writer.writerow(row)
