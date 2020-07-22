with open('./rules.txt') as rules:
    rules_list = rules.read().rstrip().split('\n')
    rules_list = set(rules_list)
    with open('./unique.txt', 'w') as unique:
        unique.write("\n".join(list(rules_list)))
