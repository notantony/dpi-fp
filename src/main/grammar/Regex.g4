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


start                   : SLASH expr* SLASH params ;
params                  : LETTER* ;

charset                 : L_SQBRACKET CAP? (charsetValues)+ R_SQBRACKET ;
charsetRange            : (DIGIT | LETTER | BYTESYMBOL) HYPHEN (LETTER | DIGIT | BYTESYMBOL);
charsetValues           : charsetRange | character | PLUS | DOLLAR | R_BRACE | L_BRACE | L_PAR | R_PAR ;

expr                    : expr1+ OR expr*
                        | expr1 ;

expr1                   : optionalExpr
                        | repeatedExpr
                        | pureExpr ;

pureExpr                : character
                        | L_PAR expr* R_PAR
                        | charset
                        | special ;

character               : BYTESYMBOL | LETTER | SYMBOL | DIGIT | ESCAPED_CHAR | METACHAR | HYPHEN | COMMA | QUESTIONMARK | SLASH;

special                 : DOLLAR | CAP ;

repeatedExpr            : pureExpr PLUS QUESTIONMARK?
                        | pureExpr STAR QUESTIONMARK?
                        | pureExpr repeatCounter QUESTIONMARK? ;  // TODO: questionmark cases

number                  : DIGIT+ ;
repeatCounter           : L_BRACE number COMMA number R_BRACE   #rangeCounter
                        | L_BRACE number COMMA R_BRACE          #lBorderCounter
                        | L_BRACE COMMA number R_BRACE          #rBorderCounter
                        | L_BRACE number R_BRACE                #exactCounter
                        ;

optionalExpr            : pureExpr QUESTIONMARK QUESTIONMARK? ;
