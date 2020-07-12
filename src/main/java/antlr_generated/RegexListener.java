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
	 * Enter a parse tree produced by {@link RegexParser#charset_range}.
	 * @param ctx the parse tree
	 */
	void enterCharset_range(RegexParser.Charset_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#charset_range}.
	 * @param ctx the parse tree
	 */
	void exitCharset_range(RegexParser.Charset_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#charset_values}.
	 * @param ctx the parse tree
	 */
	void enterCharset_values(RegexParser.Charset_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#charset_values}.
	 * @param ctx the parse tree
	 */
	void exitCharset_values(RegexParser.Charset_valuesContext ctx);
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
	 * Enter a parse tree produced by {@link RegexParser#pure_expr}.
	 * @param ctx the parse tree
	 */
	void enterPure_expr(RegexParser.Pure_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#pure_expr}.
	 * @param ctx the parse tree
	 */
	void exitPure_expr(RegexParser.Pure_exprContext ctx);
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
	 * Enter a parse tree produced by {@link RegexParser#repeated_expr}.
	 * @param ctx the parse tree
	 */
	void enterRepeated_expr(RegexParser.Repeated_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#repeated_expr}.
	 * @param ctx the parse tree
	 */
	void exitRepeated_expr(RegexParser.Repeated_exprContext ctx);
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
	 * Enter a parse tree produced by {@link RegexParser#repeat_counter}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_counter(RegexParser.Repeat_counterContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#repeat_counter}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_counter(RegexParser.Repeat_counterContext ctx);
	/**
	 * Enter a parse tree produced by {@link RegexParser#optional_expr}.
	 * @param ctx the parse tree
	 */
	void enterOptional_expr(RegexParser.Optional_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RegexParser#optional_expr}.
	 * @param ctx the parse tree
	 */
	void exitOptional_expr(RegexParser.Optional_exprContext ctx);
}