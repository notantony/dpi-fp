import re
from python_tests import tests


HEADER = """package generated.python;
import templates.RegexTest;

public class PythonTestset {
    public static RegexTest[] allTests = {
"""

END = """    };
}
"""

TEST_TEMPLATE = """        new PythonTest("{}", "{}", "{}", {}),
"""

banned = [
    (r'"(?:\\"|[^"])*?"', r'"\""'),
    (r'\t\n\v\r\f\a', '\t\n\v\r\f\a'),
    ('\t\n\v\r\f\a', '\t\n\v\r\f\a'),
    (r'\x00f', '\017')
]

banned_words = [
    '\\b', '\\B',
    '(?P', '(?!',
    '(?=', '(?<',
    '(?#', '(?:'
]


with open('./../src/test/java/generated/python/PythonTestset.java', "w") as result:
    result.write(HEADER)
    for i, (regex, string, verdict, *b) in enumerate(tests):
        try:
            num_backref_re = re.compile(r'\\\d+')
            if num_backref_re.search(regex) is not None:
                continue

            found = False
            for word in banned_words:
                if word in regex:
                    found = True
                    break
            if found:
                continue

            if (regex, string) in banned:
                continue

            mode = ""

            rule = repr(regex)[1:-1]
            if rule.startswith('(?i)'):
                rule = rule[4:]
                mode = "i"

            rule = '/' + rule + '/' + mode

#            hex_re = re.compile(r'\\x[0-9a-hA-H]{2}?')
#            matches = hex_re.findall(regex)
#            for match in matches:
#                regex.replace

            result.write('        new RegexTest("#{}", "{}", "{}", {}),\n'.format(i, rule, repr(string)[1:-1], verdict))
        except UnicodeEncodeError as e:
            print(e, string)

    result.write(END)