import re

class RegexFilter:
    def __init__(self, cap_start_only=True, default_verdict=True, banned=set()):
        self.default_verdict = default_verdict
        self.counters = {'default': 0}
        self.rules = []

        self._add_filter_rule(lambda regex: regex in banned, 'banned')
        if cap_start_only:
            self._add_filter_rule(lambda regex: not regex.startswith('/^'), 'not_cap_start')
        num_backref_re = re.compile(r'\\\d+')
        self._add_filter_rule(lambda regex: num_backref_re.search(regex) is not None, 'num_backref')
        self._add_filter_rule(lambda regex: r'(?P=' in regex, r'(?P=')  # https://www.regular-expressions.info/refext.html
        self._add_filter_rule(lambda regex: r'(?!' in regex, r'(?!')  # https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions/Assertions
        self._add_filter_rule(lambda regex: r'(?=' in regex, r'(?=')  # https://stackoverflow.com/questions/20998251/question-mark-with-equal-to-sign-in-regex
        self._add_filter_rule(lambda regex: r'\b' in regex, r'\b')  # not implemented


    def _add_filter_rule(self, rule, rulename, verdict=False):
        self.counters[rulename] = 0
        self.rules.append((rule, rulename, verdict))

    def __call__(self, regex):
        for rule, rulename, verdict in self.rules:
            if rule(regex):
                self.counters[rulename] += 1
                return verdict
        self.counters['default'] += 1
        return self.default_verdict


with open('./bans.txt') as bans:
    banned = set(list(bans))

with open('./rules.txt') as rules:
    rules = list(rules)
    rules_filter = RegexFilter(cap_start_only=True, banned=banned)
    result = list(filter(rules_filter, rules))
    print('Verdicts, of {} rules:'.format(len(rules)))
    for rule, count in rules_filter.counters.items():
        print('{} : {}'.format(rule, count))
    with open('./filtered.txt', 'w') as filtered:
        filtered.write(''.join(result))
