// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr_generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

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
		RULE_charsetValues = 4, RULE_expr = 5, RULE_pureExpr = 6, RULE_character = 7, 
		RULE_repeatedExpr = 8, RULE_number = 9, RULE_repeatCounter = 10, RULE_optionalExpr = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "params", "charset", "charsetRange", "charsetValues", "expr", 
			"pureExpr", "character", "repeatedExpr", "number", "repeatCounter", "optionalExpr"
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
		public TerminalNode CAP() { return getToken(RegexParser.CAP, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(SLASH);
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CAP) {
				{
				setState(25);
				match(CAP);
				}
			}

			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << L_PAR) | (1L << L_SQBRACKET) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << DOLLAR) | (1L << COMMA))) != 0)) {
				{
				{
				setState(28);
				expr(0);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			match(SLASH);
			setState(35);
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
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LETTER) {
				{
				{
				setState(37);
				match(LETTER);
				}
				}
				setState(42);
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
			setState(43);
			match(L_SQBRACKET);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CAP) {
				{
				setState(44);
				match(CAP);
				}
			}

			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				charsetValues();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << PLUS) | (1L << DOLLAR) | (1L << COMMA))) != 0) );
			setState(52);
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
			setState(54);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(55);
			match(HYPHEN);
			setState(56);
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
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				charsetRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				character();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
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
		public PureExprContext pureExpr() {
			return getRuleContext(PureExprContext.class,0);
		}
		public OptionalExprContext optionalExpr() {
			return getRuleContext(OptionalExprContext.class,0);
		}
		public RepeatedExprContext repeatedExpr() {
			return getRuleContext(RepeatedExprContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(RegexParser.OR, 0); }
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
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(64);
				pureExpr();
				}
				break;
			case 2:
				{
				setState(65);
				optionalExpr();
				}
				break;
			case 3:
				{
				setState(66);
				repeatedExpr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(69);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(70);
					match(OR);
					setState(71);
					expr(3);
					}
					} 
				}
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		enterRule(_localctx, 12, RULE_pureExpr);
		int _la;
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTESYMBOL:
			case DIGIT:
			case LETTER:
			case SYMBOL:
			case HYPHEN:
			case QUESTIONMARK:
			case ESCAPED_CHAR:
			case METACHAR:
			case DOLLAR:
			case COMMA:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				character();
				}
				break;
			case L_PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(L_PAR);
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(79);
					expr(0);
					}
					}
					setState(82); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << L_PAR) | (1L << L_SQBRACKET) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << DOLLAR) | (1L << COMMA))) != 0) );
				setState(84);
				match(R_PAR);
				}
				break;
			case L_SQBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				charset();
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
		public TerminalNode DOLLAR() { return getToken(RegexParser.DOLLAR, 0); }
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
		enterRule(_localctx, 14, RULE_character);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << DOLLAR) | (1L << COMMA))) != 0)) ) {
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
		enterRule(_localctx, 16, RULE_repeatedExpr);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				pureExpr();
				setState(92);
				match(PLUS);
				setState(94);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(93);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				pureExpr();
				setState(97);
				match(STAR);
				setState(99);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(98);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				pureExpr();
				setState(102);
				repeatCounter();
				setState(104);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(103);
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
		enterRule(_localctx, 18, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				match(DIGIT);
				}
				}
				setState(111); 
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
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(RegexParser.COMMA, 0); }
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
		public TerminalNode COMMA() { return getToken(RegexParser.COMMA, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
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
		enterRule(_localctx, 20, RULE_repeatCounter);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new RangeCounterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(L_BRACE);
				setState(114);
				number();
				setState(115);
				match(COMMA);
				setState(116);
				number();
				setState(117);
				match(R_BRACE);
				}
				break;
			case 2:
				_localctx = new RBorderCounterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(L_BRACE);
				setState(120);
				number();
				setState(121);
				match(COMMA);
				setState(122);
				match(R_BRACE);
				}
				break;
			case 3:
				_localctx = new LBorderCounterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				match(L_BRACE);
				setState(125);
				match(COMMA);
				setState(126);
				number();
				setState(127);
				match(R_BRACE);
				}
				break;
			case 4:
				_localctx = new ExactCounterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				match(L_BRACE);
				setState(130);
				number();
				setState(131);
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
		public TerminalNode QUESTIONMARK() { return getToken(RegexParser.QUESTIONMARK, 0); }
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
		enterRule(_localctx, 22, RULE_optionalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			pureExpr();
			setState(136);
			match(QUESTIONMARK);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u008d\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\3\2\7\2 \n\2\f\2\16\2#\13\2\3"+
		"\2\3\2\3\2\3\3\7\3)\n\3\f\3\16\3,\13\3\3\4\3\4\5\4\60\n\4\3\4\6\4\63\n"+
		"\4\r\4\16\4\64\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6@\n\6\3\7\3\7\3"+
		"\7\3\7\5\7F\n\7\3\7\3\7\3\7\7\7K\n\7\f\7\16\7N\13\7\3\b\3\b\3\b\6\bS\n"+
		"\b\r\b\16\bT\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\n\3\n\3\n\5\na\n\n\3\n\3\n"+
		"\3\n\5\nf\n\n\3\n\3\n\3\n\5\nk\n\n\5\nm\n\n\3\13\6\13p\n\13\r\13\16\13"+
		"q\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u0088\n\f\3\r\3\r\3\r\3\r\2\3\f\16\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\2\4\3\2\3\5\7\2\3\b\r\r\17\17\24\24\27\27\2\u0096\2\32\3"+
		"\2\2\2\4*\3\2\2\2\6-\3\2\2\2\b8\3\2\2\2\n?\3\2\2\2\fE\3\2\2\2\16Y\3\2"+
		"\2\2\20[\3\2\2\2\22l\3\2\2\2\24o\3\2\2\2\26\u0087\3\2\2\2\30\u0089\3\2"+
		"\2\2\32\34\7\16\2\2\33\35\7\21\2\2\34\33\3\2\2\2\34\35\3\2\2\2\35!\3\2"+
		"\2\2\36 \5\f\7\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3\2"+
		"\2\2#!\3\2\2\2$%\7\16\2\2%&\5\4\3\2&\3\3\2\2\2\')\7\5\2\2(\'\3\2\2\2)"+
		",\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\5\3\2\2\2,*\3\2\2\2-/\7\13\2\2.\60\7\21"+
		"\2\2/.\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61\63\5\n\6\2\62\61\3\2\2\2\63"+
		"\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\7\f\2\2\67"+
		"\7\3\2\2\289\t\2\2\29:\7\7\2\2:;\t\2\2\2;\t\3\2\2\2<@\5\b\5\2=@\5\20\t"+
		"\2>@\7\23\2\2?<\3\2\2\2?=\3\2\2\2?>\3\2\2\2@\13\3\2\2\2AB\b\7\1\2BF\5"+
		"\16\b\2CF\5\30\r\2DF\5\22\n\2EA\3\2\2\2EC\3\2\2\2ED\3\2\2\2FL\3\2\2\2"+
		"GH\f\4\2\2HI\7\20\2\2IK\5\f\7\5JG\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2"+
		"\2M\r\3\2\2\2NL\3\2\2\2OZ\5\20\t\2PR\7\t\2\2QS\5\f\7\2RQ\3\2\2\2ST\3\2"+
		"\2\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\n\2\2WZ\3\2\2\2XZ\5\6\4\2YO\3\2"+
		"\2\2YP\3\2\2\2YX\3\2\2\2Z\17\3\2\2\2[\\\t\3\2\2\\\21\3\2\2\2]^\5\16\b"+
		"\2^`\7\23\2\2_a\7\b\2\2`_\3\2\2\2`a\3\2\2\2am\3\2\2\2bc\5\16\b\2ce\7\22"+
		"\2\2df\7\b\2\2ed\3\2\2\2ef\3\2\2\2fm\3\2\2\2gh\5\16\b\2hj\5\26\f\2ik\7"+
		"\b\2\2ji\3\2\2\2jk\3\2\2\2km\3\2\2\2l]\3\2\2\2lb\3\2\2\2lg\3\2\2\2m\23"+
		"\3\2\2\2np\7\4\2\2on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\25\3\2\2\2"+
		"st\7\25\2\2tu\5\24\13\2uv\7\27\2\2vw\5\24\13\2wx\7\26\2\2x\u0088\3\2\2"+
		"\2yz\7\25\2\2z{\5\24\13\2{|\7\27\2\2|}\7\26\2\2}\u0088\3\2\2\2~\177\7"+
		"\25\2\2\177\u0080\7\27\2\2\u0080\u0081\5\24\13\2\u0081\u0082\7\26\2\2"+
		"\u0082\u0088\3\2\2\2\u0083\u0084\7\25\2\2\u0084\u0085\5\24\13\2\u0085"+
		"\u0086\7\26\2\2\u0086\u0088\3\2\2\2\u0087s\3\2\2\2\u0087y\3\2\2\2\u0087"+
		"~\3\2\2\2\u0087\u0083\3\2\2\2\u0088\27\3\2\2\2\u0089\u008a\5\16\b\2\u008a"+
		"\u008b\7\b\2\2\u008b\31\3\2\2\2\22\34!*/\64?ELTY`ejlq\u0087";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}