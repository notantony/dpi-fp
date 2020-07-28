
with open('./numbers.txt') as numbers:
    selected_numbers = list(map(int, numbers.read().rstrip().split(', ')))

with open('./filtered.txt') as filtered:
    rules_list = list(filtered.read().rstrip().split('\n'))

selected_list = [rules_list[i - 1] for i in selected_numbers]

with open('./selected.txt', 'w') as selected:
    selected.write("\n".join(selected_list))