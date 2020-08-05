with open('./ind660.txt') as used_fs:
    used = set(used_fs.read().rstrip().split('\n'))

with open('./filtered.txt') as filtered_fs:
    rules = list(filtered_fs.read().rstrip().split('\n'))

with open('./selected.txt', 'w') as selected_fs:
    selected = [rule for rule in rules if rule not in used]
    selected_fs.write("\n".join(selected))