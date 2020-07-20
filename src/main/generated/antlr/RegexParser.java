// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BYTESYMBOL=1, DIGIT=2, LETTER=3, SYMBOL=4, HYPHEN=5, QUESTIONMARK=6, L_PAR=7, 
		R_PAR=8, L_SQBRACKET=9, R_SQBRACKET=10, ESCAPED_CHAR=11, SLASH=12, METACHAR=13, 
		OR=14, CAP=15, STAR=16, PLUS=17, DOLLAR=18, L_BRACE=19, R_BRACE=20, COMMA=21;
	public static final int
		RULE_start = 0, RULE_params = 1, RULE_charset = 2, RULE_charsetRange = 3, 
		RULE_charsetValues = 4, RULE_expr = 5, RULE_expr1 = 6, RULE_pureExpr = 7, 
		RULE_character = 8, RULE_special = 9, RULE_repeatedExpr = 10, RULE_number = 11, 
		RULE_repeatCounter = 12, RULE_optionalExpr = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "params", "charset", "charsetRange", "charsetValues", "expr", 
			"expr1", "pureExpr", "character", "special", "repeatedExpr", "number", 
			"repeatCounter", "optionalExpr"
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

	@Override
	public String getGrammarFileName() { return "Regex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RegexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public List<TerminalNode> SLASH() { return getTokens(RegexParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(RegexParser.SLASH, i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(SLASH);
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(29);
					expr();
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(35);
			match(SLASH);
			setState(36);
			params();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<TerminalNode> LETTER() { return getTokens(RegexParser.LETTER); }
		public TerminalNode LETTER(int i) {
			return getToken(RegexParser.LETTER, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LETTER) {
				{
				{
				setState(38);
				match(LETTER);
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharsetContext extends ParserRuleContext {
		public TerminalNode L_SQBRACKET() { return getToken(RegexParser.L_SQBRACKET, 0); }
		public TerminalNode R_SQBRACKET() { return getToken(RegexParser.R_SQBRACKET, 0); }
		public TerminalNode CAP() { return getToken(RegexParser.CAP, 0); }
		public List<CharsetValuesContext> charsetValues() {
			return getRuleContexts(CharsetValuesContext.class);
		}
		public CharsetValuesContext charsetValues(int i) {
			return getRuleContext(CharsetValuesContext.class,i);
		}
		public CharsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterCharset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitCharset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitCharset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharsetContext charset() throws RecognitionException {
		CharsetContext _localctx = new CharsetContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_charset);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(L_SQBRACKET);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CAP) {
				{
				setState(45);
				match(CAP);
				}
			}

			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				charsetValues();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << ESCAPED_CHAR) | (1L << SLASH) | (1L << METACHAR) | (1L << PLUS) | (1L << COMMA))) != 0) );
			setState(53);
			match(R_SQBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharsetRangeContext extends ParserRuleContext {
		public TerminalNode HYPHEN() { return getToken(RegexParser.HYPHEN, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(RegexParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(RegexParser.DIGIT, i);
		}
		public List<TerminalNode> LETTER() { return getTokens(RegexParser.LETTER); }
		public TerminalNode LETTER(int i) {
			return getToken(RegexParser.LETTER, i);
		}
		public List<TerminalNode> BYTESYMBOL() { return getTokens(RegexParser.BYTESYMBOL); }
		public TerminalNode BYTESYMBOL(int i) {
			return getToken(RegexParser.BYTESYMBOL, i);
		}
		public CharsetRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charsetRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterCharsetRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitCharsetRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitCharsetRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharsetRangeContext charsetRange() throws RecognitionException {
		CharsetRangeContext _localctx = new CharsetRangeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_charsetRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(56);
			match(HYPHEN);
			setState(57);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharsetValuesContext extends ParserRuleContext {
		public CharsetRangeContext charsetRange() {
			return getRuleContext(CharsetRangeContext.class,0);
		}
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(RegexParser.PLUS, 0); }
		public CharsetValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charsetValues; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterCharsetValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitCharsetValues(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitCharsetValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharsetValuesContext charsetValues() throws RecognitionException {
		CharsetValuesContext _localctx = new CharsetValuesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_charsetValues);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				charsetRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				character();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(PLUS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(RegexParser.OR, 0); }
		public List<Expr1Context> expr1() {
			return getRuleContexts(Expr1Context.class);
		}
		public Expr1Context expr1(int i) {
			return getRuleContext(Expr1Context.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expr);
		int _la;
		try {
			int _alt;
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(64);
					expr1();
					}
					}
					setState(67); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << L_PAR) | (1L << L_SQBRACKET) | (1L << ESCAPED_CHAR) | (1L << SLASH) | (1L << METACHAR) | (1L << CAP) | (1L << DOLLAR) | (1L << COMMA))) != 0) );
				setState(69);
				match(OR);
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(70);
						expr();
						}
						} 
					}
					setState(75);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				expr1();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr1Context extends ParserRuleContext {
		public OptionalExprContext optionalExpr() {
			return getRuleContext(OptionalExprContext.class,0);
		}
		public RepeatedExprContext repeatedExpr() {
			return getRuleContext(RepeatedExprContext.class,0);
		}
		public PureExprContext pureExpr() {
			return getRuleContext(PureExprContext.class,0);
		}
		public Expr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitExpr1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitExpr1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr1Context expr1() throws RecognitionException {
		Expr1Context _localctx = new Expr1Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr1);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				optionalExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				repeatedExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				pureExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PureExprContext extends ParserRuleContext {
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public TerminalNode L_PAR() { return getToken(RegexParser.L_PAR, 0); }
		public TerminalNode R_PAR() { return getToken(RegexParser.R_PAR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CharsetContext charset() {
			return getRuleContext(CharsetContext.class,0);
		}
		public SpecialContext special() {
			return getRuleContext(SpecialContext.class,0);
		}
		public PureExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pureExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterPureExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitPureExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitPureExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PureExprContext pureExpr() throws RecognitionException {
		PureExprContext _localctx = new PureExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_pureExpr);
		int _la;
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTESYMBOL:
			case DIGIT:
			case LETTER:
			case SYMBOL:
			case HYPHEN:
			case QUESTIONMARK:
			case ESCAPED_CHAR:
			case SLASH:
			case METACHAR:
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				character();
				}
				break;
			case L_PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(L_PAR);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << L_PAR) | (1L << L_SQBRACKET) | (1L << ESCAPED_CHAR) | (1L << SLASH) | (1L << METACHAR) | (1L << CAP) | (1L << DOLLAR) | (1L << COMMA))) != 0)) {
					{
					{
					setState(86);
					expr();
					}
					}
					setState(91);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(92);
				match(R_PAR);
				}
				break;
			case L_SQBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				charset();
				}
				break;
			case CAP:
			case DOLLAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				special();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharacterContext extends ParserRuleContext {
		public TerminalNode BYTESYMBOL() { return getToken(RegexParser.BYTESYMBOL, 0); }
		public TerminalNode LETTER() { return getToken(RegexParser.LETTER, 0); }
		public TerminalNode SYMBOL() { return getToken(RegexParser.SYMBOL, 0); }
		public TerminalNode DIGIT() { return getToken(RegexParser.DIGIT, 0); }
		public TerminalNode ESCAPED_CHAR() { return getToken(RegexParser.ESCAPED_CHAR, 0); }
		public TerminalNode METACHAR() { return getToken(RegexParser.METACHAR, 0); }
		public TerminalNode HYPHEN() { return getToken(RegexParser.HYPHEN, 0); }
		public TerminalNode COMMA() { return getToken(RegexParser.COMMA, 0); }
		public TerminalNode QUESTIONMARK() { return getToken(RegexParser.QUESTIONMARK, 0); }
		public TerminalNode SLASH() { return getToken(RegexParser.SLASH, 0); }
		public CharacterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterCharacter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitCharacter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitCharacter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharacterContext character() throws RecognitionException {
		CharacterContext _localctx = new CharacterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_character);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << ESCAPED_CHAR) | (1L << SLASH) | (1L << METACHAR) | (1L << COMMA))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpecialContext extends ParserRuleContext {
		public TerminalNode DOLLAR() { return getToken(RegexParser.DOLLAR, 0); }
		public TerminalNode CAP() { return getToken(RegexParser.CAP, 0); }
		public SpecialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_special; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterSpecial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitSpecial(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitSpecial(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecialContext special() throws RecognitionException {
		SpecialContext _localctx = new SpecialContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_special);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_la = _input.LA(1);
			if ( !(_la==CAP || _la==DOLLAR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatedExprContext extends ParserRuleContext {
		public PureExprContext pureExpr() {
			return getRuleContext(PureExprContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(RegexParser.PLUS, 0); }
		public TerminalNode QUESTIONMARK() { return getToken(RegexParser.QUESTIONMARK, 0); }
		public TerminalNode STAR() { return getToken(RegexParser.STAR, 0); }
		public RepeatCounterContext repeatCounter() {
			return getRuleContext(RepeatCounterContext.class,0);
		}
		public RepeatedExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatedExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRepeatedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRepeatedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitRepeatedExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatedExprContext repeatedExpr() throws RecognitionException {
		RepeatedExprContext _localctx = new RepeatedExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_repeatedExpr);
		try {
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				pureExpr();
				setState(102);
				match(PLUS);
				setState(104);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(103);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				pureExpr();
				setState(107);
				match(STAR);
				setState(109);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(108);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				pureExpr();
				setState(112);
				repeatCounter();
				setState(114);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(113);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(RegexParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(RegexParser.DIGIT, i);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				match(DIGIT);
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatCounterContext extends ParserRuleContext {
		public RepeatCounterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatCounter; }
	 
		public RepeatCounterContext() { }
		public void copyFrom(RepeatCounterContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExactCounterContext extends RepeatCounterContext {
		public TerminalNode L_BRACE() { return getToken(RegexParser.L_BRACE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode R_BRACE() { return getToken(RegexParser.R_BRACE, 0); }
		public ExactCounterContext(RepeatCounterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterExactCounter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitExactCounter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitExactCounter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RBorderCounterContext extends RepeatCounterContext {
		public TerminalNode L_BRACE() { return getToken(RegexParser.L_BRACE, 0); }
		public TerminalNode COMMA() { return getToken(RegexParser.COMMA, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode R_BRACE() { return getToken(RegexParser.R_BRACE, 0); }
		public RBorderCounterContext(RepeatCounterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRBorderCounter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRBorderCounter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitRBorderCounter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LBorderCounterContext extends RepeatCounterContext {
		public TerminalNode L_BRACE() { return getToken(RegexParser.L_BRACE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(RegexParser.COMMA, 0); }
		public TerminalNode R_BRACE() { return getToken(RegexParser.R_BRACE, 0); }
		public LBorderCounterContext(RepeatCounterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterLBorderCounter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitLBorderCounter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitLBorderCounter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RangeCounterContext extends RepeatCounterContext {
		public TerminalNode L_BRACE() { return getToken(RegexParser.L_BRACE, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(RegexParser.COMMA, 0); }
		public TerminalNode R_BRACE() { return getToken(RegexParser.R_BRACE, 0); }
		public RangeCounterContext(RepeatCounterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterRangeCounter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitRangeCounter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitRangeCounter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatCounterContext repeatCounter() throws RecognitionException {
		RepeatCounterContext _localctx = new RepeatCounterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_repeatCounter);
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new RangeCounterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				match(L_BRACE);
				setState(124);
				number();
				setState(125);
				match(COMMA);
				setState(126);
				number();
				setState(127);
				match(R_BRACE);
				}
				break;
			case 2:
				_localctx = new LBorderCounterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(L_BRACE);
				setState(130);
				number();
				setState(131);
				match(COMMA);
				setState(132);
				match(R_BRACE);
				}
				break;
			case 3:
				_localctx = new RBorderCounterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(L_BRACE);
				setState(135);
				match(COMMA);
				setState(136);
				number();
				setState(137);
				match(R_BRACE);
				}
				break;
			case 4:
				_localctx = new ExactCounterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				match(L_BRACE);
				setState(140);
				number();
				setState(141);
				match(R_BRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalExprContext extends ParserRuleContext {
		public PureExprContext pureExpr() {
			return getRuleContext(PureExprContext.class,0);
		}
		public List<TerminalNode> QUESTIONMARK() { return getTokens(RegexParser.QUESTIONMARK); }
		public TerminalNode QUESTIONMARK(int i) {
			return getToken(RegexParser.QUESTIONMARK, i);
		}
		public OptionalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).enterOptionalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RegexListener ) ((RegexListener)listener).exitOptionalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RegexVisitor ) return ((RegexVisitor<? extends T>)visitor).visitOptionalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalExprContext optionalExpr() throws RecognitionException {
		OptionalExprContext _localctx = new OptionalExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_optionalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			pureExpr();
			setState(146);
			match(QUESTIONMARK);
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(147);
				match(QUESTIONMARK);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u0099\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\7\2!\n\2\f\2\16\2$\13"+
		"\2\3\2\3\2\3\2\3\3\7\3*\n\3\f\3\16\3-\13\3\3\4\3\4\5\4\61\n\4\3\4\6\4"+
		"\64\n\4\r\4\16\4\65\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6A\n\6\3\7\6"+
		"\7D\n\7\r\7\16\7E\3\7\3\7\7\7J\n\7\f\7\16\7M\13\7\3\7\5\7P\n\7\3\b\3\b"+
		"\3\b\5\bU\n\b\3\t\3\t\3\t\7\tZ\n\t\f\t\16\t]\13\t\3\t\3\t\3\t\5\tb\n\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\f\5\fk\n\f\3\f\3\f\3\f\5\fp\n\f\3\f\3\f\3"+
		"\f\5\fu\n\f\5\fw\n\f\3\r\6\rz\n\r\r\r\16\r{\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\5\16\u0092\n\16\3\17\3\17\3\17\5\17\u0097\n\17\3\17\2\2\20\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\2\5\3\2\3\5\5\2\3\b\r\17\27\27\4\2\21\21"+
		"\24\24\2\u00a3\2\36\3\2\2\2\4+\3\2\2\2\6.\3\2\2\2\b9\3\2\2\2\n@\3\2\2"+
		"\2\fO\3\2\2\2\16T\3\2\2\2\20a\3\2\2\2\22c\3\2\2\2\24e\3\2\2\2\26v\3\2"+
		"\2\2\30y\3\2\2\2\32\u0091\3\2\2\2\34\u0093\3\2\2\2\36\"\7\16\2\2\37!\5"+
		"\f\7\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#%\3\2\2\2$\"\3\2\2"+
		"\2%&\7\16\2\2&\'\5\4\3\2\'\3\3\2\2\2(*\7\5\2\2)(\3\2\2\2*-\3\2\2\2+)\3"+
		"\2\2\2+,\3\2\2\2,\5\3\2\2\2-+\3\2\2\2.\60\7\13\2\2/\61\7\21\2\2\60/\3"+
		"\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\64\5\n\6\2\63\62\3\2\2\2\64\65\3"+
		"\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\7\f\2\28\7\3\2\2"+
		"\29:\t\2\2\2:;\7\7\2\2;<\t\2\2\2<\t\3\2\2\2=A\5\b\5\2>A\5\22\n\2?A\7\23"+
		"\2\2@=\3\2\2\2@>\3\2\2\2@?\3\2\2\2A\13\3\2\2\2BD\5\16\b\2CB\3\2\2\2DE"+
		"\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GK\7\20\2\2HJ\5\f\7\2IH\3\2\2\2"+
		"JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LP\3\2\2\2MK\3\2\2\2NP\5\16\b\2OC\3\2\2"+
		"\2ON\3\2\2\2P\r\3\2\2\2QU\5\34\17\2RU\5\26\f\2SU\5\20\t\2TQ\3\2\2\2TR"+
		"\3\2\2\2TS\3\2\2\2U\17\3\2\2\2Vb\5\22\n\2W[\7\t\2\2XZ\5\f\7\2YX\3\2\2"+
		"\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^b\7\n\2\2_b\5\6"+
		"\4\2`b\5\24\13\2aV\3\2\2\2aW\3\2\2\2a_\3\2\2\2a`\3\2\2\2b\21\3\2\2\2c"+
		"d\t\3\2\2d\23\3\2\2\2ef\t\4\2\2f\25\3\2\2\2gh\5\20\t\2hj\7\23\2\2ik\7"+
		"\b\2\2ji\3\2\2\2jk\3\2\2\2kw\3\2\2\2lm\5\20\t\2mo\7\22\2\2np\7\b\2\2o"+
		"n\3\2\2\2op\3\2\2\2pw\3\2\2\2qr\5\20\t\2rt\5\32\16\2su\7\b\2\2ts\3\2\2"+
		"\2tu\3\2\2\2uw\3\2\2\2vg\3\2\2\2vl\3\2\2\2vq\3\2\2\2w\27\3\2\2\2xz\7\4"+
		"\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\31\3\2\2\2}~\7\25\2\2~\177"+
		"\5\30\r\2\177\u0080\7\27\2\2\u0080\u0081\5\30\r\2\u0081\u0082\7\26\2\2"+
		"\u0082\u0092\3\2\2\2\u0083\u0084\7\25\2\2\u0084\u0085\5\30\r\2\u0085\u0086"+
		"\7\27\2\2\u0086\u0087\7\26\2\2\u0087\u0092\3\2\2\2\u0088\u0089\7\25\2"+
		"\2\u0089\u008a\7\27\2\2\u008a\u008b\5\30\r\2\u008b\u008c\7\26\2\2\u008c"+
		"\u0092\3\2\2\2\u008d\u008e\7\25\2\2\u008e\u008f\5\30\r\2\u008f\u0090\7"+
		"\26\2\2\u0090\u0092\3\2\2\2\u0091}\3\2\2\2\u0091\u0083\3\2\2\2\u0091\u0088"+
		"\3\2\2\2\u0091\u008d\3\2\2\2\u0092\33\3\2\2\2\u0093\u0094\5\20\t\2\u0094"+
		"\u0096\7\b\2\2\u0095\u0097\7\b\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\35\3\2\2\2\24\"+\60\65@EKOT[ajotv{\u0091\u0096";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}