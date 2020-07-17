// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr_generated;
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

			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				expr(0);
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << L_PAR) | (1L << L_SQBRACKET) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << DOLLAR) | (1L << COMMA))) != 0) );
			setState(33);
			match(SLASH);
			setState(34);
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
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LETTER) {
				{
				{
				setState(36);
				match(LETTER);
				}
				}
				setState(41);
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
		public CharsetValuesContext charsetValues() {
			return getRuleContext(CharsetValuesContext.class,0);
		}
		public TerminalNode R_SQBRACKET() { return getToken(RegexParser.R_SQBRACKET, 0); }
		public TerminalNode CAP() { return getToken(RegexParser.CAP, 0); }
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
			setState(42);
			match(L_SQBRACKET);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CAP) {
				{
				setState(43);
				match(CAP);
				}
			}

			setState(46);
			charsetValues();
			setState(47);
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
			setState(49);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(50);
			match(HYPHEN);
			setState(51);
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
		public List<CharsetRangeContext> charsetRange() {
			return getRuleContexts(CharsetRangeContext.class);
		}
		public CharsetRangeContext charsetRange(int i) {
			return getRuleContext(CharsetRangeContext.class,i);
		}
		public List<CharacterContext> character() {
			return getRuleContexts(CharacterContext.class);
		}
		public CharacterContext character(int i) {
			return getRuleContext(CharacterContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(RegexParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(RegexParser.PLUS, i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(56);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(53);
					charsetRange();
					}
					break;
				case 2:
					{
					setState(54);
					character();
					}
					break;
				case 3:
					{
					setState(55);
					match(PLUS);
					}
					break;
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << PLUS) | (1L << DOLLAR) | (1L << COMMA))) != 0) );
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
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(61);
				pureExpr();
				}
				break;
			case 2:
				{
				setState(62);
				optionalExpr();
				}
				break;
			case 3:
				{
				setState(63);
				repeatedExpr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(71);
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
					setState(66);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(67);
					match(OR);
					setState(68);
					expr(3);
					}
					} 
				}
				setState(73);
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
		public List<CharacterContext> character() {
			return getRuleContexts(CharacterContext.class);
		}
		public CharacterContext character(int i) {
			return getRuleContext(CharacterContext.class,i);
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
			int _alt;
			setState(88);
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
				setState(75); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(74);
						character();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(77); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case L_PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(L_PAR);
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(80);
					expr(0);
					}
					}
					setState(83); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTESYMBOL) | (1L << DIGIT) | (1L << LETTER) | (1L << SYMBOL) | (1L << HYPHEN) | (1L << QUESTIONMARK) | (1L << L_PAR) | (1L << L_SQBRACKET) | (1L << ESCAPED_CHAR) | (1L << METACHAR) | (1L << DOLLAR) | (1L << COMMA))) != 0) );
				setState(85);
				match(R_PAR);
				}
				break;
			case L_SQBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
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
			setState(90);
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
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				pureExpr();
				setState(93);
				match(PLUS);
				setState(95);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(94);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				pureExpr();
				setState(98);
				match(STAR);
				setState(100);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(99);
					match(QUESTIONMARK);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				pureExpr();
				setState(103);
				repeatCounter();
				setState(105);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(104);
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
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109);
				match(DIGIT);
				}
				}
				setState(112); 
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
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new RangeCounterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(L_BRACE);
				setState(115);
				number();
				setState(116);
				match(COMMA);
				setState(117);
				number();
				setState(118);
				match(R_BRACE);
				}
				break;
			case 2:
				_localctx = new RBorderCounterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				match(L_BRACE);
				setState(121);
				number();
				setState(122);
				match(COMMA);
				setState(123);
				match(R_BRACE);
				}
				break;
			case 3:
				_localctx = new LBorderCounterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				match(L_BRACE);
				setState(126);
				match(COMMA);
				setState(127);
				number();
				setState(128);
				match(R_BRACE);
				}
				break;
			case 4:
				_localctx = new ExactCounterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(130);
				match(L_BRACE);
				setState(131);
				number();
				setState(132);
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
			setState(136);
			pureExpr();
			setState(137);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u008e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\3\2\6\2 \n\2\r\2\16\2!\3\2\3"+
		"\2\3\2\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\5\4/\n\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\6\6;\n\6\r\6\16\6<\3\7\3\7\3\7\3\7\5\7C\n\7\3\7"+
		"\3\7\3\7\7\7H\n\7\f\7\16\7K\13\7\3\b\6\bN\n\b\r\b\16\bO\3\b\3\b\6\bT\n"+
		"\b\r\b\16\bU\3\b\3\b\3\b\5\b[\n\b\3\t\3\t\3\n\3\n\3\n\5\nb\n\n\3\n\3\n"+
		"\3\n\5\ng\n\n\3\n\3\n\3\n\5\nl\n\n\5\nn\n\n\3\13\6\13q\n\13\r\13\16\13"+
		"r\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u0089\n\f\3\r\3\r\3\r\3\r\2\3\f\16\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\2\4\3\2\3\5\7\2\3\b\r\r\17\17\24\24\27\27\2\u0098\2\32\3"+
		"\2\2\2\4)\3\2\2\2\6,\3\2\2\2\b\63\3\2\2\2\n:\3\2\2\2\fB\3\2\2\2\16Z\3"+
		"\2\2\2\20\\\3\2\2\2\22m\3\2\2\2\24p\3\2\2\2\26\u0088\3\2\2\2\30\u008a"+
		"\3\2\2\2\32\34\7\16\2\2\33\35\7\21\2\2\34\33\3\2\2\2\34\35\3\2\2\2\35"+
		"\37\3\2\2\2\36 \5\f\7\2\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2"+
		"\"#\3\2\2\2#$\7\16\2\2$%\5\4\3\2%\3\3\2\2\2&(\7\5\2\2\'&\3\2\2\2(+\3\2"+
		"\2\2)\'\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+)\3\2\2\2,.\7\13\2\2-/\7\21\2\2."+
		"-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\5\n\6\2\61\62\7\f\2\2\62\7\3\2\2"+
		"\2\63\64\t\2\2\2\64\65\7\7\2\2\65\66\t\2\2\2\66\t\3\2\2\2\67;\5\b\5\2"+
		"8;\5\20\t\29;\7\23\2\2:\67\3\2\2\2:8\3\2\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2"+
		"\2\2<=\3\2\2\2=\13\3\2\2\2>?\b\7\1\2?C\5\16\b\2@C\5\30\r\2AC\5\22\n\2"+
		"B>\3\2\2\2B@\3\2\2\2BA\3\2\2\2CI\3\2\2\2DE\f\4\2\2EF\7\20\2\2FH\5\f\7"+
		"\5GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\r\3\2\2\2KI\3\2\2\2LN\5\20"+
		"\t\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2P[\3\2\2\2QS\7\t\2\2RT\5\f"+
		"\7\2SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\n\2\2X[\3\2"+
		"\2\2Y[\5\6\4\2ZM\3\2\2\2ZQ\3\2\2\2ZY\3\2\2\2[\17\3\2\2\2\\]\t\3\2\2]\21"+
		"\3\2\2\2^_\5\16\b\2_a\7\23\2\2`b\7\b\2\2a`\3\2\2\2ab\3\2\2\2bn\3\2\2\2"+
		"cd\5\16\b\2df\7\22\2\2eg\7\b\2\2fe\3\2\2\2fg\3\2\2\2gn\3\2\2\2hi\5\16"+
		"\b\2ik\5\26\f\2jl\7\b\2\2kj\3\2\2\2kl\3\2\2\2ln\3\2\2\2m^\3\2\2\2mc\3"+
		"\2\2\2mh\3\2\2\2n\23\3\2\2\2oq\7\4\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2r"+
		"s\3\2\2\2s\25\3\2\2\2tu\7\25\2\2uv\5\24\13\2vw\7\27\2\2wx\5\24\13\2xy"+
		"\7\26\2\2y\u0089\3\2\2\2z{\7\25\2\2{|\5\24\13\2|}\7\27\2\2}~\7\26\2\2"+
		"~\u0089\3\2\2\2\177\u0080\7\25\2\2\u0080\u0081\7\27\2\2\u0081\u0082\5"+
		"\24\13\2\u0082\u0083\7\26\2\2\u0083\u0089\3\2\2\2\u0084\u0085\7\25\2\2"+
		"\u0085\u0086\5\24\13\2\u0086\u0087\7\26\2\2\u0087\u0089\3\2\2\2\u0088"+
		"t\3\2\2\2\u0088z\3\2\2\2\u0088\177\3\2\2\2\u0088\u0084\3\2\2\2\u0089\27"+
		"\3\2\2\2\u008a\u008b\5\16\b\2\u008b\u008c\7\b\2\2\u008c\31\3\2\2\2\23"+
		"\34!).:<BIOUZafkmr\u0088";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}