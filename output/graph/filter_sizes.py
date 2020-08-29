with open('./splitResults.txt') as results_fs:
    data = list(results_fs.read().rstrip().split('\n'))

with open('./sizes.txt', 'w') as sizes_fs:
    result = [data[1 + i * 2] for i in range((len(data) - 1) // 2)]
    sizes_fs.write('\n'.join(result))

print(sum(map(int, result)))
