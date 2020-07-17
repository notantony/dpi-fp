from python_tests import tests

with open('tests.txt', "w") as result:
    for i, (regex, string, verdict, *b) in enumerate(tests):
        try:
            if r'(?P' in regex:
                continue
            result.write("#{}\n{}\n{}\n{}\n".format(i, '/' + repr(regex)[1:-1] + '/', repr(string)[1:-1], verdict))
        except UnicodeEncodeError as e:
            print(e, string)
