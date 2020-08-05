

with open('plot.txt') as plot_fs:
    a = plot_fs.readline()
    data = []
    while a:
        x = a.rstrip()
        data_lines = [plot_fs.readline().rstrip() for _ in range(7)]
        data_lines = list(map(lambda line: line.split(": ")[1], data_lines))
        data_lines = [x] + [data_lines[i] for i in [0, 2, 5, 6]]
        data.append(data_lines)
        a = plot_fs.readline().rstrip()

with open('formatted.txt', 'w') as formatted_fs:
    for i in range(len(data[0]) - 1):
        for row in data:
            formatted_fs.write("({},{})".format(row[0], row[i + 1]))
        formatted_fs.write('\n')