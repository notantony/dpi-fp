// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr_generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BYTESYMBOL=1, DIGIT=2, LETTER=3, SYMBOL=4, HYPHEN=5, QUESTIONMARK=6, L_PAR=7, 
		R_PAR=8, L_SQBRACKET=9, R_SQBRACKET=10, ESCAPED_CHAR=11, SLASH=12, METACHAR=13, 
		OR=14, CAP=15, STAR=16, PLUS=17, DOLLAR=18, L_BRACE=19, R_BRACE=20, COMMA=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"HEX", "BYTESYMBOL", "DIGIT", "LETTER", "SYMBOL", "HYPHEN", "QUESTIONMARK", 
			"L_PAR", "R_PAR", "L_SQBRACKET", "R_SQBRACKET", "ESCAPED_CHAR", "SLASH", 
			"METACHAR", "OR", "CAP", "STAR", "PLUS", "DOLLAR", "L_BRACE", "R_BRACE", 
			"COMMA"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'-'", "'?'", "'('", "')'", "'['", "']'", 
			null, "'/'", null, "'|'", "'^'", "'*'", "'+'", "'$'", "'{'", "'}'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BYTESYMBOL", "DIGIT", "LETTER", "SYMBOL", "HYPHEN", "QUESTIONMARK", 
			"L_PAR", "R_PAR", "L_SQBRACKET", "R_SQBRACKET", "ESCAPED_CHAR", "SLASH", 
			"METACHAR", "OR", "CAP", "STAR", "PLUS", "DOLLAR", "L_BRACE", "R_BRACE", 
			"COMMA"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public RegexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Regex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27d\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\5\rL\n\r\3\16\3\16\3\17\3\17\3\17\5\17S\n\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\2\2\30\3\2\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31"+
		"\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26-\27\3\2\b\5\2\62;CHch\3"+
		"\2\62;\4\2C\\c|\n\2\"#%%\')<<>@BBaa\u0080\u0080\13\2##\'\61<A]apprrtt"+
		"vv}\177\f\2CCFFJKUUYYccffjkuuyy\2c\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\3/\3\2\2\2\5\61\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2"+
		"\2\2\r=\3\2\2\2\17?\3\2\2\2\21A\3\2\2\2\23C\3\2\2\2\25E\3\2\2\2\27G\3"+
		"\2\2\2\31I\3\2\2\2\33M\3\2\2\2\35R\3\2\2\2\37T\3\2\2\2!V\3\2\2\2#X\3\2"+
		"\2\2%Z\3\2\2\2\'\\\3\2\2\2)^\3\2\2\2+`\3\2\2\2-b\3\2\2\2/\60\t\2\2\2\60"+
		"\4\3\2\2\2\61\62\7^\2\2\62\63\7z\2\2\63\64\3\2\2\2\64\65\5\3\2\2\65\66"+
		"\5\3\2\2\66\6\3\2\2\2\678\t\3\2\28\b\3\2\2\29:\t\4\2\2:\n\3\2\2\2;<\t"+
		"\5\2\2<\f\3\2\2\2=>\7/\2\2>\16\3\2\2\2?@\7A\2\2@\20\3\2\2\2AB\7*\2\2B"+
		"\22\3\2\2\2CD\7+\2\2D\24\3\2\2\2EF\7]\2\2F\26\3\2\2\2GH\7_\2\2H\30\3\2"+
		"\2\2IK\7^\2\2JL\t\6\2\2KJ\3\2\2\2L\32\3\2\2\2MN\7\61\2\2N\34\3\2\2\2O"+
		"S\7\60\2\2PQ\7^\2\2QS\t\7\2\2RO\3\2\2\2RP\3\2\2\2S\36\3\2\2\2TU\7~\2\2"+
		"U \3\2\2\2VW\7`\2\2W\"\3\2\2\2XY\7,\2\2Y$\3\2\2\2Z[\7-\2\2[&\3\2\2\2\\"+
		"]\7&\2\2](\3\2\2\2^_\7}\2\2_*\3\2\2\2`a\7\177\2\2a,\3\2\2\2bc\7.\2\2c"+
		".\3\2\2\2\5\2KR\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}