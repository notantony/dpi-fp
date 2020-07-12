grammar Regex;


//fragment  ;
fragment HEX            : [0-9a-fA-F] ;

BYTESYMBOL              : '\\x' HEX HEX ;
DIGIT                   : [0-9] ;
LETTER                  : [A-Za-z] ;
SYMBOL                  : [ _=<>!~@#&:%'] ;
HYPHEN                  : '-' ;
QUESTIONMARK            : '?' ;
L_PAR                   : '(' ;
R_PAR                   : ')' ;
L_SQBRACKET             : '[' ;
R_SQBRACKET             : ']' ;
ESCAPED_CHAR            : '\\' ([ntfr./\-?():^',;=<>*&{}|+%!_] | '[' | ']' | '\\') ;
SLASH                   : '/' ;
METACHAR                : '.' | ('\\' [sSdDaAwWhHiI]) ;
OR                      : '|' ;
CAP                     : '^' ;
STAR                    : '*' ;
PLUS                    : '+' ;
DOLLAR                  : '$' ;
L_BRACE                 : '{' ;
R_BRACE                 : '}' ;
COMMA                   : ',' ;


start                   : SLASH CAP? expr+ SLASH params ;
params                  : LETTER* ;

charset                 : L_SQBRACKET CAP? charset_values R_SQBRACKET ;
charset_range           : (DIGIT | LETTER | BYTESYMBOL) HYPHEN (LETTER | DIGIT | BYTESYMBOL);
//charset_values          : (charset_range | SYMBOL | METACHAR | LETTER | ESCAPED_CHAR | DIGIT | BYTESYMBOL | COMMA)+ ;
charset_values          : (charset_range | character)+ ;

expr                    : pure_expr
                        | optional_expr
                        | expr OR expr
                        | repeated_expr ;

pure_expr               : character+
                        | L_PAR expr+ R_PAR
                        | charset ;

character               : BYTESYMBOL | LETTER | SYMBOL | DIGIT | ESCAPED_CHAR | METACHAR | HYPHEN | COMMA | L_BRACE | R_BRACE | R_SQBRACKET | L_SQBRACKET | QUESTIONMARK | DOLLAR;

repeated_expr           : pure_expr PLUS QUESTIONMARK?
                        | pure_expr STAR QUESTIONMARK?
                        | pure_expr repeat_counter QUESTIONMARK? ;  // TODO: questionmark cases

number                  : DIGIT+ ;
repeat_counter          : L_BRACE number COMMA number R_BRACE
                        | L_BRACE number COMMA R_BRACE
                        | L_BRACE COMMA number R_BRACE
                        | L_BRACE number R_BRACE ;

optional_expr           : pure_expr QUESTIONMARK ;
