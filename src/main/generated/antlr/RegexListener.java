// Generated from F:/repo/java/dpi-fp/src/main/grammar\Regex.g4 by ANTLR 4.8
package antlr_generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RegexParser}.
 */
public interface RegexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RegexParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(RegexParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(RegexParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(RegexParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(RegexParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#charset}.
	 * @param ctx the parse tree
	 */
	void enterCharset(RegexParser.CharsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#charset}.
	 * @param ctx the parse tree
	 */
	void exitCharset(RegexParser.CharsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#charsetRange}.
	 * @param ctx the parse tree
	 */
	void enterCharsetRange(RegexParser.CharsetRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#charsetRange}.
	 * @param ctx the parse tree
	 */
	void exitCharsetRange(RegexParser.CharsetRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#charsetValues}.
	 * @param ctx the parse tree
	 */
	void enterCharsetValues(RegexParser.CharsetValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#charsetValues}.
	 * @param ctx the parse tree
	 */
	void exitCharsetValues(RegexParser.CharsetValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RegexParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RegexParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#pureExpr}.
	 * @param ctx the parse tree
	 */
	void enterPureExpr(RegexParser.PureExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#pureExpr}.
	 * @param ctx the parse tree
	 */
	void exitPureExpr(RegexParser.PureExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#character}.
	 * @param ctx the parse tree
	 */
	void enterCharacter(RegexParser.CharacterContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#character}.
	 * @param ctx the parse tree
	 */
	void exitCharacter(RegexParser.CharacterContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#repeatedExpr}.
	 * @param ctx the parse tree
	 */
	void enterRepeatedExpr(RegexParser.RepeatedExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#repeatedExpr}.
	 * @param ctx the parse tree
	 */
	void exitRepeatedExpr(RegexParser.RepeatedExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(RegexParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(RegexParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rangeCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void enterRangeCounter(RegexParser.RangeCounterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rangeCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void exitRangeCounter(RegexParser.RangeCounterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rBorderCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void enterRBorderCounter(RegexParser.RBorderCounterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rBorderCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void exitRBorderCounter(RegexParser.RBorderCounterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lBorderCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void enterLBorderCounter(RegexParser.LBorderCounterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lBorderCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void exitLBorderCounter(RegexParser.LBorderCounterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exactCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void enterExactCounter(RegexParser.ExactCounterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exactCounter}
	 * labeled alternative in {@link RegexParser#repeatCounter}.
	 * @param ctx the parse tree
	 */
	void exitExactCounter(RegexParser.ExactCounterContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#optionalExpr}.
	 * @param ctx the parse tree
	 */
	void enterOptionalExpr(RegexParser.OptionalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#optionalExpr}.
	 * @param ctx the parse tree
	 */
	void exitOptionalExpr(RegexParser.OptionalExprContext ctx);
}